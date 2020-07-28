package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.models.Applicant;
import gr.bitsplease.bitsplease.models.ApplicantSkills;

import javax.persistence.Column;
import java.util.List;
import java.util.Optional;

public interface ApplicantService {

    List<Applicant> getApplicants();

    Applicant getApplicantById(int applicantId) throws ApplicantNotFoundException;

    Applicant addApplicant(Applicant applicant);

    Applicant updateApplicant(Applicant applicant, int applicantId) throws ApplicantNotFoundException;

    ApplicantSkills addSkillsToApplicant(int applicantId, int skillId)
            throws ApplicantNotFoundException;

    List<Applicant> getApplicant(String firstName,
                                     String region, Integer skillId);

}
