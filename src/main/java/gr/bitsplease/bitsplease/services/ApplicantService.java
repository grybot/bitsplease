package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.exceptions.ApplicantException;
import gr.bitsplease.bitsplease.exceptions.SkillException;
import gr.bitsplease.bitsplease.models.Applicant;
import gr.bitsplease.bitsplease.models.ApplicantSkills;

import java.util.List;

/**
 * The interface Applicant service.
 */
public interface ApplicantService {

    /**
     * Gets applicants.
     *
     * @return list of all applicants
     */
    List<Applicant> getApplicants();

    /**
     * Gets applicant by id.
     *
     * @param applicantId the applicant id
     * @return applicant by id
     * @throws ApplicantException if applicant not found by id
     */
    Applicant getApplicantById(int applicantId) throws ApplicantException;

    /**
     * Add applicant.
     *
     * @param applicant the applicant
     * @return a new applicant
     */
    Applicant addApplicant(Applicant applicant);

    /**
     * Update applicant.
     *
     * @param applicant   the applicant
     * @param applicantId the applicant id
     * @return updated applicant
     * @throws ApplicantException if applicant not found by id
     */
    Applicant updateApplicant(Applicant applicant, int applicantId) throws ApplicantException;

    /**
     * Add skills to applicant.
     *
     * @param applicantId the applicant id
     * @param skillId     the skill id
     * @return applicant with new skills
     * @throws ApplicantException if applicant not found by id
     * @throws SkillException    if skill not found by id
     */
    ApplicantSkills addSkillsToApplicant(int applicantId, int skillId)
            throws ApplicantException, SkillException;

    List<Applicant> getApplicant(String firstName,
                                 String region, Integer skillId);

}
