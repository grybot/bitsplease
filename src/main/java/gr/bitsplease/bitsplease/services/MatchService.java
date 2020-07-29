package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.models.Match;

import java.util.List;
import java.util.UUID;

public interface MatchService {
    List<Match> getMatches() throws ApplicantNotFoundException;

    Match getMatchById(UUID matchId) throws ApplicantNotFoundException;

    Match manualMatch(int applicantId, int jobOfferId) throws ApplicantNotFoundException;

    boolean deleteMatch(UUID matchId) throws ApplicantNotFoundException;

    Match updateMatch(Match match, UUID matchId) throws ApplicantNotFoundException;

    List<Match> getFinalisedMatches();

    Match getFinalisedMatch(UUID matchId);

    boolean finaliseMatch(UUID matchId);
}
