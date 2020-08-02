package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.exceptions.JobOfferNotFoundException;
import gr.bitsplease.bitsplease.exceptions.SkillNotFoundException;
import gr.bitsplease.bitsplease.models.JobOffer;
import gr.bitsplease.bitsplease.models.JobOfferSkills;
import gr.bitsplease.bitsplease.models.Skills;
import gr.bitsplease.bitsplease.repository.JobOfferRepository;
import gr.bitsplease.bitsplease.repository.JobOfferSkillsRepository;
import gr.bitsplease.bitsplease.repository.SkillsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public JobOffer addJobOffer(JobOffer jobOffer) throws JobOfferNotFoundException {
        if (jobOffer == null)
            throw new JobOfferNotFoundException("Null Job Offer");
        return jobOfferRepository.save(jobOffer);
    }

    @Override
    public JobOfferSkills addSkillsToJobOffers(int jobOfferId, int skillId) throws JobOfferNotFoundException, SkillNotFoundException {
        Skills skills = skillsRepository
                .findById(skillId)
                .orElseThrow(() -> new SkillNotFoundException("Could not find any skill with this ID."));
        JobOffer jobOffer = jobOfferRepository
                .findById(jobOfferId)
                .orElseThrow(() -> new JobOfferNotFoundException("Could not find any job offer with this ID."));
        Optional<JobOfferSkills> jobOfferSkillsOptional = jobOfferSkillsRepository
                .findAll()
                .stream()
                .filter(op -> op.getJobOffer().getJobOfferId() == jobOfferId && op.getSkills().getSkillsId() == skillId)
                .findFirst();
        JobOfferSkills jobSkills;
        if (jobOfferSkillsOptional.isPresent()) {
            jobSkills = jobOfferSkillsOptional.get();
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
    public boolean deleteJobOffer(int jobOfferId) throws JobOfferNotFoundException {
        JobOffer jobOffer = jobOfferRepository
                .findById(jobOfferId)
                .orElseThrow(() -> new JobOfferNotFoundException("This id is not associated with any job offer."));
        return true;
    }

}
