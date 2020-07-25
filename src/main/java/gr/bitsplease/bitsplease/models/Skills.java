package gr.bitsplease.bitsplease.models;


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
    private int skillId;
    private String name;

    @OneToMany(mappedBy = "skills")
    private List<JobOfferSkills> jobOfferSkills;

    @OneToMany(mappedBy = "skills")
    private List<ApplicantSkills> applicantSkills;
}
