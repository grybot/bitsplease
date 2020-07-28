package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.models.*;
import gr.bitsplease.bitsplease.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
        return null;
    }

    public List<Match> getMatches() {
        List<Match> allMatches = new ArrayList<>();
        List<ApplicantSkills> allApplicants = applicantSkillsRepository.findAll();
        List<JobOfferSkills> allJobOffers = jobOfferSkillsRepository.findAll();

        for(ApplicantSkills applicantSkills : allApplicants){
            for(JobOfferSkills jobOfferSkills : allJobOffers){
                if (applicantSkills.getSkills().equals(jobOfferSkills.getSkills()))
                {
                    Match match = new Match();
                    match.setApplicant(applicantSkills.getApplicant());
                    match.setJobOffer(jobOfferSkills.getJobOffer());
                    matchRepository.save(match);
                    allMatches.add(match);
                }
            }
        }
        return allMatches;
    }

    @Override
    public Match getMatchById(UUID matchId) {
        return null;
    }

    @Override
    public boolean deleteMatch(UUID matchId) {
        return false;
    }

    @Override
    public Match updateMatch(Match match) {
        return null;
    }

    @Override
    public List<Match> getfinalisedMatches() {
        return null;
    }

    @Override
    public Match getFinalisedMatch(UUID matchId) {
        return null;
    }

    @Override
    public boolean finaliseMatch(UUID matchId) {
        return false;
    }
}