package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.models.ApplicantSkills;
import gr.bitsplease.bitsplease.models.JobOfferSkills;
import gr.bitsplease.bitsplease.models.Skills;
import gr.bitsplease.bitsplease.repository.ApplicantSkillsRepository;
import gr.bitsplease.bitsplease.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ApplicantSkillsServiceImpl implements ApplicantSkillsService {
    @Autowired
    private ApplicantSkillsRepository applicantSkillsRepository;
    @Autowired
    private SkillsRepository skillsRepository;

    @Override
    public List<ApplicantSkills> getApplicantSkills() {
        return applicantSkillsRepository.findAll();
    }

}
