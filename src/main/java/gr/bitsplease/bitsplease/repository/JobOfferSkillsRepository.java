package gr.bitsplease.bitsplease.repository;

import gr.bitsplease.bitsplease.models.JobOfferSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOfferSkillsRepository extends JpaRepository<JobOfferSkills, Integer> {
}
