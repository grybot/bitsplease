package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.exceptions.SkillException;
import gr.bitsplease.bitsplease.models.Skills;

import java.util.List;

/**
 * The interface Skill service.
 */
public interface SkillService {
     /**
      * Gets skills.
      *
      * @return list of all skills
      */
     List<Skills> getSkills();

     /**
      * Add skills.
      *
      * @param skills the skills
      * @return new skill
      */
     Skills addSkills(Skills skills);

     /**
      * Update skill.
      *
      * @param skills  the skills
      * @param skillId the skill id
      * @return updated skill
      * @throws SkillException skill is not found by id
      */
     Skills updateSkills(Skills skills, int skillId) throws SkillException;

     /**
      * Delete skill.
      *
      * @param skillId the skill id
      * @return boolean (true if skill was deleted, false if not)
      * @throws SkillException skill is not found by id
      */
     boolean deleteSkills(int skillId) throws SkillException;

     /**
      * Gets skill by id.
      *
      * @param skillsId the skills id
      * @return skill specified by id
      * @throws SkillException if skill is not found by id
      */
     Skills getSkillById(int skillsId) throws SkillException;
}
