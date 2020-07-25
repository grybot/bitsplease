package gr.bitsplease.bitsplease.repository;

import gr.bitsplease.bitsplease.models.ApplicantSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantSkillsRepository extends JpaRepository<ApplicantSkills, Integer> {
}
