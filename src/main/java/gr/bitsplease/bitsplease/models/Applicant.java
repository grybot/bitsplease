package gr.bitsplease.bitsplease.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Applicant {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int ApplicantId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String email;
    @NotNull
    private String edLevel;
    @NotNull
    private String level;
    @NotNull
    private String address;
    @NotNull
    private String region;
    @NotNull
    @Getter
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
