package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.dto.MatchedReport;
import gr.bitsplease.bitsplease.dto.ReportNotMatched;
import gr.bitsplease.bitsplease.dto.Reporter;
import gr.bitsplease.bitsplease.repository.ApplicantRepository;
import gr.bitsplease.bitsplease.repository.JobOfferRepository;
import gr.bitsplease.bitsplease.repository.JobOfferSkillsRepository;
import gr.bitsplease.bitsplease.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporterServiceImpl implements ReporterService {
    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private JobOfferRepository jobOfferRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public List<Reporter> getOffered() {

        List<Reporter> list;
        return list = applicantRepository.findOffered();
    }

    @Override
    public List<Reporter> getRequested() {
        List<Reporter> list;
        return list =jobOfferRepository.findRequested();
    }

    @Override
    public List<ReportNotMatched> getNotMatchedByApplicants() {
        List<ReportNotMatched> list;
        return list =jobOfferRepository.getNotMatchedByApplicant();
    }

    @Override
    public List<MatchedReport> getMatchedReport() {
        List<MatchedReport> list;
        return list = matchRepository.getMatchedReport();
    }


}
