package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.models.Skills;
import gr.bitsplease.bitsplease.repository.ApplicantRepository;
import gr.bitsplease.bitsplease.repository.ApplicantSkillsRepository;
import gr.bitsplease.bitsplease.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {
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
    public Skills updateSkills(Skills skills) {
        return null;
    }

    @Override
    public boolean deleteSkills(int skillIndex) {
        return false;
    }

    @Override
    public Skills getSkill(int skillsId) {
        return null;
    }

}