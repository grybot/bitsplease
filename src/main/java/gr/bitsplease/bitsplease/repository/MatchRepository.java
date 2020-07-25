package gr.bitsplease.bitsplease.repository;

import gr.bitsplease.bitsplease.models.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MatchRepository extends JpaRepository<Match, UUID> {
}
