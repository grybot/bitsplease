package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.exceptions.SkillNotFoundException;
import gr.bitsplease.bitsplease.models.Skills;

import java.util.List;

public interface SkillService {
     List<Skills> getSkills();

     Skills addSkills(Skills skills);

     Skills updateSkills(Skills skills, int skillId) throws SkillNotFoundException;

     boolean deleteSkills(int skillIndex) throws SkillNotFoundException;

     Skills getSkillById(int skillsId) throws SkillNotFoundException;
}
