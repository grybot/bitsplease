package gr.bitsplease.bitsplease.controller;

import gr.bitsplease.bitsplease.dto.ReportNotMatched;
import gr.bitsplease.bitsplease.dto.Reporter;
import gr.bitsplease.bitsplease.models.Skills;
import gr.bitsplease.bitsplease.services.ReporterService;
import gr.bitsplease.bitsplease.services.ReporterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReporterController {
    @Autowired
    ReporterServiceImpl reporterServiceImpl;

    @GetMapping("mostOffered")
    public List<Reporter> getOffered() {
        return reporterServiceImpl.getOffered();
    }

    @GetMapping("mostRequested")
    public List <Reporter> getRequested(){
        return reporterServiceImpl.getRequested();
        }

    @GetMapping("NotMatchedByApplicant")
    public List <ReportNotMatched> notMatchedByApplicant(){
        return reporterServiceImpl.getNotMatchedByApplicants();
    }
    }

