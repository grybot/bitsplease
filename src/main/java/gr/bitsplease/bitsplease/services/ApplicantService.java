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
    Applicant addApplicant(Applicant applicant) throws ApplicantException;


    /**
     * Update applicant by id.
     *
     * @param applicantId the applicant id
     * @param firstName the applicant firstName
     * @param lastName the applicant lastName
     * @param active if the applicant is active
     * @param level the applicant level
     * @param address the applicant address
     * @param region the applicant region
     * @return the applicant specified by ID
     * @throws ApplicantException If applicant is not found by id
     */
    Applicant updateApplicant(int applicantId, String firstName, String lastName, String email,
                              boolean active, String level, String address, String region) throws ApplicantException;

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
