package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.exceptions.SkillException;
import gr.bitsplease.bitsplease.models.Applicant;
import gr.bitsplease.bitsplease.models.Skills;
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
class SkillServiceImplTest {
    @Mock
    private SkillsRepository skillsRepository;
    @InjectMocks
    private SkillService skillService= new SkillServiceImpl();

    @Test
    void getSkills() {
        Skills skillTest = new Skills();
        skillTest.setSkillsId(1);
        skillTest.setName("Data Bases");
        List<Skills> skills = Arrays.asList(skillTest);
        when(skillsRepository.findAll()).thenReturn(skills);
        List<Skills> skillsRetrieved = skillsRepository.findAll();
        assertEquals(1, skillsRetrieved.size());
    }

    @Test
    void addSkills() throws SkillException {

        Skills skillTest = new Skills();
        skillTest.setSkillsId(1);
        skillTest.setName("Data Bases");
        when(skillsRepository.save(skillTest)).thenReturn(skillTest);
        when(skillsRepository.findById(1)).thenReturn(Optional.of(skillTest));
        skillService.addSkills(skillTest);
        Optional<Skills> skill = skillsRepository.findById(1);
        assertEquals(1, skill.get().getSkillsId());

        Skills skillTest2 = null;
        Assertions.assertThrows(SkillException.class, () -> {
            skillService.addSkills(skillTest2);
        });


    }

//    @Test
//    void updateSkills() throws SkillException  {
//        Skills skillTest = new Skills();
//        skillTest.setSkillsId(1);
//        skillTest.setName("Data Bases");
//        when(skillsRepository.findById(1)).thenReturn(Optional.of(skillTest));
//        Skills skillTest2 = skillService.updateSkills(skillTest.getSkillsId(), skillTest.getName());
//
//        assertTrue(skillTest2.getName().compareTo(skillTest.getName()));
//
//
//        Skills skillTest3 = null;
//        Assertions.assertThrows(SkillException.class, () -> {
//            skillService.addSkills(skillTest3);
//        });
//
//    }

    @Test
    void deleteSkills() {
        Skills skillTest = new Skills();
        skillTest.setSkillsId(1);
        skillTest.setName("Data Bases");
        when(skillsRepository.save(skillTest)).thenReturn(skillTest);
        when(skillsRepository.findById(1)).thenReturn(Optional.of(skillTest));
        try {
            skillService.deleteSkills(skillTest.getSkillsId());
        } catch (SkillException e) {
            e.printStackTrace();
        }

        Optional<Skills> skill = skillsRepository.findById(1);
        assertEquals(0, skill.get().getSkillsId());

    }

    @Test
    void getSkillById() {
        Skills skillTest = new Skills();
        skillTest.setSkillsId(1);
        skillTest.setName("Data Bases");
        Optional<Skills> optionalSkill = Optional.of(skillTest);
        when(skillsRepository.findById(1)).thenReturn(optionalSkill);
        Optional<Skills> applicant = skillsRepository.findById(1);
        assertEquals(1, applicant.get().getSkillsId());
    }
}