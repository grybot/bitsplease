package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.exceptions.MatchNotFoundException;
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

    private int requiredSkills;
    private int matches;
    private Object ApplicationContextException;


    @Override
    public Match manualMatch(int applicantSkillsId, int jobOfferId) throws MatchNotFoundException {
        Applicant applicant = applicantRepository
                .findById(applicantSkillsId)
                .orElseThrow(() -> new MatchNotFoundException("Applicant Not found"));
        JobOffer jobOffer = jobOfferRepository
                .findById(jobOfferId)
                .orElseThrow(() -> new MatchNotFoundException("Job Offer not found"));
        Match match = new Match();
        match.setTypeOfMatching("Manual");
        match.setApplicant(applicant);
        match.setJobOffer(jobOffer);
        matchRepository.save(match);
        return match;
    }

    @Override
    public List<Match> getMatches() {
        return matchRepository.findAll();
    }

    @Override
    public Match getMatchById(UUID matchId) throws MatchNotFoundException {
        Match match = matchRepository
                .findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException("Match ID wasn't associated with any match."));
        return match;
    }

    @Override
    public boolean deleteMatch(UUID matchId) throws MatchNotFoundException {
        matchRepository.deleteById(matchId);
        return true;
    }

    @Override
    public Match updateMatch(Match match, UUID matchId) throws MatchNotFoundException {
        Match matchToBe = matchRepository.findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException("Couldn't find any match with the specified ID."));
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
    public Match getFinalisedMatch(UUID matchId) throws MatchNotFoundException {
        Match match = matchRepository
                .findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException("No match found"));
        if (getFinalisedMatches().contains(match)) {
            return match;
        } else {
            throw new MatchNotFoundException("This match is not finalised.");
        }
    }

    @Override
    public boolean finaliseMatch(UUID matchId) throws MatchNotFoundException {
        Match match = matchRepository
                .findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException("This ID is not associated with any match"));
        match.setFinalised(true);
        match.getApplicant().setActive(false);
        match.getJobOffer().setFulfilled(true);
        matchRepository.save(match);
        return true;
    }


    public boolean matches(int applicantId, int jobOfferId) throws MatchNotFoundException {
        List<ApplicantSkills> applicantSkillsList = new ArrayList();
        List<JobOfferSkills> jobOfferSkillsList = new ArrayList();
        Optional<Applicant> applicantOptional = applicantRepository.findById(applicantId);
        if (applicantOptional.isPresent()) {
            Applicant applicant = applicantOptional.get();
            applicantSkillsList = applicant.getApplicantSkills();
        } else
            throw new MatchNotFoundException("not such applicant");
        Optional<JobOffer> jobOfferOptional = jobOfferRepository.findById(jobOfferId);
        if (jobOfferOptional.isPresent()) {
            JobOffer jobOffer = jobOfferOptional.get();
            jobOfferSkillsList = jobOffer.getJobOfferSkills();
        }
        requiredSkills = jobOfferSkillsList.size();
        matches = getMatches(jobOfferSkillsList, applicantSkillsList);
        if (requiredSkills == matches)
            return true;
        else
            return false;
    }

    public int getMatches(List<JobOfferSkills> jobOfferSkillsList, List<ApplicantSkills> applicantSkillsList) {
        List<Skills> jobOfferSkills = jobOfferSkillsList.stream().map(js -> js.getSkills()).collect(Collectors.toList());
        List<Skills> applicantSkills = applicantSkillsList.stream().map(js -> js.getSkills()).collect(Collectors.toList());
        List<Skills> matchList = new ArrayList(jobOfferSkills);
        matchList.retainAll(applicantSkills);
        return matchList.size();
    }

    public List<Match> matchJobOffersWithApplicants() throws MatchNotFoundException {
        List<Applicant> ab = applicantRepository.findAll();
        List<JobOffer> jo = jobOfferRepository.findAll();
        List<Match> allMatches = new ArrayList<>();
        for (JobOffer job : jo) {
            int jobid = job.getJobOfferId();
            for (Applicant app : ab) {
                int apid = app.getApplicantId();
                if (matches(apid, jobid) && app.getLevel().equals(job.getEdLevel())) {
                    Match match = new Match();
                    match.setApplicant(app);
                    match.setJobOffer(job);
                    match.setPercentage(100);
                    match.setTypeOfMatching("Automatic");
                    match.setStatus("Proposed");
                    matchRepository.save(match);
                    allMatches.add(match);
                }
            }
        }
        return allMatches;
    }
}