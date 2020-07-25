package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.models.Applicant;
import gr.bitsplease.bitsplease.models.ApplicantSkills;

import java.util.List;
import java.util.Optional;

public interface ApplicantService {

    List<Applicant> getApplicants();

    Optional<Applicant> getApplicantById(int applicantId);

    Applicant addApplicant(Applicant applicant);

    Applicant updateApplicant(Applicant applicant, int applicantId) throws ApplicantNotFoundException;

    ApplicantSkills addSkillsToApplicant(int applicantId, int skillId)
            throws ApplicantNotFoundException;

}
