package gr.bitsplease.bitsplease.services;


import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.exceptions.SkillNotFoundException;
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
    public Applicant getApplicantById(int applicantId) throws ApplicantNotFoundException {
        Applicant applicant = applicantRepository
                .findById(applicantId)
                .orElseThrow(() -> new ApplicantNotFoundException("Cannot find applicant with this ID."));
        return applicant;
    }


    @Override
    public Applicant addApplicant(Applicant applicant) throws ApplicantNotFoundException {
        if (applicant == null)
            throw new ApplicantNotFoundException("Null Applicant");
        if (applicant.getEmail() == null || !applicant.getEmail().contains("@"))
            throw new ApplicantNotFoundException("invalid applicant's email");
        return applicantRepository.save(applicant);
    }

//    @Override
//    public Applicant updateApplicant(int applicantId, String firstName, String lastName, boolean active, String level, String address, String region) throws ApplicantNotFoundException {
//        Applicant applicantInDB = applicantRepository
//                .findById(applicantId)
//                .orElseThrow(() -> new ApplicantNotFoundException("Could not find any applicant with this ID."));
//        applicantInDB.setFirstName(firstName);
//        applicantInDB.setLastName(lastName);
//        applicantInDB.setActive(active);
//        applicantInDB.setAddress(address);
//        applicantInDB.setRegion(region);
//        applicantInDB.setLevel(level);
//        applicantRepository.save(applicantInDB);
//        return applicantInDB;
//    }

    @Override
    public Applicant updateApplicant(int applicantId, String firstName, String lastName, String email, boolean active, String level, String address, String region) throws ApplicantNotFoundException {
        Applicant applicantInDB = applicantRepository
                .findById(applicantId)
                .orElseThrow(() -> new ApplicantNotFoundException("Could not find any applicant with this ID."));
        if (firstName == null) applicantInDB.setFirstName(applicantInDB.getFirstName()); else applicantInDB.setFirstName(firstName);
        if (lastName == null) applicantInDB.setLastName(applicantInDB.getLastName()); else applicantInDB.setLastName(lastName);
        if (email == null) applicantInDB.setEmail(applicantInDB.getEmail()); else applicantInDB.setEmail(email);
        if (address == null) applicantInDB.setAddress(applicantInDB.getAddress()); else applicantInDB.setAddress(address);
        if (region == null) applicantInDB.setRegion(applicantInDB.getRegion()); else applicantInDB.setRegion(region);
        if (active) applicantInDB.setActive(true); else applicantInDB.setActive(false);
        applicantRepository.save(applicantInDB);
        return applicantInDB;
    }

    @Override
    public ApplicantSkills addSkillsToApplicant(int applicantId, int skillId) throws ApplicantNotFoundException, SkillNotFoundException {
        Skills skills = skillsRepository
                .findById(skillId)
                .orElseThrow(() -> new SkillNotFoundException("Could not find any skill with this ID."));
        Applicant applicant = applicantRepository
                .findById(applicantId)
                .orElseThrow(() -> new ApplicantNotFoundException("Could not find any applicant with this ID."));
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
    public List<Applicant> getApplicant(String firstName, String region, String email, String address,
                                        String dob, Integer skillId) {
        if (firstName != null )
            return applicantRepository.findByFirstName(firstName);
        else if (region != null)
            return applicantRepository.findByRegion(region);
        else if (email != null && email.contains("@"))
            return applicantRepository.findByEmail(email);
        else if (address != null)
            return applicantRepository.findByAddress(address);
        else if (dob != null)
            return applicantRepository.findByDob(dob);
        else if (skillId != 0) {
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
