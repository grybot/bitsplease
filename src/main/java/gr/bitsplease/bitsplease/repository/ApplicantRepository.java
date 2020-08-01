package gr.bitsplease.bitsplease.repository;

import gr.bitsplease.bitsplease.dto.Reporter;
import gr.bitsplease.bitsplease.dto.SurveyAnswerStatistics;
import gr.bitsplease.bitsplease.models.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
    List<Applicant> findByFirstName(String firstName);

    List<Applicant> findByRegion(String region);

    List<Applicant> findByEmail(String email);

    List<Applicant>  findByAddress(String address);


    @Query(nativeQuery = true, value =
            " SELECT 		TOP(20)	 COUNT (skillsId) Freq , Skills.name Name " +
                    " FROM ApplicantSkills INNER JOIN Skills ON ApplicantSkills.skills_skillsId= Skills.skillsId" +
                    " GROUP BY skills_skillsId,Skills.name ORDER BY Freq Desc")
    List<Reporter> findOffered();
}