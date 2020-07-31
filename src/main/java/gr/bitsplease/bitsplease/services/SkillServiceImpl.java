package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.exceptions.SkillException;
import gr.bitsplease.bitsplease.models.Skills;
import gr.bitsplease.bitsplease.repository.ApplicantRepository;
import gr.bitsplease.bitsplease.repository.ApplicantSkillsRepository;
import gr.bitsplease.bitsplease.repository.SkillsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SkillServiceImpl implements SkillService {
    Logger logger = LoggerFactory.getLogger(SkillServiceImpl.class);
    @Autowired
    private SkillsRepository skillsRepository;

    @Autowired
    private ApplicantSkillsRepository applicantSkillsRepository;


    @Override
    public List<Skills> getSkills() {
        return skillsRepository.findAll();

    }

    @Override
    public Skills addSkills(Skills skills) {

        return skillsRepository.save(skills);
    }

    @Override
    public Skills updateSkills(Skills skills, int skillId) throws SkillException {
        Skills skillinDB = skillsRepository
                .findById(skillId)
                .orElseThrow(() -> new SkillException("Skill Not Found."));
        skillinDB.setName(skills.getName());
        skillsRepository.save(skillinDB);
        return skillinDB;
    }

    @Override
    public boolean deleteSkills(int skillId) throws SkillException {
        skillsRepository
                .deleteById(skillId);
        return true;
    }

    @Override
    public Skills getSkillById(int skillsId) throws SkillException {
        return skillsRepository
                .findById(skillsId)
                .orElseThrow(() -> new SkillException("Couldn't find any skill based on this ID"));
    }

}