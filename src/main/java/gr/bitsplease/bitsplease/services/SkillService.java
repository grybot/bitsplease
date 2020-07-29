package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.models.Skills;

import java.util.List;

public interface SkillService {
     List<Skills> getSkills();

     Skills addSkills(Skills skills);

     Skills updateSkills(Skills skills, int skillId) throws ApplicantNotFoundException;

     boolean deleteSkills(int skillIndex) throws ApplicantNotFoundException;

     Skills getSkillById(int skillsId) throws ApplicantNotFoundException;
}
