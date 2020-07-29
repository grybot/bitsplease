package gr.bitsplease.bitsplease.controller;

import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.exceptions.MatchNotFoundException;
import gr.bitsplease.bitsplease.models.Match;
import gr.bitsplease.bitsplease.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class MatchController {
    @Autowired
    private MatchService matchService;

    @GetMapping("match")
    public List<Match> getMatches() throws MatchNotFoundException {
        return matchService.matchJobOffersWithApplicants();
    }
    @PostMapping("match/{applicantId}/{jobOfferId}")
    public Match manualMatch(@PathVariable int applicantId, @PathVariable int jobOfferId) throws MatchNotFoundException {
        return matchService.manualMatch(applicantId, jobOfferId);
    }
    @GetMapping("match/{matchId}")
    public Match getMatchById(@PathVariable UUID matchId) throws MatchNotFoundException {
        return matchService.getMatchById(matchId);
    }
    @DeleteMapping("match/{matchId}")
    public boolean deleteMatch(@PathVariable UUID matchId) throws MatchNotFoundException{
        return matchService.deleteMatch(matchId);
    }
    @PutMapping("match/{matchId}")
    public Match updateMatch(@RequestBody  Match match,
                             @PathVariable UUID matchId) throws MatchNotFoundException{
        return matchService.updateMatch(match, matchId);
    }
    @GetMapping("match/finalised")
    public List<Match> getFinalisedMatches(){
        return matchService.getFinalisedMatches();
    }
    @GetMapping("match/finalised/{matchId}")
    public Match getFinalisedMatch(@PathVariable UUID matchId) throws MatchNotFoundException {
        return matchService.getFinalisedMatch(matchId);
    }
    @PutMapping("match/finalised/{matchId}")
    public boolean finaliseMatch(@PathVariable UUID matchId) throws MatchNotFoundException {
        return matchService.finaliseMatch(matchId);
    }
}
