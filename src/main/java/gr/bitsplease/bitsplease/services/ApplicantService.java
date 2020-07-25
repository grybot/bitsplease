package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.models.Applicant;

import java.util.List;
import java.util.Optional;

public interface ApplicantService {

    List<Applicant> getApplicants();

    Optional<Applicant> getApplicantById(double applicantId);

    Applicant addApplicant(Applicant applicant);

    Applicant updateApplicant(Applicant applicant, double applicantId) throws ApplicantNotFoundException;

}
