package gr.bitsplease.bitsplease.repository;

import gr.bitsplease.bitsplease.models.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Double> {
}
