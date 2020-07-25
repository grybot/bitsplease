package gr.bitsplease.bitsplease.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int skillsId;
    private String name;

    @OneToMany(mappedBy = "skills")
    @JsonIgnore
    private List<JobOfferSkills> jobOfferSkills;

    @OneToMany(mappedBy = "skills")
    @JsonIgnore
    private List<ApplicantSkills> applicantSkills;
}
