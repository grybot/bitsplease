package gr.bitsplease.bitsplease.repository;

import gr.bitsplease.bitsplease.dto.ReportNotMatched;
import gr.bitsplease.bitsplease.dto.OfferedRequestedReport;
import gr.bitsplease.bitsplease.models.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Integer> {
    List<JobOffer> findByCompanyName(String companyName);

    List<JobOffer> findByRegion(String region);

    List<JobOffer> findByDop(String dop);

    @Query(nativeQuery = true, value =
            " SELECT 		TOP(20)	 COUNT (skillsId) Freq , Skills.name Name " +
                    " FROM JobOfferSkills INNER JOIN Skills ON JobOfferSkills.skills_skillsId= Skills.skillsId" +
                    " GROUP BY skills_skillsId,Skills.name ORDER BY Freq Desc")
    List<OfferedRequestedReport> findRequested();

    @Query(nativeQuery = true, value =

            " Select  skills_skillsId  Name,Skills.name From " +
                    " JobOfferSkills  Inner join Skills ON skills_skillsId = Skills.skillsId " +
                    " where " +
                    " skills_skillsId not in ( " +
                    " select skills_skillsId from ApplicantSkills)")
    List<ReportNotMatched> getNotMatchedByApplicant();

}
