package gr.bitsplease.bitsplease.repository;

import gr.bitsplease.bitsplease.dto.*;
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
    /**
     * Query to fetch the  potential matches between applicant and job offer.
     *
     * @return the list of the matches between applicant and job offer
     */
    @Query(nativeQuery = true, value =
            " SELECT typeOfMatching TypeOfMatching , applicant_ApplicantId ApplicantID ,jobOffer_jobOfferId JobOfferId" +
                    " FROM Match ")
    List<MatchedReport> getMatchedReport();
    /**
     * Query to fetch 20 recent finalised matches.
     *
     * @return the list of the 20 recent finalised matches
     */
    @Query(nativeQuery = true, value =
            " SELECT TOP (20) applicant_ApplicantId ApplicantId ,jobOffer_jobOfferId JobOfferId, Match.finalisedDate FinalisedDate" +
                    " From " + " Match " +
                    " Where status is not null Order By Match.finalisedDate ")
    List<FinalisedMatches> getFinalisedMatches();
    /**
     * Query to fetch finalised matches for monthly report.
     *
     * @return the list of finalised matches based on month
     */
    @Query(nativeQuery = true, value = " SELECT count( [Match].matchId) Frequency , { fn MONTHNAME ( [Match].finalisedDate ) }  MonthName " +
            "            FROM [Match] " +
            "             WHERE status ='finalised' " +
            "             GROUP BY { fn MONTHNAME ( [Match].finalisedDate ) }")
    List<MatchByMonth> getFinalisedByMonth();
    /**
     * Query to fetch finalised matches for weekly report.
     *
     * @return the list of finalised matches based on week
     */
    @Query(nativeQuery = true, value = " SELECT count ( Match.matchId )  Frequency ,  {fn WEEK ( Match.finalisedDate ) }   Week" +
            "            FROM Match " +
            "             WHERE status ='finalised' " +
            "             GROUP BY { fn WEEK ( Match.finalisedDate )}    ")
    List<MatchByWeek> getFinalisedByWeek();
}