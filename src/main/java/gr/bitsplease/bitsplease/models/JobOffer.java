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
public class JobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobOfferId;
    private String companyName;
    private String titleOfPosition;
    private String edLevel;
    private String region;
    private boolean fulfilled;

    @OneToMany(mappedBy = "jobOffer")
    private List<JobOfferSkills> jobOfferSkills;

    @OneToMany(mappedBy = "jobOffer")
    private List<Match> matches;
}
