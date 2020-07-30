package gr.bitsplease.bitsplease.controller;

import gr.bitsplease.bitsplease.exceptions.ApplicantException;
import gr.bitsplease.bitsplease.exceptions.SkillException;
import gr.bitsplease.bitsplease.models.Applicant;
import gr.bitsplease.bitsplease.models.ApplicantSkills;
import gr.bitsplease.bitsplease.services.ApplicantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Applicant controller.
 */
@RestController
public class ApplicantController {
    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(ApplicantController.class);
    @Autowired
    private ApplicantService applicantService;

    /**
     * Gets applicants.
     *
     * @return list of all applicants
     */
    @GetMapping("applicant")
    public List<Applicant> getApplicants() {
        return applicantService.getApplicants();
    }

    /**
     * Add applicant applicant.
     *
     * @param applicant the applicant
     * @return new applicant
     */
    @PostMapping("applicant")
    public Applicant addApplicant(@RequestBody Applicant applicant) {
        return applicantService.addApplicant(applicant);
    }

    /**
     * Get applicant by id.
     *
     * @param applicantId the applicant id
     * @return the applicant specified by ID
     * @throws ApplicantException If applicant is not found by id
     */
    @GetMapping("applicant/{applicantId}")
    public Applicant getApplicantById(@PathVariable int applicantId)
            throws ApplicantException {
        return applicantService.getApplicantById(applicantId);
    }

    /**
     * Add skills to applicant .
     *
     * @param applicantId the applicant id
     * @param skillId     the skill id
     * @return applicant with updated skills
     * @throws ApplicantException if applicant is not found by id
     * @throws SkillException     if skill is not found by id
     */
    @PostMapping("skill/{applicantId}/{skillId}")
    public ApplicantSkills addSkillsToApplicant(@PathVariable int applicantId,
                                                @PathVariable int skillId) throws ApplicantException, SkillException {
        return applicantService.addSkillsToApplicant(applicantId, skillId);
    }

    /**
     * Index string.
     *
     * @return the string
     */

}
