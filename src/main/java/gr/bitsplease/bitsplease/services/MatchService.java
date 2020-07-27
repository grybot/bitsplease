package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.models.Match;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface MatchService {
    List<Match> getMatches();

    Match getMatchById(UUID matchId);

    Match manualMatch(int applicantId, int jobOfferId);

    boolean deleteMatch(UUID matchId);

    Match updateMatch(Match match);

    List<Match> getfinalisedMatches();

    Match getFinalisedMatch(UUID matchId);

    boolean finaliseMatch(UUID matchId);


}
