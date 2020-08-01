package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.dto.SurveyAnswerStatistics;
import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.exceptions.JobOfferNotFoundException;
import gr.bitsplease.bitsplease.exceptions.MatchNotFoundException;
import gr.bitsplease.bitsplease.models.ApplicantSkills;
import gr.bitsplease.bitsplease.models.JobOfferSkills;
import gr.bitsplease.bitsplease.models.Match;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The interface Match service.
 */
public interface MatchService {

    /**
     * Gets match by id.
     *
     * @param matchId the match id
     * @return the match by id
     * @throws MatchNotFoundException if match is not found by id
     */
    Match getMatchById(UUID matchId) throws MatchNotFoundException;

    /**
     * Manual match applicant with job offer.
     *
     * @param applicantId the applicant id
     * @param jobOfferId  the job offer id
     * @return new match
     * @throws MatchNotFoundException     if match is not found by id
     * @throws JobOfferNotFoundException  if job offer is not found by id
     * @throws ApplicantNotFoundException if applicant is not found by id
     */
    Match manualMatch(int applicantId, int jobOfferId) throws MatchNotFoundException, JobOfferNotFoundException, ApplicantNotFoundException;

    /**
     * Delete match.
     *
     * @param matchId the match id
     * @return boolean(true if match is deleted, false if not) boolean
     * @throws MatchNotFoundException if match is not found by id
     */
    boolean deleteMatch(UUID matchId) throws MatchNotFoundException;

    /**
     * Update match.
     *
     * @param match   the match
     * @param matchId the match id
     * @return updated match
     * @throws MatchNotFoundException if match not found by id
     */
    Match updateMatch(Match match, UUID matchId) throws MatchNotFoundException;

    /**
     * Gets finalised matches.
     *
     * @return list of finalised matches
     */
    List<Match> getFinalisedMatches();

    /**
     * Gets finalised match.
     *
     * @param matchId the match id
     * @return finalised match found by id
     * @throws MatchNotFoundException match is not found by id
     */
    Match getFinalisedMatch(UUID matchId) throws MatchNotFoundException;

    /**
     * Finalise match.
     *
     * @param matchId the match id
     * @return boolean(true if match was finalised, false if not boolean
     * @throws MatchNotFoundException match is not found by id
     */
    boolean finaliseMatch(UUID matchId) throws MatchNotFoundException;

    /**
     * Gets matches.
     *
     * @return all matches from database
     */
    List<SurveyAnswerStatistics> getMatches();

    /**
     * Initial match.
     */
    void initialMatch() throws ApplicantNotFoundException;

    /**
     * Create match.
     *
     * @param applicantId the applicant id
     * @param jobOfferId  the job offer id
     * @throws ApplicantNotFoundException if applicant is not found by id
     */
    void createMatch(int applicantId, int jobOfferId) throws ApplicantNotFoundException;

    /**
     * Partial percentage int.
     *
     * @param matches        the matches
     * @param requiredSkills the required skills
     * @return the int
     */
    int partialPercentage(int matches, int requiredSkills);

    /**
     * Gets matches.
     *
     * @param jobOfferSkillsList  the job offer skills list
     * @param applicantSkillsList the applicant skills list
     * @return the matches
     */
    int getMatches(List<JobOfferSkills> jobOfferSkillsList, List<ApplicantSkills> applicantSkillsList);

    /**
     * Return matching list.
     *
     * @param type       the type
     * @param percentage the percentage
     * @return the list
     */
    List<SurveyAnswerStatistics> returnMatching(String type, int percentage);

    /**
     * Gets match by app i dand job id.
     *
     * @param applicantId the applicant id
     * @param jobOfferId  the job offer id
     * @return the match by app i dand job id
     * @throws ApplicantNotFoundException the applicant exception
     * @throws JobOfferNotFoundException  the job offer exception
     * @throws MatchNotFoundException     the match exception
     */
    Optional<Match> getMatchByAppIDandJobID(int applicantId, int jobOfferId) throws ApplicantNotFoundException, JobOfferNotFoundException, MatchNotFoundException;
}
