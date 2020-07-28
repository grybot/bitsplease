package gr.bitsplease.bitsplease.repository;

import gr.bitsplease.bitsplease.models.ApplicantSkills;
import gr.bitsplease.bitsplease.models.JobOfferSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicantSkillsRepository extends JpaRepository<ApplicantSkills, Integer> {
    Optional<ApplicantSkills> findApplicantSkillsByApplicantAndSkills(int applicantId, int skillsId);

}
