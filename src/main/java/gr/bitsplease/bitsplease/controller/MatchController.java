package gr.bitsplease.bitsplease.controller;

import gr.bitsplease.bitsplease.dto.SurveyAnswerStatistics;
import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.exceptions.JobOfferNotFoundException;
import gr.bitsplease.bitsplease.exceptions.MatchNotFoundException;
import gr.bitsplease.bitsplease.models.Match;
import gr.bitsplease.bitsplease.services.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
     * @throws MatchNotFoundException     if match is not found by id
     * @throws ApplicantNotFoundException if applicant is not found by id
     * @throws JobOfferNotFoundException  if job offer is not found by id
     */
    @PostMapping("match/{applicantId}/{jobOfferId}")
    public Match manualMatch(@PathVariable int applicantId, @PathVariable int jobOfferId) throws MatchNotFoundException, ApplicantNotFoundException, JobOfferNotFoundException {
        return matchService.manualMatch(applicantId, jobOfferId);
    }

    /**
     * Gets match by id.
     *
     * @param matchId the match id
     * @return match by id
     * @throws MatchNotFoundException if match is not found by id
     */
    @GetMapping("match/{matchId}")
    public Match getMatchById(@PathVariable UUID matchId) throws MatchNotFoundException {
        return matchService.getMatchById(matchId);
    }

    /**
     * Delete match.
     *
     * @param matchId the match id
     * @return if match was deleted(true or false)
     * @throws MatchNotFoundException if match is not found by id
     */
    @DeleteMapping("match/{matchId}")
    public boolean deleteMatch(@PathVariable UUID matchId) throws MatchNotFoundException {
        return matchService.deleteMatch(matchId);
    }

    /**
     * Update match.
     *
     * @param match   match
     * @param matchId matchId
     * @return updated match
     * @throws MatchNotFoundException if match is not found by id
     */
    @PutMapping("match/{matchId}")
    public Match updateMatch(@RequestBody Match match,
                             @PathVariable UUID matchId) throws MatchNotFoundException {
        return matchService.updateMatch(match, matchId);
    }

    /**
     * Get finalised matches list.
     *
     * @return list of finalised matches
     */
    @GetMapping("match/finalised")
    public List<Match> getFinalisedMatches() {
        return matchService.getFinalisedMatches();
    }

    /**
     * Gets finalised match.
     *
     * @param matchId the match id
     * @return finalised match
     * @throws MatchNotFoundException if match is not found by id
     */
    @GetMapping("match/finalised/{matchId}")
    public Match getFinalisedMatch(@PathVariable UUID matchId) throws MatchNotFoundException {
        return matchService.getFinalisedMatch(matchId);
    }

    /**
     * Finalise match.
     *
     * @param matchId match id
     * @return boolean(true if match exists and successfully deleted, if false error will be thrown) boolean
     * @throws MatchNotFoundException if match is not found by id
     */
    @PutMapping("match/finalised/{matchId}")
    public boolean finaliseMatch(@PathVariable UUID matchId) throws MatchNotFoundException {
        return matchService.finaliseMatch(matchId);
    }

    /**
     * Return matching list.
     *
     * @param type       type of match(partial or total)
     * @param percentage the lowest partial match percentage
     * @return list of all matches that match the criteria above
     */
    @GetMapping("matcher/{type}/{percentage}")
    public List<SurveyAnswerStatistics> returnMatching(@PathVariable String type, @PathVariable int percentage) {
        return matchService.returnMatching(type, percentage);
    }

    /**
     * Creates matches.
     */
    @GetMapping("createMatch")
    public void initialMatch() throws ApplicantNotFoundException {
        matchService.initialMatch();
    }

    /**
     * Find match in which appID and jobID are connected.
     *
     * @param applicantId the applicant id
     * @param jobOfferId  the job offer id
     * @return match that is associated with both appID and jobID
     * @throws MatchNotFoundException     if there is no match that connects both appID and jobID
     * @throws JobOfferNotFoundException  if there is no jobOffer associated with the jobID
     * @throws ApplicantNotFoundException if there is not applicant associated with the appID
     */
    @GetMapping("match/{applicantId}/{jobOfferId}")
    public Optional<Match> getMatchByAppIDandJobID(@PathVariable int applicantId, @PathVariable int jobOfferId)
            throws MatchNotFoundException, JobOfferNotFoundException, ApplicantNotFoundException {
        return matchService.getMatchByAppIDandJobID(applicantId, jobOfferId);
    }
}
