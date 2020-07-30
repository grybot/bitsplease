package gr.bitsplease.bitsplease.services;
import gr.bitsplease.bitsplease.dto.SurveyAnswerStatistics;
import gr.bitsplease.bitsplease.repository.ApplicantRepository;
import gr.bitsplease.bitsplease.repository.JobOfferRepository;
import gr.bitsplease.bitsplease.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {
    @Autowired
    private JobOffersService jobOffersService;
    @Autowired
    private ApplicantService aplicantService;
    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private JobOfferRepository jobOfferRepository;
    @Autowired
    private MatchRepository matchRepository;

    @Override
    public List<SurveyAnswerStatistics> getMatches() {

        List<SurveyAnswerStatistics> list;
       return list = matchRepository.findSurveyCount();


    }

}