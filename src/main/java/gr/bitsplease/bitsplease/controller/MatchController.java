package gr.bitsplease.bitsplease.controller;
import gr.bitsplease.bitsplease.dto.SurveyAnswerStatistics;
import gr.bitsplease.bitsplease.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.List;
import java.util.Map;
@RestController
public class MatchController {
    @Autowired
    MatchService matchService;
    @GetMapping("matcher")
    public List<SurveyAnswerStatistics> matching(){
        return  matchService.getMatches();
    }
}