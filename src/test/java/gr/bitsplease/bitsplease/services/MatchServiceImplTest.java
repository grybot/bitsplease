package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.exceptions.ApplicantNotFoundException;
import gr.bitsplease.bitsplease.exceptions.JobOfferNotFoundException;
import gr.bitsplease.bitsplease.exceptions.MatchNotFoundException;
import gr.bitsplease.bitsplease.models.*;
import gr.bitsplease.bitsplease.repository.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest

class MatchServiceImplTest {


    @Mock
    private ApplicantRepository applicantRepository;
    @Mock
    private SkillsRepository skillsRepository;
    @InjectMocks
    private ApplicantService applicantService = new ApplicantServiceImpl();
    @InjectMocks
    private ApplicantSkillsService applicantSkillsService = new ApplicantSkillsServiceImpl();
    @InjectMocks
    private MatchService matchService = new MatchServiceImpl();
    @Mock
    JobOfferRepository jobOfferRepository;
    @Mock
    MatchRepository matchRepository;


    @Test
    void getFinalisedMatches() {
        Applicant applicantTest = new Applicant();
        applicantTest.setApplicantId(1);
        applicantTest.setFirstName("John");
        applicantTest.setLastName("Matsis");
        applicantTest.setAddress("21 Athinas");
        applicantTest.setRegion("Athens");
        applicantTest.setEdLevel("Computer Science");
        applicantTest.setLevel("Junior");
        applicantTest.setActive(true);
        applicantTest.setEmail("john@gmail.com");
        when(applicantRepository.findById(applicantTest.getApplicantId())).thenReturn(Optional.of(applicantTest));
        JobOffer job = new JobOffer();
        job.setJobOfferId(1);
        job.setEdLevel("Junior");
        job.setRegion("Athens");
        job.setTitleOfPosition("Developer");
        job.setCompanyName("Accenture");
        job.setFulfilled(false);
        when(jobOfferRepository.findById(job.getJobOfferId())).thenReturn(Optional.of(job));
        Match match = new Match();
        match.setTypeOfMatching("Manual");
        match.setApplicant(applicantTest);
        match.setFinalisedDate(LocalDate.now());
        match.setJobOffer(job);
        when(matchRepository.save(match)).thenReturn(match);

        Match match2 = new Match();
        match2.setApplicant(applicantTest);
        match2.setFinalisedDate(LocalDate.now());
        match2.setJobOffer(job);
        match2.setFinalisedDate(LocalDate.now());
        when(matchRepository.save(match2)).thenReturn(match2);

        List<Match> matches = Arrays.asList();
        when(matchRepository.findAll()).thenReturn(matches);
        List<Match> matcher = matches.stream().filter( m -> m.getFinalisedDate()!=null).collect(Collectors.toList());
        List<Match> matchRetrieved = Arrays.asList();
        when(matchService.getFinalisedMatches()).thenReturn(matchRetrieved);
        assertEquals(matcher.size(), matchRetrieved.size());
    }
    @Test
     void getMatchById() throws MatchNotFoundException {
        Applicant applicantTest = new Applicant();
        applicantTest.setApplicantId(1);
        applicantTest.setFirstName("John");
        applicantTest.setLastName("Matsis");
        applicantTest.setAddress("21 Athinas");
        applicantTest.setRegion("Athens");
        applicantTest.setEdLevel("Computer Science");
        applicantTest.setLevel("Junior");
        applicantTest.setActive(true);
        applicantTest.setEmail("john@gmail.com");
        when(applicantRepository.findById(applicantTest.getApplicantId())).thenReturn(Optional.of(applicantTest));
        JobOffer job = new JobOffer();
        job.setJobOfferId(1);
        job.setEdLevel("Junior");
        job.setRegion("Athens");
        job.setTitleOfPosition("Developer");
        job.setCompanyName("Accenture");
        job.setFulfilled(false);
        when(jobOfferRepository.findById(job.getJobOfferId())).thenReturn(Optional.of(job));
        Match match = new Match();
        match.setTypeOfMatching("Manual");
        match.setApplicant(applicantTest);
        match.setJobOffer(job);
        when(matchRepository.save(match)).thenReturn(match);

        UUID matchTest = match.getMatchId();
       Optional<Match> optionalMatch = Optional.of(match);
        when(matchRepository.findById(optionalMatch.get().getMatchId())).thenReturn(optionalMatch);
        Optional<Match> match1 = matchRepository.findById(matchTest);
        assertEquals(optionalMatch.get().getMatchId(), match1.get().getMatchId());


    }




