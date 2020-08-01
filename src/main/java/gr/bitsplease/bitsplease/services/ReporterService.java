package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.dto.ReportNotMatched;
import gr.bitsplease.bitsplease.dto.Reporter;

import java.util.List;

/**
 * The interface Reporter service.
 */
public interface ReporterService {
    /**
     * Method for the most offered skills by applicants.
     *
     * @return list of most offered skills by applicants.
     */
    List<Reporter> getOffered();

    /**
     * Mathod for the most requested skills by the companies.
     *
     * @return list of the most requested skills by the job offers
     */
    List<Reporter> getRequested();

    /**
     * Mathod for the skills that not applicant matched.
     *
     * @return list of the skills that not applicants matched and was requested by the job offers
     */
    List<ReportNotMatched> getNotMatchedByApplicants();
}
