package gr.bitsplease.bitsplease.controller;

import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.iohelper.Helper;
import gr.bitsplease.bitsplease.iohelper.ResponseMessage;
import gr.bitsplease.bitsplease.models.Applicant;
import gr.bitsplease.bitsplease.models.ApplicantSkills;
import gr.bitsplease.bitsplease.models.JobOffer;
import gr.bitsplease.bitsplease.models.Skills;
import gr.bitsplease.bitsplease.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
public class ApplicantController {
    private ApplicantService applicantService;
    private SkillService skillService;
    private JobOffersService jobOffersService;
    private ImportDataService importDataService;

    @Autowired
    public ApplicantController(ApplicantService applicantService,
                               SkillService skillService,
                               JobOffersService jobOffersService,
                               ImportDataService importDataService) {
        this.applicantService = applicantService;
        this.skillService = skillService;
        this.jobOffersService = jobOffersService;
        this.importDataService = importDataService;
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
    public List<Skills> getSkill() {
        return skillService.getSkills();
    }

    @PostMapping("skills")
    public Skills addSkills(@RequestBody Skills skills) {
        return skillService.addSkills(skills);
    }

//    @PostMapping("skill/{applicantId}/{skillId}")
//    public ApplicantSkills addSkillsToApplicant(@PathVariable int applicantId,
//                                                @PathVariable int skillId) throws ApplicantNotFoundException {
//        return applicantService.addSkillsToApplicant(skillId, applicantId);
//    }

    @PostMapping("upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (Helper.hasExcelFormat(file)) {
            try {
                importDataService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + e.getMessage() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("JobOffers")
    public List<JobOffer> getJobOffers() {
        return jobOffersService.getJobOffers();
    }

}
