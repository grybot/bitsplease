package gr.bitsplease.bitsplease.controller;

import gr.bitsplease.bitsplease.dto.SurveyAnswerStatistics;
import gr.bitsplease.bitsplease.exceptions.ApplicantException;
import gr.bitsplease.bitsplease.exceptions.JobOfferException;
import gr.bitsplease.bitsplease.exceptions.MatchException;
import gr.bitsplease.bitsplease.models.Match;
import gr.bitsplease.bitsplease.services.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


/**
 * The type Match controller.
 */
@RestController
public class MatchController {

    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(MatchController.class);
    @Autowired
    private MatchService matchService;


    /**
     * Matching list.
     *
     * @return list of all matches
     */
    @GetMapping("matcher")
    public List<SurveyAnswerStatistics> matching() {
        return matchService.getMatches();
    }


    /**
     * Manual match an applicant with a job offer.
     *
     * @param applicantId the applicant id
     * @param jobOfferId  the job offer id
     * @return match that is associated with applicantId and jobOfferId
     * @throws MatchException     if match is not found by id
     * @throws ApplicantException if applicant is not found by id
     * @throws JobOfferException  if job offer is not found by id
     */
    @PostMapping("match/{applicantId}/{jobOfferId}")
    public Match manualMatch(@PathVariable int applicantId, @PathVariable int jobOfferId) throws MatchException, ApplicantException, JobOfferException {
        return matchService.manualMatch(applicantId, jobOfferId);
    }

    /**
     * Gets match by id.
     *
     * @param matchId the match id
     * @return match by id
     * @throws MatchException if match is not found by id
     */
    @GetMapping("match/{matchId}")
    public Match getMatchById(@PathVariable UUID matchId) throws MatchException {
        return matchService.getMatchById(matchId);
    }

    /**
     * Delete match.
     *
     * @param matchId the match id
     * @return if match was deleted(true or false)
     * @throws MatchException if match is not found by id
     */
    @DeleteMapping("match/{matchId}")
    public boolean deleteMatch(@PathVariable UUID matchId) throws MatchException {
        return matchService.deleteMatch(matchId);
    }

    /**
     * Update match.
     *
     * @param match   match
     * @param matchId matchId
     * @return updated match
     * @throws MatchException if match is not found by id
     */
    @PutMapping("match/{matchId}")
    public Match updateMatch(@RequestBody  Match match,
                             @PathVariable UUID matchId) throws MatchException {
        return matchService.updateMatch(match, matchId);
    }

    /**
     * Get finalised matches list.
     *
     * @return list of finalised matches
     */
    @GetMapping("match/finalised")
    public List<Match> getFinalisedMatches(){
        return matchService.getFinalisedMatches();
    }

    /**
     * Gets finalised match.
     *
     * @param matchId the match id
     * @return finalised match
     * @throws MatchException if match is not found by id
     */
    @GetMapping("match/finalised/{matchId}")
    public Match getFinalisedMatch(@PathVariable UUID matchId) throws MatchException {
        return matchService.getFinalisedMatch(matchId);
    }

    /**
     * Finalise match boolean.
     *
     * @param matchId the match id
     * @return boolean (if match was finalised)
     * @throws MatchException if match is not found by id
     */
    @PutMapping("match/finalised/{matchId}")
    public boolean finaliseMatch(@PathVariable UUID matchId) throws MatchException {
        return matchService.finaliseMatch(matchId);
    }

    /**
     * Logger.
     *
     * @return the logDialog
     */
}
