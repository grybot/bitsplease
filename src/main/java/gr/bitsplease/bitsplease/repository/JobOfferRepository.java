package gr.bitsplease.bitsplease.repository;

import gr.bitsplease.bitsplease.models.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobOfferRepository extends JpaRepository<JobOffer, Integer> {
}
