package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.models.Applicant;
import gr.bitsplease.bitsplease.models.ApplicantSkills;
import gr.bitsplease.bitsplease.models.JobOffer;
import gr.bitsplease.bitsplease.models.JobOfferSkills;

import java.util.List;


public interface JobOffersService {

    List<JobOffer> getJobOffers();

    JobOffer addJobOffer(JobOffer jobOffer);

    JobOfferSkills addSkillsToJobOffers(int jobOfferId, int skillId) throws ApplicantNotFoundException;

    List<JobOffer> getJobOffer(String companyName,
                                 String region, Integer skillId);
}
