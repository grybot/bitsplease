package gr.bitsplease.bitsplease.repository;

import gr.bitsplease.bitsplease.models.JobOfferSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobOfferSkillsRepository extends JpaRepository<JobOfferSkills, Integer> {

    Optional<JobOfferSkills> findJobOfferSkillByJobOfferAndSkills(int jobOfferId, int skillsId);
}
