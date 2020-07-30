package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.models.ApplicantSkills;

import java.util.List;

/**
 * The interface Applicant skills service.
 */
public interface ApplicantSkillsService {

    /**
     * Gets list of all applicants with skills.
     *
     * @return list of all applicants with skills
     */
    List<ApplicantSkills> getApplicantSkills();
  //  ApplicantSkills addApplicantSkills(Skills skills);
}
