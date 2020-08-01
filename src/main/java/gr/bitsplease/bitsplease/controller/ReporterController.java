package gr.bitsplease.bitsplease.controller;

import gr.bitsplease.bitsplease.dto.*;
import gr.bitsplease.bitsplease.services.ReporterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ReporterController {
    @Autowired
    ReporterServiceImpl reporterServiceImpl;

    /**
     * Gets offered.
     *
     * @return most offered skills from applicants
     */
    @GetMapping("mostOffered")
    public List<OfferedRequestedReport> getOffered() {
        return reporterServiceImpl.getOffered();
    }

    /**
     * Gets requested.
     *
     * @return the most requested skills from job offers
     */
    @GetMapping("mostRequested")
    public List<OfferedRequestedReport> getRequested() {
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

    /**
     * Gets matching of applicant and job offer based on skills.
     *
     * @return list of matching between applicant and job offer
     */

    @GetMapping("matched")
    public List<MatchedReport> getMatchedReport() {
        return reporterServiceImpl.getMatchedReport();
    }

    /**
     * Gets 20 recent finalised matches
     *
     * @return list of the 20 most recent finalised matches
     */

    @GetMapping("Finalised")
    public List<FinalisedMatches> getFinalisedMatches() {
        return reporterServiceImpl.getFinalisedMatches();
    }

    /**
     * Gets finalised matches for monthly report
     *
     * @return list of finalised matches based on month of finalisedDate for monthly report
     */

    @GetMapping("FinalisedByMonth")
    public List<MatchByMonth> getFinalisedByMonth() {
        return reporterServiceImpl.getFinalisedByMonth();
    }

    /**
     * Gets finalised matches for weekly report
     *
     * @return list of finalised matches based on week of finalisedDate for weekly report
     */
    @GetMapping("FinalisedByWeek")
    public List<MatchByWeek> getFinalisedByWeek() {
        return reporterServiceImpl.getFinalisedByWeek();
    }
}

