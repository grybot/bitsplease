package gr.bitsplease.bitsplease.services;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
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

    private int matches;


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
    @Override
    public List<Match> getMatches() {
        return matchRepository.findAll();
    }

    @Override
    public Multimap<Integer, Integer> matchJobOffersWithApplicants() throws ApplicantNotFoundException {
        Multimap<Integer, Integer> matches = ArrayListMultimap.create();
        List<Applicant> ab= applicantRepository.findAll();
        List<JobOffer> jo= jobOfferRepository.findAll();
        for (JobOffer job : jo) {
            int jobid = job.getJobOfferId();
            for (Applicant app : ab) {
                int apid = app.getApplicantId();
                if(Matcher(apid, jobid)) matches.put(jobid, apid);
            }
        }
        return matches;
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
    public Match getFinalisedMatch(UUID matchId) throws ApplicantNotFoundException {
        Match match = matchRepository
                .findById(matchId)
                .orElseThrow(() -> new ApplicantNotFoundException("No match found"));
        if (getFinalisedMatches().contains(match)) {
            return match;
        }else{
            throw new ApplicantNotFoundException("This match is not finalised.");
        }
    }

    @Override
    public boolean finaliseMatch(UUID matchId) {
        List<Match> matches = matchRepository.findAll();
        for(Match match : matches){
            if(match.getMatchId().equals(matchId)){
                match.setFinalised(true);
                match.getApplicant().setActive(false);
                match.getJobOffer().setFulfilled(true);
            }
        }
        return true;
    }



    public boolean Matcher(int applicantId, int jobOfferId) throws ApplicantNotFoundException {
        int requiredSkills;
        int matches;
        List<ApplicantSkills> applicantSkillsList = new ArrayList();
        List<JobOfferSkills> jobOfferSkillsList = new ArrayList();
        Optional<Applicant> applicantOptional = applicantRepository.findById(applicantId);
        if(applicantOptional.isPresent()){
            Applicant applicant = applicantOptional.get();
            applicantSkillsList=applicant.getApplicantSkills();
        }
        else
            throw new ApplicantNotFoundException("not such applicant");
        Optional<JobOffer> jobOfferOptional = jobOfferRepository.findById(jobOfferId);
        if(jobOfferOptional.isPresent()) {
            JobOffer jobOffer =jobOfferOptional.get();
            jobOfferSkillsList = jobOffer.getJobOfferSkills();
        }
        requiredSkills=jobOfferSkillsList.size();
        matches=getMatches(jobOfferSkillsList,applicantSkillsList);
        if (requiredSkills==matches)
            return true;
        else
            return false;
    }
    public static int getMatches(List<JobOfferSkills> jobOfferSkillsList, List<ApplicantSkills> applicantSkillsList){
        List<Skills> jobOfferSkills = jobOfferSkillsList
                .stream()
                .map(js -> js.getSkills())
                .collect(Collectors.toList());

        List<Skills> applicantSkills = applicantSkillsList
                .stream()
                .map(js -> js.getSkills())
                .collect(Collectors.toList());

        List<Skills> matchList = new ArrayList(jobOfferSkills);
        matchList.retainAll(applicantSkills);
        return matchList.size();
    }
}