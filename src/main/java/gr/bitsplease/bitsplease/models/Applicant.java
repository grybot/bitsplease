package gr.bitsplease.bitsplease.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Applicant {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long ApplicantId;
    private String firstName;
    private String lastName;
    private String email;
    private String edLevel;
    private String level;
    private String address;
    private String region;

    @OneToMany(mappedBy = "applicant")
    private List<ApplicantSkills> applicantSkills;

    @OneToMany(mappedBy = "applicant")
    private List<Match> matches;
}
