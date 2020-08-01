package gr.bitsplease.bitsplease.services;


import gr.bitsplease.bitsplease.exceptions.JobOfferException;
import gr.bitsplease.bitsplease.exceptions.SkillException;
import gr.bitsplease.bitsplease.models.*;
import gr.bitsplease.bitsplease.repository.JobOfferRepository;
import gr.bitsplease.bitsplease.repository.JobOfferSkillsRepository;
import gr.bitsplease.bitsplease.repository.SkillsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobOfferServiceImpl implements JobOffersService {
    Logger logger = LoggerFactory.getLogger(JobOfferServiceImpl.class);
    @Autowired
    private JobOfferRepository jobOfferRepository;
    @Autowired
    private SkillsRepository skillsRepository;
    @Autowired
    private JobOfferSkillsRepository jobOfferSkillsRepository;

    @Override
    public List<JobOffer> getJobOffers() {
        return jobOfferRepository.findAll();
    }

    @Override
    public JobOffer addJobOffer(JobOffer jobOffer) {
        return jobOfferRepository.save(jobOffer);
    }
    @Override
    public JobOfferSkills addSkillsToJobOffers(int jobOfferId, int skillId) throws JobOfferException, SkillException {
        Skills skills = skillsRepository
                .findById(skillId)
                .orElseThrow(() -> new SkillException("Could not find any skill with this ID."));
        JobOffer jobOffer = jobOfferRepository
                .findById(jobOfferId)
                .orElseThrow(() -> new JobOfferException("Could not find any job offer with this ID."));
        Optional<JobOfferSkills> jobOfferSkillsOptional = jobOfferSkillsRepository
                .findAll()
                .stream()
                .filter(op -> op.getJobOffer().getJobOfferId() == jobOfferId && op.getSkills().getSkillsId() == skillId)
                .findFirst();
        JobOfferSkills jobSkills;
        if(jobOfferSkillsOptional.isPresent()){
            jobSkills = jobOfferSkillsOptional.get();
            jobSkills.setJobOffer(jobSkills.getJobOffer());
            jobSkills.setSkills(jobSkills.getSkills());
        }else{
            jobSkills = new JobOfferSkills();
            jobSkills.setJobOffer(jobOffer);
            jobSkills.setSkills(skills);
        }
        jobOfferSkillsRepository.save(jobSkills);
        return jobSkills;
    }

    @Override
    public boolean deleteJobOffer(int jobOfferId) throws JobOfferException {
        JobOffer jobOffer = jobOfferRepository
                .findById(jobOfferId)
                .orElseThrow(() -> new JobOfferException("This id is not associated with any job offer."));
        return true;
    }

    @Override
    public List<JobOffer> getJobOffer(String companyName, String region, String dop, Integer skillId) {
        if (companyName != null)
            return jobOfferRepository.findByCompanyName(companyName);
        if (region != null)
            return jobOfferRepository.findByRegion(region);
        if(dop != null)
            return jobOfferRepository.findByDop(dop);
        if (skillId != 0) {
            List<JobOffer> jobOfferList = jobOfferRepository.findAll();
            List<JobOffer> jobOffersListMatched = new ArrayList<>();
            for (JobOffer jobOffer : jobOfferList) {
                List<JobOfferSkills> jobOfferSkillsList = new ArrayList<>();
                jobOfferSkillsList = jobOffer.getJobOfferSkills();
                for (JobOfferSkills jobOfferSkillsListMatch : jobOfferSkillsList) {
                    if (jobOfferSkillsListMatch.getSkills().getSkillsId() == skillId) {
                        jobOffersListMatched.add(jobOffer);
                    }
                }
            }
            return jobOffersListMatched;
        }
        return jobOfferRepository.findAll();
    }
}
