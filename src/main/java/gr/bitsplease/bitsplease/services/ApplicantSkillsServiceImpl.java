package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.models.ApplicantSkills;
import gr.bitsplease.bitsplease.repository.ApplicantSkillsRepository;
import gr.bitsplease.bitsplease.repository.SkillsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * The type Applicant skills service.
 */
public class ApplicantSkillsServiceImpl implements ApplicantSkillsService {
    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(ApplicantSkillsServiceImpl.class);
    @Autowired
    private ApplicantSkillsRepository applicantSkillsRepository;
    @Autowired
    private SkillsRepository skillsRepository;

    @Override
    public List<ApplicantSkills> getApplicantSkills() {
        return applicantSkillsRepository.findAll();
    }

}
