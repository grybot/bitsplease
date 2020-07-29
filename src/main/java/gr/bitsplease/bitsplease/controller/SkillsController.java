package gr.bitsplease.bitsplease.controller;

import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.exceptions.SkillNotFoundException;
import gr.bitsplease.bitsplease.models.ApplicantSkills;
import gr.bitsplease.bitsplease.models.Skills;
import gr.bitsplease.bitsplease.services.ApplicantService;
import gr.bitsplease.bitsplease.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SkillsController {
    @Autowired
    private SkillService skillService;
    @Autowired
    private ApplicantService applicantService;


    @GetMapping("skills")
    public List<Skills> getSkill() {
        return skillService.getSkills();
    }

    @PostMapping("skills")
    public Skills addSkills(@RequestBody Skills skills) {
        return skillService.addSkills(skills);
    }

    @PutMapping("skill/{skillId}")
    public Skills updateSkills(@PathVariable int skillId,
                               @RequestBody Skills skills) throws SkillNotFoundException {
        return skillService.updateSkills(skills, skillId);
    }
    @DeleteMapping("skill/{skillIndex}")
    public boolean deleteSkills(@PathVariable int skillIndex) throws SkillNotFoundException {
        return skillService.deleteSkills(skillIndex);
    }
    @GetMapping("skill/{skillId}")
    public Skills getSkillById(@PathVariable int skillId) throws SkillNotFoundException {
        return skillService.getSkillById(skillId);
    }

}
