package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.dto.OfferedRequestedReport;
import gr.bitsplease.bitsplease.models.Applicant;
import gr.bitsplease.bitsplease.models.ApplicantSkills;
import gr.bitsplease.bitsplease.models.Skills;
import gr.bitsplease.bitsplease.repository.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class ReporterServiceImplTest {
    @Mock
    JobOfferRepository jobOfferRepository;

    @Mock
    SkillsRepository skillsRepository;

    @Mock
    JobOfferSkillsRepository jobOfferSkillsRepository;

    @Mock
    ApplicantSkillsRepository applicantSkillsRepository;

    @Mock
    ApplicantRepository applicantRepository;

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    ReporterServiceImpl ReporterServiceImpl;
    private Object Skills;

    @Test
    void getOffered() {


                ApplicantSkills applicantSkills = new ApplicantSkills();
                Skills skills = new Skills();
                skills.setSkillsId(7);
                applicantSkills.setId(8);
                List<OfferedRequestedReport> offeredRequestedReports =applicantRepository.findOffered();
                System.out.println(offeredRequestedReports.get(0).getFreq());
            System.out.println(offeredRequestedReports.get(0).getName());

                assertNotNull(offeredRequestedReports);
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