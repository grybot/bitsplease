package gr.bitsplease.bitsplease.controller;
import gr.bitsplease.bitsplease.dto.SurveyAnswerStatistics;
import gr.bitsplease.bitsplease.services.MatchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatchController {
    @Autowired
    MatchServiceImpl matchServiceImpl;

    @GetMapping("matcher")
    public List<SurveyAnswerStatistics> matching(){
        return  matchServiceImpl.getMatches();
    }
}