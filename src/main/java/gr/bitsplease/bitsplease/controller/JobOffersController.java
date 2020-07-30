package gr.bitsplease.bitsplease.controller;

import gr.bitsplease.bitsplease.exceptions.JobOfferException;
import gr.bitsplease.bitsplease.exceptions.SkillException;
import gr.bitsplease.bitsplease.models.JobOffer;
import gr.bitsplease.bitsplease.models.JobOfferSkills;
import gr.bitsplease.bitsplease.services.JobOffersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Job offers controller.
 */
@RestController
public class JobOffersController {
    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(JobOffersController.class);
    @Autowired
    private JobOffersService jobOffersService;


    /**
     * Gets job offers.
     *
     * @return list of all job offers
     */
    @GetMapping("JobOffers")
    public List<JobOffer> getJobOffers() {
        return jobOffersService.getJobOffers();
    }

    /**
     * Add job offer .
     *
     * @param jobOffer the job offer
     * @return new job order
     */
    @PostMapping("JobOffer")
    public JobOffer addJobOffer(@RequestBody JobOffer jobOffer){
        return jobOffersService.addJobOffer(jobOffer);
    }

    /**
     * Add skills to job offers.
     *
     * @param jobOfferId the job offer id
     * @param skillId    the skill id
     * @return updated job offer (w/ a new skill)
     * @throws SkillException    if skill is not found by id
     * @throws JobOfferException if job offer is not found by id
     */
    @PostMapping("jobOffer/{jobOfferId}/{skillId}")
    public JobOfferSkills addSkillsToJobOffers(@PathVariable int jobOfferId,
                                               @PathVariable int skillId) throws SkillException, JobOfferException {
        return jobOffersService.addSkillsToJobOffers(jobOfferId, skillId);
    }

    /**
     * Delete job offer.
     *
     * @param jobOfferId the job offer id
     * @return boolean(true if job offer exists and successfully deleted, if false error will be thrown)
     * @throws JobOfferException if job offer is not found by id
     */
    @DeleteMapping("JobOffer/{jobOfferId}")
    public boolean deleteJobOffer(@PathVariable int jobOfferId) throws JobOfferException {
        return jobOffersService.deleteJobOffer(jobOfferId);
    }


}
