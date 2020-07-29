package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.Utilities.Checker;
import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.models.*;
import gr.bitsplease.bitsplease.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MatchServiceImpl implements MatchService {
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


    @Override
    public Match manualMatch(int applicantSkillsId, int jobOfferId) throws ApplicantNotFoundException {
        Applicant applicant = applicantRepository
                .findById(applicantSkillsId)
                .orElseThrow(() -> new ApplicantNotFoundException("Applicant Not found"));
        JobOffer jobOffer = jobOfferRepository
                .findById(jobOfferId)
                .orElseThrow(() -> new ApplicantNotFoundException("Job Offer not found"));
        Match match = new Match();
        match.setTypeOfMatching("Manual");
        match.setApplicant(applicant);
        match.setJobOffer(jobOffer);
        matchRepository.save(match);
        return match;
        }

    public List<Match> getMatches() {
        List<ApplicantSkills> allApplicants = applicantSkillsRepository.findAll();
        List<JobOfferSkills> allJobOffers = jobOfferSkillsRepository.findAll();
        List<Match> allMatches = matchRepository.findAll();

        for(ApplicantSkills applicantSkills : allApplicants){
            for(JobOfferSkills jobOfferSkills : allJobOffers){
                if (applicantSkills.getSkills().equals(jobOfferSkills.getSkills()))
                {
                        Match match = new Match();
                        match.setApplicant(applicantSkills.getApplicant());
                        match.setJobOffer(jobOfferSkills.getJobOffer());
                        match.setTypeOfMatching("Automatic");
                        match.setPercentage(100.0);
                        matchRepository.save(match);
                        allMatches.add(match);
                }
            }
        }
        return allMatches;
    }

    @Override
    public Match getMatchById(UUID matchId) throws ApplicantNotFoundException {
        Match match = matchRepository
                .findById(matchId)
                .orElseThrow(() -> new ApplicantNotFoundException("Match ID wasn't associated with any match."));
        return match;
    }

    @Override
    public boolean deleteMatch(UUID matchId) throws ApplicantNotFoundException {
        matchRepository.deleteById(matchId);
        return true;
    }

    @Override
    public Match updateMatch(Match match, UUID matchId) throws ApplicantNotFoundException {
        Match matchToBe = matchRepository.findById(matchId)
                .orElseThrow(() -> new ApplicantNotFoundException("Couldn't find any match with the specified ID."));
        matchToBe.setJobOffer(match.getJobOffer());
        matchToBe.setApplicant(match.getApplicant());
        return matchToBe;
    }

    @Override
    public List<Match> getFinalisedMatches() {
        List<Match> allMatches = matchRepository.findAll();
        List<Match> finalisedMatches = new ArrayList<>();
        for(Match match : allMatches){
            if(match.getStatus() == "final"){
                finalisedMatches.add(match);
            }
        }
        matchRepository.saveAll(finalisedMatches);
        return finalisedMatches;
    }

    @Override
    public Match getFinalisedMatch(UUID matchId) {
        return null;
    }

    @Override
    public boolean finaliseMatch(UUID matchId) {
        List<Match> matches = matchRepository.findAll();
        for(Match match : matches){
            if(match.getMatchId().equals(matchId)){
                match.setFinalised(true);
            }
        }
        return true;
    }
}