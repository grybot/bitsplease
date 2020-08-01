package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.exceptions.JobOfferException;
import gr.bitsplease.bitsplease.exceptions.SkillException;
import gr.bitsplease.bitsplease.models.JobOffer;
import gr.bitsplease.bitsplease.models.JobOfferSkills;

import java.util.List;


/**
 * The interface Job offers service.
 */
public interface JobOffersService {

    /**
     * Gets job offers.
     *
     * @return list of all job offers
     */
    List<JobOffer> getJobOffers();

    /**
     * create a job offer.
     *
     * @param jobOffer job offer
     * @return new job offer
     */
    JobOffer addJobOffer(JobOffer jobOffer);

    /**
     * Add skills to job offer.
     *
     * @param jobOfferId the job offer id
     * @param skillId    the skill id
     * @return job offer with new skills
     * @throws JobOfferException if job offer is not found by id
     * @throws SkillException    if skill not found by id
     */
    JobOfferSkills addSkillsToJobOffers(int jobOfferId, int skillId) throws JobOfferException, SkillException;

    boolean deleteJobOffer(int jobOfferId) throws JobOfferException;

    List<JobOffer> getJobOffer(String companyName,
                               String region, String dop , Integer skillId);
}
