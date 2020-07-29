package gr.bitsplease.bitsplease.controller;

import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.models.Match;
import gr.bitsplease.bitsplease.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class MatchController {
    @Autowired
    private MatchService matchService;

    @GetMapping("match")
    public List<Match> getMatches() throws ApplicantNotFoundException {
        return matchService.getMatches();
    }
    @PostMapping("match/{applicantId}/{jobOfferId}")
    public Match manualMatch(@PathVariable int applicantId, @PathVariable int jobOfferId) throws ApplicantNotFoundException {
        return matchService.manualMatch(applicantId, jobOfferId);
    }
    @GetMapping("match/{matchId}")
    public Match getMatchById(@PathVariable UUID matchId) throws ApplicantNotFoundException {
        return matchService.getMatchById(matchId);
    }
    @PostMapping("match/delete/{matchId}")
    public boolean deleteMatch(@PathVariable UUID matchId) throws ApplicantNotFoundException{
        return matchService.deleteMatch(matchId);
    }
    @PutMapping("match/{matchId}")
    public Match updateMatch(@RequestBody Match match,
                             @PathVariable UUID matchId) throws ApplicantNotFoundException{
        return matchService.updateMatch(match, matchId);
    }
    @GetMapping("match/finalised")
    public List<Match> getFinalisedMatches(){
        return matchService.getFinalisedMatches();
    }
    @GetMapping("match/finalised/{matchId}")
    public Match getFinalisedMatch(UUID matchId){
        return matchService.getFinalisedMatch(matchId);
    }
    @PutMapping("match/finalised/{matchId}")
    public boolean finaliseMatch(UUID matchId){
        return matchService.finaliseMatch(matchId);
    }
}
