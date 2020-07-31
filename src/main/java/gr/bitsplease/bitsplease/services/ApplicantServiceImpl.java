package gr.bitsplease.bitsplease.services;


import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.models.Applicant;
import gr.bitsplease.bitsplease.models.ApplicantSkills;
import gr.bitsplease.bitsplease.models.Skills;
import gr.bitsplease.bitsplease.repository.ApplicantRepository;
import gr.bitsplease.bitsplease.repository.ApplicantSkillsRepository;
import gr.bitsplease.bitsplease.repository.SkillsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Applicant service.
 */
@Service
public class ApplicantServiceImpl implements ApplicantService {
    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(ApplicantServiceImpl.class);
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
    public Applicant getApplicantById(int applicantId) throws ApplicantException {
        Applicant applicant = applicantRepository
                .findById(applicantId)
                .orElseThrow(() -> new ApplicantException("Cannot find applicant with this ID."));
        return applicant;
    }


    @Override
    public Applicant addApplicant(Applicant applicant) {
        return applicantRepository.save(applicant);
    }

    @Override
    public Applicant updateApplicant(Applicant applicant, int applicantId) throws ApplicantException {
        Applicant applicantInDB = applicantRepository
                .findById(applicantId)
                .orElseThrow(() -> new ApplicantException("Could not find any applicant with this ID."));
        applicantInDB.setFirstName(applicant.getFirstName());
        applicantInDB.setLastName(applicant.getLastName());
        applicantRepository.save(applicantInDB);
        return applicantInDB;
    }

    @Override
    public ApplicantSkills addSkillsToApplicant(int applicantId, int skillId) throws ApplicantException, SkillException {
        Skills skills = skillsRepository
                .findById(skillId)
                .orElseThrow(() -> new SkillException("Could not find any skill with this ID. "));
        Applicant applicant = applicantRepository
                .findById(applicantId)
                .orElseThrow(() -> new ApplicantException("Could not find any applicant with this ID."));
        Optional<ApplicantSkills> applicantSkillsOptional = applicantSkillsRepository
                .findAll()
                .stream()
                .filter(op -> op.getApplicant().getApplicantId() == applicantId && op.getSkills().getSkillsId() == skillId)
                .findFirst();
        ApplicantSkills applicantSkill;
        if (applicantSkillsOptional.isPresent()) {
            applicantSkill = applicantSkillsOptional.get();
            applicantSkill.setApplicant(applicantSkill.getApplicant());
            applicantSkill.setSkills(applicantSkill.getSkills());
            applicantSkill.setLevel(applicantSkill.getApplicant().getLevel());
        } else {
            applicantSkill = new ApplicantSkills();
            applicantSkill.setApplicant(applicant);
            applicantSkill.setSkills(skills);
            applicantSkill.setLevel(applicant.getLevel());
        }
        applicantSkillsRepository.save(applicantSkill);
        return applicantSkill;
    }

    @Override
    public List<Applicant> getApplicant(String firstName, String region, Integer skillId) {
        if (firstName != null)
            return applicantRepository.findByFirstName(firstName);
        if (region != null)
            return applicantRepository.findByRegion(region);
        if (skillId != 0) {
            List<Applicant> applicantList = applicantRepository.findAll();
            List<Applicant> applicantListMatched = new ArrayList<>();
            for (Applicant applicant : applicantList) {
                List<ApplicantSkills> applicantSkillsList = new ArrayList<>();
                applicantSkillsList = applicant.getApplicantSkills();
                for (ApplicantSkills applicantSkillsListMatch : applicantSkillsList) {
                    if (applicantSkillsListMatch.getSkills().getSkillsId() == skillId) {
                        applicantListMatched.add(applicant);
                    }
                }
            }
            return applicantListMatched;
        }
        return applicantRepository.findAll();

    }
}
