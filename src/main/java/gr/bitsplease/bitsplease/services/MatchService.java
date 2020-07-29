package gr.bitsplease.bitsplease.services;

import com.google.common.collect.Multimap;
import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.exceptions.MatchNotFoundException;
import gr.bitsplease.bitsplease.models.Match;

import java.util.List;
import java.util.UUID;
import java.util.zip.ZipFile;

public interface MatchService {
    List<Match> getMatches() throws MatchNotFoundException;

    Match getMatchById(UUID matchId) throws MatchNotFoundException;

    Match manualMatch(int applicantId, int jobOfferId) throws MatchNotFoundException;

    boolean deleteMatch(UUID matchId) throws MatchNotFoundException;

    Match updateMatch(Match match, UUID matchId) throws MatchNotFoundException;

    List<Match> getFinalisedMatches();

    Match getFinalisedMatch(UUID matchId) throws MatchNotFoundException;

    boolean finaliseMatch(UUID matchId) throws MatchNotFoundException;

    List<Match> matchJobOffersWithApplicants() throws MatchNotFoundException;

}