    @Test
    void getMatchByAppIDandJobID() throws MatchNotFoundException, JobOfferNotFoundException, ApplicantNotFoundException {
        Applicant applicantTest = new Applicant();
       applicantTest.setApplicantId(1);
        applicantTest.setFirstName("John");
        applicantTest.setLastName("Matsis");
        applicantTest.setAddress("21 Athinas");
        applicantTest.setRegion("Athens");
        applicantTest.setEdLevel("Computer Science");
        applicantTest.setLevel("Junior");
        applicantTest.setActive(true);
        applicantTest.setEmail("john@gmail.com");
        when(applicantRepository.findById(applicantTest.getApplicantId())).thenReturn(Optional.of(applicantTest));
        JobOffer job = new JobOffer();
        job.setJobOfferId(1);
        job.setEdLevel("Junior");
        job.setRegion("Athens");
        job.setTitleOfPosition("Developer");
        job.setCompanyName("Accenture");
        job.setFulfilled(false);
        when(jobOfferRepository.findById(job.getJobOfferId())).thenReturn(Optional.of(job));
        Match match = new Match();
        match.setTypeOfMatching("Manual");
        match.setApplicant(applicantTest);
        match.setJobOffer(job);
        when(matchRepository.save(match)).thenReturn(match);
        List<Match> matches = Arrays.asList(match);
        when(matchRepository.findAll()).thenReturn(matches);

        Optional<Match> match2 = matchService.getMatchByAppIDandJobID(1,1);
        List<Optional<Match>> retrieveMatches = Arrays.asList(match2);

           assertEquals(matches.size(), retrieveMatches.size());

    }

    @Test
    void manualMatch() throws MatchNotFoundException, JobOfferNotFoundException, ApplicantNotFoundException {
        Applicant applicantTest = new Applicant();
        applicantTest.setApplicantId(1);
        applicantTest.setFirstName("John");
        applicantTest.setLastName("Matsis");
        applicantTest.setAddress("21 Athinas");
        applicantTest.setRegion("Athens");
        applicantTest.setEdLevel("Computer Science");
        applicantTest.setLevel("Junior");
        applicantTest.setActive(true);
        applicantTest.setEmail("john@gmail.com");

        ApplicantSkills applicantSkill = new ApplicantSkills();
        ApplicantSkills applicantSkill2 = new ApplicantSkills();
        JobOfferSkills jobOfferSkill = new JobOfferSkills();
        JobOfferSkills jobOfferSkill12 = new JobOfferSkills();
        Skills skill = new Skills();
        skill.setSkillsId(1);
        Skills skill2 = new Skills();
        skill2.setSkillsId(2);
        applicantSkill.setSkills(skill);
        applicantSkill2.setSkills(skill2);
        List<ApplicantSkills> applicantSkills = new ArrayList<>();
        applicantSkills.add(applicantSkill);
        applicantTest.setApplicantSkills(applicantSkills);

        jobOfferSkill.setSkills(skill);
        jobOfferSkill12.setSkills(skill2);

        List<JobOfferSkills> jobOfferSkills = new ArrayList<>();
        jobOfferSkills.add(jobOfferSkill);
        jobOfferSkills.add(jobOfferSkill12);


        when(applicantRepository.findById(applicantTest.getApplicantId())).thenReturn(Optional.of(applicantTest));
        JobOffer jobOffer = new JobOffer();
        jobOffer.setJobOfferSkills(jobOfferSkills);
        jobOffer.setJobOfferId(1);
        jobOffer.setCompanyName("BCA");
        jobOffer.setRegion("Athens");
        jobOffer.setTitleOfPosition("Product Manager");
        jobOffer.setFulfilled(false);

         when(jobOfferRepository.findById(jobOffer.getJobOfferId())).thenReturn(Optional.of(jobOffer));

        Match match = matchService.manualMatch(1, 1);
        assertEquals(1, match.getApplicant().getApplicantId());
        assertEquals(1, match.getJobOffer().getJobOfferId());

    }
}