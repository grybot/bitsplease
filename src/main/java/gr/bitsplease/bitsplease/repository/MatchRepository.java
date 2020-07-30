package gr.bitsplease.bitsplease.repository;

import gr.bitsplease.bitsplease.dto.SurveyAnswerStatistics;
import gr.bitsplease.bitsplease.models.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MatchRepository extends JpaRepository<Match, UUID> {
    @Query(nativeQuery =true,value=
            "SELECT JobOfferSkills.jobOffer_jobOfferId," + " ApplicantSkills.applicant_ApplicantId AppId FROM JobOfferSkills " +
                    " LEFT JOIN ApplicantSkills " +
                    " ON JobOfferSkills.skills_skillsId = ApplicantSkills.skills_skillsId " +
                    " WHERE JobOfferSkills.jobOffer_jobOfferId " +
                    " NOT IN ( SELECT JobOfferSkills.jobOffer_jobOfferId FROM JobOfferSkills " +
                    " LEFT JOIN ApplicantSkills ON JobOfferSkills.skills_skillsId = ApplicantSkills.skills_skillsId " +
                    " WHERE ApplicantSkills.applicant_ApplicantId IS NULL) " +
                    " GROUP BY JobOfferSkills.jobOffer_jobOfferId, ApplicantSkills.applicant_ApplicantId")
    List<SurveyAnswerStatistics> findSurveyCount();

}

