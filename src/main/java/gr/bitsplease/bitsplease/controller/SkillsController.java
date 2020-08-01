package gr.bitsplease.bitsplease.controller;

import gr.bitsplease.bitsplease.exceptions.SkillException;
import gr.bitsplease.bitsplease.models.Skills;
import gr.bitsplease.bitsplease.services.ApplicantService;
import gr.bitsplease.bitsplease.services.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Skills controller.
 */
@RestController
public class SkillsController {
    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(SkillsController.class);
    @Autowired
    private SkillService skillService;
    @Autowired
    private ApplicantService applicantService;


    /**
     * Gets skill.
     *
     * @return list of all skills
     */
    @GetMapping("skills")
    public List<Skills> getSkill() {
        return skillService.getSkills();
    }

    /**
     * Add skill.
     *
     * @param skills attributes of skill
     * @return a new skill
     */
    @PostMapping("skills")
    public Skills addSkills(@RequestBody Skills skills) throws SkillException {
        return skillService.addSkills(skills);
    }

    /**
     * Update skill.
     *
     * @param skillId unique identifier of skill
     * @param name the name of the skill
     * @return updated skill
     * @throws SkillException the skill exception
     */



    @PatchMapping("skill/{skillId}/{name}")

    public Skills updateSkills(@PathVariable(required = true) int skillId,
                                     @RequestParam(required = false) String name)
            throws SkillException {
        return skillService.updateSkills(skillId,name);
    }


    /**
     * Delete skill.
     *
     * @param skillId unique identifier of skill
     * @return boolean(if skill was deleted)
     * @throws SkillException if skill not found by id
     */
    @DeleteMapping("skill/{skillIndex}")
    public boolean deleteSkills(@PathVariable int skillId) throws SkillException {
        return skillService.deleteSkills(skillId);
    }

    /**
     * Gets skill by id.
     *
     * @param skillId the skill id
     * @return the skill by id
     * @throws SkillException if skill not found by id
     */
    @GetMapping("skill/{skillId}")
    public Skills getSkillById(@PathVariable int skillId) throws SkillException {
        return skillService.getSkillById(skillId);
    }
}
