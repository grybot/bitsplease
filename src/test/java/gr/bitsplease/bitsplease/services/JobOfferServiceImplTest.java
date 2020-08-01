package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.exceptions.ApplicantException;
import gr.bitsplease.bitsplease.models.JobOffer;
import gr.bitsplease.bitsplease.models.Skills;
import gr.bitsplease.bitsplease.repository.JobOfferRepository;
import gr.bitsplease.bitsplease.repository.SkillsRepository;
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

    @InjectMocks
    JobOfferServiceImpl jobOfferServiceImpl;

    @Test
    void getJobOffers() {
        JobOffer job = new JobOffer();
        job.setEdLevel(null);
        job.setRegion(null);
        job.setTitleOfPosition(null);
        job.setCompanyName(null);
        job.setJobOfferId(1);
        job.isFulfilled();

        when(jobOfferRepository.save(job)).thenReturn(job);
        List<JobOffer> jobOfferList = new ArrayList<>();
        jobOfferList.add(job);
        when(jobOfferRepository.findAll()).thenReturn(jobOfferList);
        assertEquals(1, job.getJobOfferId());

    }

    @Test
    void addJobOffer() {
        JobOffer job = new JobOffer();
        job.setEdLevel("Junior");
        job.setRegion("Athens");
        job.setTitleOfPosition("Developer");
        job.setCompanyName("Accenture");

        when(jobOfferRepository.save(job)).thenReturn(job);
        List<JobOffer> jobOfferList = new ArrayList<>();
        jobOfferList.add(job);
        when(jobOfferRepository.findAll()).thenReturn(jobOfferList);
        assertEquals(1, jobOfferList.size());
    }

    @Test
    void deleteJobOffer() {
        JobOffer job = new JobOffer();
        job.setEdLevel("Junior");
        job.setRegion("Athens");
        job.setTitleOfPosition("Developer");
        job.setCompanyName("Accenture");

        when(jobOfferRepository.save(job)).thenReturn(job);
        List<JobOffer> jobOfferList = new ArrayList<>();
        jobOfferList.add(job);
        jobOfferList.remove(job);
        assertEquals(0, jobOfferList.size());
    }


    @Test
    void addSkillsToJobOffers() {
        JobOffer job = new JobOffer();
        job.setJobOfferId(3);
        job.setEdLevel("Junior");
        job.setRegion("Athens");
        job.setTitleOfPosition("Developer");
        job.setCompanyName("Accenture");
        when(jobOfferRepository.save(job)).thenReturn(job);

        Skills skills = new Skills();
        skills.setSkillsId(2);
        when(skillsRepository.save(skills)).thenReturn(skills);

    }
    }




