package gr.bitsplease.bitsplease.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "jobOffer",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<JobOfferSkills> jobOfferSkills;

    @OneToMany(mappedBy = "jobOffer")
    @JsonIgnore
    private List<Match> matches;
}
