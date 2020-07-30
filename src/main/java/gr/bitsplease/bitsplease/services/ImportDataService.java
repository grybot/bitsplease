package gr.bitsplease.bitsplease.services;

import gr.bitsplease.bitsplease.iohelper.Helper;
import gr.bitsplease.bitsplease.models.Applicant;
import gr.bitsplease.bitsplease.models.JobOffer;
import gr.bitsplease.bitsplease.models.Skills;
import gr.bitsplease.bitsplease.repository.ApplicantRepository;
import gr.bitsplease.bitsplease.repository.JobOfferRepository;
import gr.bitsplease.bitsplease.repository.SkillsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImportDataService {
    Logger logger = LoggerFactory.getLogger(ImportDataService.class);
    @Autowired
    ApplicantRepository applicantRepository;

    @Autowired
    SkillsRepository skillsRepository;

    @Autowired
    JobOfferRepository jobOfferRepository;

    public void save(MultipartFile file) {
        try {

            List<Skills> skills = Helper.excelToSkills(file.getInputStream());
            List<Skills> skillsList = skillsRepository.saveAll(skills);

            List<Applicant> applicants = Helper.excelToApplicants(file.getInputStream(), skillsList);
            applicantRepository.saveAll(applicants);


            List<JobOffer> jobOffers = Helper.excelToJobOffers(file.getInputStream(), skillsList);
            jobOfferRepository.saveAll(jobOffers);

        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }


}

