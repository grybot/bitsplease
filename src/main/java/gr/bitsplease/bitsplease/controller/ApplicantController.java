package gr.bitsplease.bitsplease.controller;

import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.iohelper.Helper;
import gr.bitsplease.bitsplease.iohelper.ResponseMessage;
import gr.bitsplease.bitsplease.models.Applicant;
import gr.bitsplease.bitsplease.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
public class ApplicantController {
    @Autowired
    private ApplicantService applicantService;

    @GetMapping("applicant")
    public List<Applicant> getApplicants() {
        return applicantService.getApplicants();
    }

    @PostMapping("applicant")
    public Applicant addApplicant(@RequestBody Applicant applicant) {
        return applicantService.addApplicant(applicant);
    }

    @GetMapping("applicant/{applicantId}")
    public Applicant getApplicantById(@PathVariable int applicantId)
            throws ApplicantNotFoundException {
        return applicantService.getApplicantById(applicantId);
    }

    @GetMapping("applicantFiltered")
    public List<Applicant> getApplicant(@RequestParam(required = false) String firstName
            , @RequestParam(required = false) String region, @RequestParam(required = false) Integer skillId) {
        return applicantService.getApplicant(firstName, region, skillId);

    }
}
