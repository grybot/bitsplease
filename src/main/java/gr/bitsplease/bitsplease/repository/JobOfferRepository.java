package gr.bitsplease.bitsplease.repository;

import gr.bitsplease.bitsplease.dto.ReportNotMatched;
import gr.bitsplease.bitsplease.dto.OfferedRequestedReport;
import gr.bitsplease.bitsplease.models.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Job offer repository.
 */
@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Integer> {
    /**
     * Find by company name.
     *
     * @param companyName client selects company name.
     * @return list of job offers that matched the given company name
     */
    List<JobOffer> findByCompanyName(String companyName);

    /**
     * Gets list of all job offers in a given region.
     *
     * @param region clients selects region
     * @return list of all job offers in a selected region
     */
    List<JobOffer> findByRegion(String region);

    /**
     * Gets most requested skills by the companies.
     *
     * @return list of the most requested skills from companies
     */
    @Query(nativeQuery =true, value=
            " SELECT 		TOP(20)	 COUNT (skillsId) Frequency , Skills.name Name " +
                    " FROM JobOfferSkills INNER JOIN Skills ON JobOfferSkills.skills_skillsId= Skills.skillsId" +
                    " GROUP BY skills_skillsId,Skills.name ORDER BY Frequency Desc")

    List<OfferedRequestedReport> findRequested();

    /**
     * Gets not matched skills of the applicants from the job offers.
     *
     * @return list of skill that no applicant had to offer
     */
    @Query(nativeQuery = true , value =

            " Select  skills_skillsId  Name,Skills.name From " +
                    " JobOfferSkills  Inner join Skills ON skills_skillsId = Skills.skillsId " +
                    " where " +
                    " skills_skillsId not in ( " +
                    " select skills_skillsId from ApplicantSkills)" )

    List<ReportNotMatched> getNotMatchedByApplicant();

    List<JobOffer> findByDop(String dop);

}