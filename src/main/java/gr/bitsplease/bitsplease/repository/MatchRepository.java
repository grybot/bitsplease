package gr.bitsplease.bitsplease.repository;
import gr.bitsplease.bitsplease.dto.SurveyAnswerStatistics;
import gr.bitsplease.bitsplease.models.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;
import java.sql.*;
@Repository
public interface MatchRepository extends JpaRepository<Match, UUID> {
//    @Query(nativeQuery = true, value =
//            "  SELECT   applicant_ApplicantId   App,  jobOffer_jobOfferId    Job  " +
//                    "           FROM  Match WHERE percentage =  100")

    @Query(nativeQuery =true,value=
            "SELECT ApplicantSkills.applicant_ApplicantId AppId," +
                    "ApplicantSkills.skills_skillsId AppSkillsId," +
                    "JobOfferSkills.jobOffer_jobOfferId JobOfferSkillsId from ApplicantSkills" +
            "INNER JOIN "+"JobOfferSkills"+ "ON"+" ApplicantSkills.skills_skillsId = JobOfferSkills.skills_skillsId")
    List<SurveyAnswerStatistics> findSurveyCount();
}