package gr.bitsplease.bitsplease.repository;
import gr.bitsplease.bitsplease.dto.SurveyAnswerStatistics;
import gr.bitsplease.bitsplease.models.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;
@Repository
public interface MatchRepository extends JpaRepository<Match, UUID> {
    @Query(nativeQuery = true, value =
            "  SELECT   applicant_ApplicantId   App,  jobOffer_jobOfferId    Job  " +
                    "  FROM  Match WHERE percentage =  100 " +
                    " AND " + " status " + " LIKE " + "'Proposed'" +
                    " GROUP BY " + " applicant_ApplicantId, jobOffer_jobOfferId " )
    List<SurveyAnswerStatistics> findSurveyCount();
    @Query(nativeQuery = true, value =
            "  SELECT   applicant_ApplicantId   App,  jobOffer_jobOfferId    Job  " +
                    " FROM " + " Match  " + " WHERE " + " percentage <  100 " + " AND percentage > :percentage "
                    + " AND " + " status " + " LIKE " + "'Proposed'" +
                    " GROUP BY " + " applicant_ApplicantId, jobOffer_jobOfferId ")
    List<SurveyAnswerStatistics> findPartialCount(@Param("percentage") int percentage);
}