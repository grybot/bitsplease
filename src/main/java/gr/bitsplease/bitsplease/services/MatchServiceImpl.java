package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.dto.SurveyAnswerStatistics;
import gr.bitsplease.bitsplease.exceptions.ApplicantException;
import gr.bitsplease.bitsplease.exceptions.JobOfferException;
import gr.bitsplease.bitsplease.exceptions.MatchException;
import gr.bitsplease.bitsplease.models.Applicant;
import gr.bitsplease.bitsplease.models.JobOffer;
import gr.bitsplease.bitsplease.models.Match;
import gr.bitsplease.bitsplease.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MatchServiceImpl implements MatchService {
    Logger logger = LoggerFactory.getLogger(MatchServiceImpl.class);
    @Autowired
    private ApplicantSkillsRepository applicantSkillsRepository;
    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private JobOfferRepository jobOfferRepository;
    @Autowired
    private JobOfferSkillsRepository jobOfferSkillsRepository;
    @Autowired
    private SkillsRepository skillsRepository;
    @Autowired
    private MatchRepository matchRepository;

    private int requiredSkills;
    private int matches;
    private Object ApplicationContextException;


    @Override
    public Match manualMatch(int applicantSkillsId, int jobOfferId) throws MatchException, JobOfferException, ApplicantException {
        Applicant applicant = applicantRepository
                .findById(applicantSkillsId)
                .orElseThrow(() -> new ApplicantException("Applicant Not found"));
        JobOffer jobOffer = jobOfferRepository
                .findById(jobOfferId)
                .orElseThrow(() -> new JobOfferException("Job Offer not found"));
        Match match = new Match();
        match.setTypeOfMatching("Manual");
        match.setApplicant(applicant);
        match.setJobOffer(jobOffer);
        matchRepository.save(match);
        return match;
    }

    @Override
    public Match getMatchById(UUID matchId) throws MatchException {
        Match match = matchRepository
                .findById(matchId)
                .orElseThrow(() -> new MatchException("Match ID wasn't associated with any match."));
        return match;
    }

    @Override
    public boolean deleteMatch(UUID matchId) throws MatchException {
        matchRepository.deleteById(matchId);
        return true;
    }

    @Override
    public Match updateMatch(Match match, UUID matchId) throws MatchException {
        Match matchToBe = matchRepository.findById(matchId)
                .orElseThrow(() -> new MatchException("Couldn't find any match with the specified ID."));
        matchToBe.setJobOffer(match.getJobOffer());
        matchToBe.setApplicant(match.getApplicant());
        return matchToBe;
    }

    @Override
    public List<Match> getFinalisedMatches() {
        List<Match> allMatches = matchRepository.findAll();
        List<Match> finalisedMatches = new ArrayList<>();
        for (Match match : allMatches) {
            if (match.getStatus() == "final") {
                finalisedMatches.add(match);
            }
        }
        matchRepository.saveAll(finalisedMatches);
        return finalisedMatches;
    }

    @Override
    public Match getFinalisedMatch(UUID matchId) throws MatchException {
        Match match = matchRepository
                .findById(matchId)
                .orElseThrow(() -> new MatchException("Could not find any match with this ID."));
        if (getFinalisedMatches().contains(match)) {
            return match;
        } else {
            throw new MatchException("This match is not finalised.");
        }
    }

    @Override
    public boolean finaliseMatch(UUID matchId) throws MatchException {
        Match match = matchRepository
                .findById(matchId)
                .orElseThrow(() -> new MatchException("This ID is not associated with any match"));
        match.setFinalised(true);
        match.getApplicant().setActive(false);
        match.getJobOffer().setFulfilled(true);
        match.setFinalisedDate(LocalDate.now());
        matchRepository.save(match);
        return true;
    }

    @Override
    public List<SurveyAnswerStatistics> getMatches() {

        List<SurveyAnswerStatistics> list = null;
        list = matchRepository.findSurveyCount();
        return list;
    }
}