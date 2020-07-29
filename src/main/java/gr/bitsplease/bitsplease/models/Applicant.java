package gr.bitsplease.bitsplease.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Applicant {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int ApplicantId;
    private String firstName;
    private String lastName;
    private String email;
    private String edLevel;
    private String level;
    private String address;
    private String region;
    private boolean active = true;





    @OneToMany(mappedBy = "applicant" ,cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<ApplicantSkills> applicantSkills;
    @OneToMany(mappedBy = "applicant")
    @JsonIgnore
    @ToString.Exclude
    private List<Match> matches;
}
