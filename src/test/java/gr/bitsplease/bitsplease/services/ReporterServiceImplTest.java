package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.repository.ApplicantSkillsRepository;
import gr.bitsplease.bitsplease.repository.JobOfferRepository;
import gr.bitsplease.bitsplease.repository.JobOfferSkillsRepository;
import gr.bitsplease.bitsplease.repository.SkillsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class ReporterServiceImplTest {
    @Mock
    JobOfferRepository jobOfferRepository;

    @Mock
    SkillsRepository skillsRepository;

    @Mock
    JobOfferSkillsRepository jobOfferSkillsRepository;

    @Mock
    ApplicantSkillsRepository applicantSkillsRepository;


    @InjectMocks
    ReporterServiceImpl ReporterServiceImpl;
    @Test
    void getOffered() {

    }

    @Test
    void getRequested() {
    }

    @Test
    void getNotMatchedByApplicants() {
    }

    @Test
    void getMatchedReport() {
    }

    @Test
    void getFinalisedMatches() {
    }

    @Test
    void getFinalisedByMonth() {
    }

    @Test
    void getFinalisedByWeek() {
    }
}