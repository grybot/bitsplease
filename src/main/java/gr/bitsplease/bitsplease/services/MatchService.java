package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.dto.SurveyAnswerStatistics;

import java.util.List;

public interface MatchService {
    List<SurveyAnswerStatistics> getMatches();
}
