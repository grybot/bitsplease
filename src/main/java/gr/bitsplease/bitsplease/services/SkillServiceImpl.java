package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
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
    public Skills updateSkills(Skills skills, int skillId) throws ApplicantNotFoundException {
        Skills skillinDB = skillsRepository
                .findById(skillId)
                .orElseThrow(() -> new ApplicantNotFoundException("Skill Not Found."));
        skillinDB.setName(skills.getName());
        skillsRepository.save(skillinDB);
        return skillinDB;
    }

    @Override
    public boolean deleteSkills(int skillIndex) throws ApplicantNotFoundException {
        skillsRepository.deleteById(skillIndex);
        return true;
    }

    @Override
    public Skills getSkillById(int skillsId) throws ApplicantNotFoundException {
        return skillsRepository
                .findById(skillsId)
                .orElseThrow(()-> new ApplicantNotFoundException("Couldn't find any skill based on this ID"));
    }

}