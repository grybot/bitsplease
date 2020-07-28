package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.models.*;
import gr.bitsplease.bitsplease.repository.JobOfferRepository;
import gr.bitsplease.bitsplease.repository.JobOfferSkillsRepository;
import gr.bitsplease.bitsplease.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobOfferServiceImpl implements JobOffersService {
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
    public JobOfferSkills addSkillsToJobOffers(int jobOfferId, int skillId) throws ApplicantNotFoundException {
        Skills skills = skillsRepository
                .findById(skillId)
                .orElseThrow(() -> new ApplicantNotFoundException("Cannot find product"));
        JobOffer jobOffer = jobOfferRepository
                .findById(jobOfferId)
                .orElseThrow(() -> new ApplicantNotFoundException("Cannot find Customer"));
        Optional<JobOfferSkills> jobOfferSkillByJobOfferAndSkills = jobOfferSkillsRepository.findJobOfferSkillByJobOfferAndSkills(jobOfferId, skillId);
        JobOfferSkills jobSkills;
        JobOfferSkills jobOfferSkills = new JobOfferSkills();
        if (jobOfferSkillByJobOfferAndSkills.isPresent()) {
            jobSkills = jobOfferSkillByJobOfferAndSkills.get();
            jobSkills.setJobOffer(jobSkills.getJobOffer());
            jobSkills.setSkills(jobSkills.getSkills());
        } else {
            jobSkills = new JobOfferSkills();
            jobSkills.setJobOffer(jobOffer);
            jobSkills.setSkills(skills);
        }

        jobOfferSkillsRepository.save(jobSkills);
        return jobSkills;
    }

    @Override
    public List<JobOffer> getJobOffer(String companyName, String region, Integer skillId) {
        if (companyName != null)
            return jobOfferRepository.findByCompanyName(companyName);
        if (region != null)
            return jobOfferRepository.findByRegion(region);
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
