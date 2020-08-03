package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.dto.SurveyAnswerStatistics;
import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.exceptions.JobOfferNotFoundException;
import gr.bitsplease.bitsplease.exceptions.MatchNotFoundException;
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
    public Match manualMatch(int applicantSkillsId, int jobOfferId) throws MatchNotFoundException, JobOfferNotFoundException, ApplicantNotFoundException {
        Applicant applicant = applicantRepository
                .findById(applicantSkillsId)
                .orElseThrow(() -> new ApplicantNotFoundException("Applicant Not found"));
        JobOffer jobOffer = jobOfferRepository
                .findById(jobOfferId)
                .orElseThrow(() -> new JobOfferNotFoundException("Job Offer not found"));
        Match match = new Match();
        match.setTypeOfMatching("Manual");
        match.setApplicant(applicant);
        match.setJobOffer(jobOffer);
        matchRepository.save(match);
        return match;
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
            if (match.getFinalisedDate() != null) {
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
                .orElseThrow(() -> new MatchNotFoundException("Could not find any match with this ID."));
        if (match.getFinalisedDate() != null) {
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
    public void initialMatch() throws ApplicantNotFoundException {
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
    public void createMatch(int applicantId, int jobOfferId) throws ApplicantNotFoundException {
        int requiredSkills;
        int matches;
        List<ApplicantSkills> applicantSkillsList;
        List<JobOfferSkills> jobOfferSkillsList = new ArrayList();
        Optional<Applicant> applicantOptional = applicantRepository.findById(applicantId);
        if (applicantOptional.isPresent()) {
            Applicant applicant = applicantOptional.get();
            applicantSkillsList = applicant.getApplicantSkills();
        } else
            throw new ApplicantNotFoundException("not such applicant");
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

    @Override
    public Optional<Match> getMatchByAppIDandJobID(int applicantId, int jobOfferId) throws ApplicantNotFoundException, JobOfferNotFoundException, MatchNotFoundException {
        Applicant applicant = applicantRepository
                .findById(applicantId)
                .orElseThrow(() -> new ApplicantNotFoundException("No applicant is associated with this ID."));
        JobOffer jobOffer = jobOfferRepository
                .findById(jobOfferId)
                .orElseThrow(() -> new JobOfferNotFoundException("No job offer is associated with this ID."));
        Optional<Match> match = matchRepository
                .findAll()
                .stream()
                .filter(po -> po.getApplicant().equals(applicant) && po.getJobOffer().equals(jobOffer))
                .findFirst();
        if (match.isPresent()) {
            return match;
        }else{
            throw new MatchNotFoundException("Couldn't generate any match with the given criteria.");
        }
    }
}