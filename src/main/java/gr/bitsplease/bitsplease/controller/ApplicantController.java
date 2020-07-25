package gr.bitsplease.bitsplease.controller;

import gr.bitsplease.bitsplease.models.Applicant;
import gr.bitsplease.bitsplease.services.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class ApplicantController {
    private ApplicantService applicantService;
    @Autowired
    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }


    @RequestMapping("applicant")
    public List<Applicant> getApplicants(){
        return applicantService.getApplicants();
    }

}
