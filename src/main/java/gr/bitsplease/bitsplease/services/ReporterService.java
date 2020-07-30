package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.dto.ReportNotMatched;
import gr.bitsplease.bitsplease.dto.Reporter;

import java.util.List;

public interface ReporterService {
    List<Reporter> getOffered();

    List<Reporter> getRequested();

    List<ReportNotMatched> getNotMatchedByApplicants();
}
