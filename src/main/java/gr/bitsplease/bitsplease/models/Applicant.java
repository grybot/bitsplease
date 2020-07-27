package gr.bitsplease.bitsplease.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

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
    @Column(nullable=false)
    private String firstName;
    @Column(nullable=false)
    private String lastName;
    //@Column(nullable=false)
    //private String email;
    @Column(nullable=false)
    private String edLevel;
    @Column(nullable=false)
    private String level;
    @Column(nullable=false)
    private String address;
    @Column(nullable=false)
    private String region;





    @OneToMany(mappedBy = "applicant" ,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ApplicantSkills> applicantSkills;
    @OneToMany(mappedBy = "applicant")
    @JsonIgnore
    private List<Match> matches;
}
