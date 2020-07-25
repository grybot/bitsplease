package gr.bitsplease.bitsplease.services;


import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.models.Applicant;
import gr.bitsplease.bitsplease.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicantServiceImpl implements ApplicantService{

    @Autowired
    private ApplicantRepository applicantRepository;


    @Override
    public List<Applicant> getApplicants() {
        return applicantRepository.findAll();
    }

    @Override
    public Optional<Applicant> getApplicantById(double applicantId) {
        return applicantRepository.findById(applicantId);
    }

    @Override
    public Applicant addApplicant(Applicant applicant) {
        return applicantRepository.save(applicant);
    }

    @Override
    public Applicant updateApplicant(Applicant applicant, double applicantId) throws ApplicantNotFoundException{
        Applicant applicantInDB = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new ApplicantNotFoundException("Applicant Not Found"));
        applicantInDB.setFirstName(applicant.getFirstName());
        applicantInDB.setLastName(applicant.getLastName());
        applicantRepository.save(applicantInDB);
        return applicantInDB;
    }
}
