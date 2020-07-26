package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.models.Skills;

import java.util.List;

public interface SkillService {

     List<Skills> getSkills();

     Skills addSkills(Skills skills);
     Skills updateSkills(Skills skills);
     boolean deleteSkills(int skillIndex);
     Skills getSkill(int skillsId);

}
