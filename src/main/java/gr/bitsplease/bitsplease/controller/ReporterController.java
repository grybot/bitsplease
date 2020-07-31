package gr.bitsplease.bitsplease.controller;

import gr.bitsplease.bitsplease.dto.ReportNotMatched;
import gr.bitsplease.bitsplease.dto.Reporter;
import gr.bitsplease.bitsplease.services.ReporterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Reporter controller.
 */
@RestController
public class ReporterController {
    /**
     * The Reporter service.
     */
    @Autowired
    ReporterServiceImpl reporterServiceImpl;

    /**
     * Gets offered.
     *
     * @return most offered skills from applicants
     */
    @GetMapping("mostOffered")
    public List<Reporter> getOffered() {
        return reporterServiceImpl.getOffered();
    }

    /**
     * Gets requested.
     *
     * @return the most requested skills from job offers
     */
    @GetMapping("mostRequested")
    public List<Reporter> getRequested() {
        return reporterServiceImpl.getRequested();
    }

    /**
     * Not matched by applicant list.
     *
     * @return list of skills that companies requested and no applicant had to offer
     */
    @GetMapping("NotMatchedByApplicant")
    public List<ReportNotMatched> notMatchedByApplicant() {
        return reporterServiceImpl.getNotMatchedByApplicants();
    }
}

