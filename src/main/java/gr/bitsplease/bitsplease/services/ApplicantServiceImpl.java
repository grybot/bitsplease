package gr.bitsplease.bitsplease.services;


import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.models.Applicant;
import gr.bitsplease.bitsplease.models.ApplicantSkills;
import gr.bitsplease.bitsplease.models.Skills;
import gr.bitsplease.bitsplease.repository.ApplicantRepository;
import gr.bitsplease.bitsplease.repository.ApplicantSkillsRepository;
import gr.bitsplease.bitsplease.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicantServiceImpl implements ApplicantService{

    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private SkillsRepository skillsRepository;
    @Autowired
    private ApplicantSkillsRepository applicantSkillsRepository;


    @Override
    public List<Applicant> getApplicants() {
        return applicantRepository.findAll();
    }

    @Override
    public Optional<Applicant> getApplicantById(int applicantId) {
        return applicantRepository.findById(applicantId);
    }



    @Override
    public Applicant addApplicant(Applicant applicant) {
        return applicantRepository.save(applicant);
    }

    @Override
    public Applicant updateApplicant(Applicant applicant, int applicantId) throws ApplicantNotFoundException{
        Applicant applicantInDB = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new ApplicantNotFoundException("Applicant Not Found"));
        applicantInDB.setFirstName(applicant.getFirstName());
        applicantInDB.setLastName(applicant.getLastName());
        applicantRepository.save(applicantInDB);
        return applicantInDB;
    }
   @Override
    public ApplicantSkills addSkillsToApplicant(int applicantId, int skillId)
            throws ApplicantNotFoundException {
        Skills skills = skillsRepository
                .findById(skillId)
                .orElseThrow(() -> new
                        ApplicantNotFoundException("Cannot find applicant"));
        Applicant applicant = applicantRepository
                .findById(applicantId)
                .orElseThrow(() -> new
                        ApplicantNotFoundException("Cannot find Customer"));

        Optional<ApplicantSkills> applicantSkillsOptional = applicantSkillsRepository

                .findAll()
                .stream()
                .filter(op -> op.getApplicant()
                        .equals(applicantId) && op.getSkills()
                        .getSkillsId() == skillId)
                .findFirst();


    // if (applicantSkillsOptional.isPresent()) {

    //    } else {
      ApplicantSkills applicantSkills ;
       Skills skillsInApplicant= applicantSkills.getSkills();

       skillsInApplicant.setApplicantSkills(applicantSkills.setSkills(skillsInApplicant));

              skillsRepository.save(skillsInApplicant);
       applicantSkillsRepository.save(applicantSkills);

         return applicantSkills;
    }
}
