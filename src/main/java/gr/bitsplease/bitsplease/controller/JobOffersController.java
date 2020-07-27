package gr.bitsplease.bitsplease.controller;

import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.models.ApplicantSkills;
import gr.bitsplease.bitsplease.models.JobOffer;
import gr.bitsplease.bitsplease.models.JobOfferSkills;
import gr.bitsplease.bitsplease.repository.JobOfferRepository;
import gr.bitsplease.bitsplease.services.JobOffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobOffersController {
    @Autowired
    private JobOffersService jobOffersService;


    @GetMapping("JobOffers")
    public List<JobOffer> getJobOffers() {
        return jobOffersService.getJobOffers();
    }
    @PostMapping("JobOffer")
    public JobOffer addJobOffer(@RequestBody JobOffer jobOffer){
        return jobOffersService.addJobOffer(jobOffer);
    }
    @PostMapping("jobOffer/{jobOfferId}/{skillId}")
    public JobOfferSkills addSkillsToJobOffers(@PathVariable int jobOfferId,
                                               @PathVariable int skillId) throws ApplicantNotFoundException {
        return jobOffersService.addSkillsToJobOffers(jobOfferId, skillId);
    }


}
