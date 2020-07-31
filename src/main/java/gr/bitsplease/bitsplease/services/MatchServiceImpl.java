package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.dto.SurveyAnswerStatistics;
import gr.bitsplease.bitsplease.exceptions.ApplicantException;
import gr.bitsplease.bitsplease.exceptions.JobOfferException;
import gr.bitsplease.bitsplease.exceptions.MatchException;
import gr.bitsplease.bitsplease.models.*;
import gr.bitsplease.bitsplease.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Autowired
    private JobOffersService jobOffersService;
    @Autowired
    private ApplicantService applicantService;



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
            if (match.getFinalisedDate() != null) {
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
        if (match.getFinalisedDate() != null) {
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
        match.getApplicant().setActive(false);
        match.getJobOffer().setFulfilled(true);
        match.setFinalisedDate(LocalDate.now());
        match.setStatus("finalised");
        matchRepository.save(match);
        return true;
    }

    @Override
    public List<SurveyAnswerStatistics> getMatches() {

        List<SurveyAnswerStatistics> list = null;
        list = matchRepository.findSurveyCount();
        return list;
    }
    @Override
    public void initialMatch() throws ApplicantException {
        List<Applicant> ab = applicantRepository.findAll();
        List<JobOffer> jo = jobOfferRepository.findAll();
        for (JobOffer job : jo) {
            int jobid = job.getJobOfferId();
            for (Applicant app : ab) {
                int apid = app.getApplicantId();
                createMatch(apid, jobid);
            }
        }
    }
    @Override
    public void createMatch(int applicantId, int jobOfferId) throws ApplicantException {
        int requiredSkills;
        int matches;
        List<ApplicantSkills> applicantSkillsList;
        List<JobOfferSkills> jobOfferSkillsList = new ArrayList();
        Optional<Applicant> applicantOptional = applicantRepository.findById(applicantId);
        if (applicantOptional.isPresent()) {
            Applicant applicant = applicantOptional.get();
            applicantSkillsList = applicant.getApplicantSkills();
        } else
            throw new ApplicantException("not such applicant");
        Optional<JobOffer> jobOfferOptional = jobOfferRepository.findById(jobOfferId);
        if (jobOfferOptional.isPresent()) {
            JobOffer jobOffer = jobOfferOptional.get();
            jobOfferSkillsList = jobOffer.getJobOfferSkills();
        }
        requiredSkills = jobOfferSkillsList.size();
        System.out.println("jobid" + jobOfferId);
        System.out.println("first req skills" + requiredSkills);
        matches = getMatches(jobOfferSkillsList, applicantSkillsList);
        Match match = new Match();
        Applicant applicant = applicantOptional.get();
        JobOffer jobOffer = jobOfferOptional.get();
        match.setApplicant(applicant);
        match.setJobOffer(jobOffer);
        match.setPercentage(partialPercentage(matches, requiredSkills));
        if (match.getPercentage()!=0)
        matchRepository.save(match);
    }
    @Override
    public int partialPercentage(int matches, int requiredSkills) {
        double p;
        System.out.println(matches);
        System.out.println(requiredSkills);
        p = ((double) matches / requiredSkills) * 100;
        return (int) p;
    }
    @Override
    public int getMatches(List<JobOfferSkills> jobOfferSkillsList, List<ApplicantSkills> applicantSkillsList) {
        List<Skills> jobOfferSkills = jobOfferSkillsList.stream().map(js -> js.getSkills()).collect(Collectors.toList());
        List<Skills> applicantSkills = applicantSkillsList.stream().map(js -> js.getSkills()).collect(Collectors.toList());
        List<Skills> matchList = new ArrayList(jobOfferSkills);
        matchList.retainAll(applicantSkills);
        return matchList.size();
    }
    @Override
    public List<SurveyAnswerStatistics> returnMatching(String type, int percentage) {
        List<SurveyAnswerStatistics> list = null;
        if (type.equals("total")) {
            list = matchRepository.findSurveyCount();
        } else
            list = matchRepository.findPartialCount(percentage);
        return list;
    }
}