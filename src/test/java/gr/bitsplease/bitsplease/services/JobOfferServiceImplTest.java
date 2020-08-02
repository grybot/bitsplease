package gr.bitsplease.bitsplease.services;

        import gr.bitsplease.bitsplease.exceptions.JobOfferNotFoundException;
        import gr.bitsplease.bitsplease.exceptions.SkillNotFoundException;
        import gr.bitsplease.bitsplease.models.*;
        import gr.bitsplease.bitsplease.repository.JobOfferRepository;
        import gr.bitsplease.bitsplease.repository.JobOfferSkillsRepository;
        import gr.bitsplease.bitsplease.repository.SkillsRepository;
        import org.junit.jupiter.api.Assertions;
        import org.junit.jupiter.api.Test;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.springframework.boot.test.context.SpringBootTest;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Optional;
        import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.when;
@SpringBootTest
class JobOfferServiceImplTest {
    @Mock
    JobOfferRepository jobOfferRepository;
    @Mock
    SkillsRepository skillsRepository;
    @Mock
    JobOfferSkillsRepository jobOfferSkillsRepository;
    @InjectMocks
    JobOfferServiceImpl jobOfferService;
    @Test
    void getJobOffers() {
        JobOffer job = new JobOffer();
        job.setEdLevel("Junior");
        job.setRegion("Athens");
        job.setTitleOfPosition("Junior Developer");
        job.setCompanyName("CompanyAE");
        job.setJobOfferId(1);
        job.setFulfilled(false);
        when(jobOfferRepository.save(job)).thenReturn(job);
        List<JobOffer> jobOfferList = new ArrayList<>();
        jobOfferList.add(job);
        when(jobOfferRepository.findAll()).thenReturn(jobOfferList);
        List<JobOffer> jobOffersRetrieved = jobOfferRepository.findAll();
        assertEquals(1, jobOffersRetrieved.size());
    }
    @Test
    void addJobOffer() {
        JobOffer job = new JobOffer();
        job.setEdLevel("Junior");
        job.setRegion("Athens");
        job.setTitleOfPosition("Developer");
        job.setCompanyName("Accenture");
        job.setFulfilled(false);
        job.setJobOfferId(1);
        when(jobOfferRepository.save(job)).thenReturn(job);
        when(jobOfferRepository.findById(1)).thenReturn(Optional.of(job));
        try {
            jobOfferService.addJobOffer(job);
        } catch (JobOfferNotFoundException e) {
            e.printStackTrace();
        }
        Optional<JobOffer> jobTest = jobOfferRepository.findById(1);
        assertEquals(1, jobTest.get().getJobOfferId());


        JobOffer jobTest3 = null;
        Assertions.assertThrows(JobOfferNotFoundException.class, () -> {
            jobOfferService.addJobOffer(jobTest3);
        });

    }
    @Test
    void deleteJobOffer() {
        JobOffer job = new JobOffer();
        job.setEdLevel("Junior");
        job.setRegion("Athens");
        job.setTitleOfPosition("Developer");
        job.setCompanyName("Accenture");
        job.setFulfilled(false);
        when(jobOfferRepository.save(job)).thenReturn(job);
        List<JobOffer> jobOfferList = new ArrayList<>();
        jobOfferList.add(job);
        jobOfferList.remove(job);
        assertEquals(0, jobOfferList.size());
    }

    @Test
    void addSkillsToJobOffers() {
        JobOffer job = new JobOffer();
        job.setJobOfferId(1);
        job.setEdLevel("Junior");
        job.setRegion("Athens");
        job.setTitleOfPosition("Developer");
        job.setCompanyName("Accenture");
        job.setFulfilled(false);
        Skills skill = new Skills();
        skill.setSkillsId(1);
        skill.setName("Internet Of Things");
        when(jobOfferRepository.findById(1)).thenReturn(Optional.of(job));
        when(skillsRepository.findById(1)).thenReturn(Optional.of(skill));
        JobOfferSkills jobOfferTestSkill = null;
        try {
            try {
                jobOfferTestSkill = jobOfferService.addSkillsToJobOffers(1, 1);
            } catch (JobOfferNotFoundException e) {
                e.printStackTrace();
            }
        } catch (SkillNotFoundException e) {
            e.printStackTrace();
        }
        when(jobOfferSkillsRepository.save(jobOfferTestSkill)).thenReturn(jobOfferTestSkill);
        assertEquals(1, jobOfferTestSkill.getJobOffer().getJobOfferId());
        assertEquals(1, jobOfferTestSkill.getSkills().getSkillsId());


    }
}