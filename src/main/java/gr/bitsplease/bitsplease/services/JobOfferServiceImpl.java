package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.models.*;
import gr.bitsplease.bitsplease.repository.JobOfferRepository;
import gr.bitsplease.bitsplease.repository.JobOfferSkillsRepository;
import gr.bitsplease.bitsplease.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Skills product = skillsRepository
                .findById(skillId)
                .orElseThrow(() -> new ApplicantNotFoundException("Cannot find product"));
        JobOffer orders = jobOfferRepository
                .findById(jobOfferId)
                .orElseThrow(() -> new ApplicantNotFoundException("Cannot find Customer"));
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
            jobSkills.setJobOffer(orders);
            jobSkills.setSkills(product);
        }
        jobOfferSkillsRepository.save(jobSkills);
        return jobSkills;
    }
}
