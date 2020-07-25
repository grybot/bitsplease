package gr.bitsplease.bitsplease.controller;

import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.models.Applicant;
import gr.bitsplease.bitsplease.models.ApplicantSkills;
import gr.bitsplease.bitsplease.models.Skills;
import gr.bitsplease.bitsplease.services.ApplicantService;
import gr.bitsplease.bitsplease.services.SkillService;
import gr.bitsplease.bitsplease.services.SkillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ApplicantController {
    private ApplicantService applicantService;
    private SkillService skillService;

    @Autowired
    public ApplicantController(ApplicantService applicantService, SkillService skillService) {
        this.applicantService = applicantService;
        this.skillService = skillService;
    }

    @GetMapping("applicant")
    public List<Applicant> getApplicants() {
        return applicantService.getApplicants();
    }

    @PostMapping("applicant")
    public Applicant addApplicant(@RequestBody Applicant applicant) {
        return applicantService.addApplicant(applicant);
    }

    @GetMapping("skills")
    public List<Skills> getSkill(){
        return skillService.getSkills();
    }

    @PostMapping("skills")
    public Skills addSkills(@RequestBody Skills skills){
        return skillService.addSkills(skills);
    }

    @PostMapping("skill/{applicantId}/{skillId}")
    public ApplicantSkills addSkillsToApplicant(@PathVariable int applicantId,
                                               @PathVariable int skillId) throws ApplicantNotFoundException {
        return applicantService.addSkillsToApplicant(skillId,applicantId);
    }

}
