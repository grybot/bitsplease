package gr.bitsplease.bitsplease.repository;

import gr.bitsplease.bitsplease.dto.OfferedRequestedReport;
import gr.bitsplease.bitsplease.models.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Applicant repository.
 */
@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
    /**
     * Find by first name.
     *
     * @param firstName gets firstName from client
     * @return list of applicants based on the first name
     */
    List<Applicant> findByFirstName(String firstName);

    /**
     * Find by region.
     *
     * @param region get Region from client
     * @return list of applicants based on the region criteria
     */
    List<Applicant> findByRegion(String region);

    /**
     * Query to fetch most offered skills by applicant.
     *
     * @return the list of the most offered skills by applicants
     */
    @Query(nativeQuery =true, value=
            " SELECT 		TOP(20)	 COUNT (skillsId) Frequency , Skills.name Name " +
                    " FROM ApplicantSkills INNER JOIN Skills ON ApplicantSkills.skills_skillsId= Skills.skillsId" +
                    " GROUP BY skills_skillsId,Skills.name ORDER BY Frequency Desc")

    List<OfferedRequestedReport> findOffered();

    List<Applicant> findByEmail(String email);
    List<Applicant>  findByAddress(String address);
    List<Applicant> findByDob(String dob);

}