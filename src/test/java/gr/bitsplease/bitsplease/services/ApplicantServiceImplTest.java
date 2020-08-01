package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.exceptions.ApplicantException;
import gr.bitsplease.bitsplease.exceptions.SkillException;
import gr.bitsplease.bitsplease.models.Applicant;
import gr.bitsplease.bitsplease.models.ApplicantSkills;
import gr.bitsplease.bitsplease.models.Skills;
import gr.bitsplease.bitsplease.repository.ApplicantRepository;
import gr.bitsplease.bitsplease.repository.ApplicantSkillsRepository;
import gr.bitsplease.bitsplease.repository.SkillsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class ApplicantServiceImplTest {


    @Mock
    private ApplicantSkillsRepository applicantSkillsRepository;
    @Mock
    private ApplicantRepository applicantRepository;
    @Mock
    private SkillsRepository skillsRepository;
    @InjectMocks
    private ApplicantService applicantService = new ApplicantServiceImpl();
    @InjectMocks
    private ApplicantSkillsService applicantSkillsService = new ApplicantSkillsServiceImpl();

    @Test
    void getApplicants() {
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
        List<Applicant> applicants = Arrays.asList(applicantTest);
        when(applicantRepository.findAll()).thenReturn(applicants);
        List<Applicant> applicantsRetrieved = applicantRepository.findAll();
        assertEquals(1, applicantsRetrieved.size());
    }

    @Test
    void getApplicantById()throws ApplicantException {
        Applicant applicantTest = new Applicant();
        applicantTest.setFirstName("John");
        applicantTest.setApplicantId(1);
        applicantTest.setLastName("Matsis");
        applicantTest.setRegion("Patras");
        applicantTest.setEdLevel("IT Sector");
        applicantTest.setAddress("5 Aretha ");
        applicantTest.setLevel("Mid");
        applicantTest.setActive(true);
        Optional<Applicant> optionalApplicant = Optional.of(applicantTest);
        when(applicantRepository.findById(1)).thenReturn(optionalApplicant);
        Optional<Applicant> applicant = applicantRepository.findById(1);
        assertEquals(1, applicant.get().getApplicantId());
    }

    @Test
    void addApplicant() {
        Applicant applicantTest = new Applicant();
        applicantTest.setFirstName("John");
        applicantTest.setApplicantId(1);
        applicantTest.setLastName("Matsis");
        applicantTest.setRegion("Patras");
        applicantTest.setEdLevel("IT Sector");
        applicantTest.setAddress("5 Aretha ");
        applicantTest.setLevel("Mid");
        applicantTest.setActive(true);
        when(applicantRepository.save(applicantTest)).thenReturn(applicantTest);
        when(applicantRepository.findById(1)).thenReturn(Optional.of(applicantTest));
        try {
            applicantService.addApplicant(applicantTest);
        } catch (ApplicantException e) {
            e.printStackTrace();
        }
        Optional<Applicant> applicant = applicantRepository.findById(1);
        assertEquals(1, applicant.get().getApplicantId());

        Applicant applicantTest2 = null;
        Assertions.assertThrows(ApplicantException.class, () -> {
                    applicantService.addApplicant(applicantTest2);
                });

        Applicant applicantTest3 =  new Applicant();
        applicantTest3.setEmail("johnPapas");
        Assertions.assertThrows(ApplicantException.class, () -> {
            applicantService.addApplicant(applicantTest3);
        });
    }

    @Test
    void updateApplicant() throws ApplicantException {
        Applicant applicant = new Applicant();
        applicant.setApplicantId(1);
        applicant.setFirstName("Elenh");
        applicant.setLastName("Tsakiri");
        applicant.setActive(true);
        applicant.setRegion("Patras");
        applicant.setAddress("4 Ampelokhpwn");
        applicant.setLevel("Junior");
        when(applicantRepository.findById(1)).thenReturn(Optional.of(applicant));
        Applicant applicantInbB = applicantService.updateApplicant(applicant.getApplicantId(), applicant.getFirstName(),
                applicant.getLastName(), applicant.getEmail(), applicant.isActive(),
        applicant.getLevel(),applicant.getAddress(),applicant.getRegion());
        assertTrue(applicantInbB.isActive());
        applicant.setActive(false);
        applicantInbB  = applicantService.updateApplicant(applicantInbB.getApplicantId(), applicantInbB.getFirstName(),
                applicantInbB.getLastName(), applicantInbB.getEmail(), applicantInbB.isActive(),
                applicantInbB.getLevel(),applicantInbB.getAddress(),applicantInbB.getRegion());
        assertFalse(applicantInbB.isActive());

    }

    @Test
    void addSkillsToApplicant() {
        Applicant applicantTest = new Applicant();
        applicantTest.setFirstName("John");
        applicantTest.setApplicantId(1);
        applicantTest.setLastName("Matsis");
        applicantTest.setRegion("Patras");
        applicantTest.setEdLevel("IT Sector");
        applicantTest.setAddress("5 Aretha ");
        applicantTest.setLevel("Mid");
        applicantTest.setActive(true);
        Skills skill = new Skills();
        skill.setSkillsId(1);
        skill.setName("Internet Of Things");
        when(applicantRepository.findById(1)).thenReturn(Optional.of(applicantTest));
        when(skillsRepository.findById(1)).thenReturn(Optional.of(skill));
        ApplicantSkills applicantTestSkill = null;
        try {
            applicantTestSkill = applicantService.addSkillsToApplicant(1, 1);
        } catch (ApplicantException e) {
            e.printStackTrace();
        } catch (SkillException e) {
            e.printStackTrace();
        }
        when(applicantSkillsRepository.save(applicantTestSkill)).thenReturn(applicantTestSkill);
        assertEquals(1, applicantTestSkill.getApplicant().getApplicantId());
        assertEquals(1, applicantTestSkill.getSkills().getSkillsId());

    }


}
