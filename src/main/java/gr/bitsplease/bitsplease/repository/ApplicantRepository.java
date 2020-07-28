package gr.bitsplease.bitsplease.repository;

import gr.bitsplease.bitsplease.models.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
    List<Applicant> findByFirstName(String firstName);

    List<Applicant> findByRegion(String region);

   // List<Applicant> findBySkillId(int skillId);
}
