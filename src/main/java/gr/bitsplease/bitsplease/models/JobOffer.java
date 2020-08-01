package gr.bitsplease.bitsplease.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int jobOfferId;
    @NotNull
    private String companyName;
    @NotNull
    private String titleOfPosition;
    private String edLevel;
    private String region;
    private boolean fulfilled = false;

    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<JobOfferSkills> jobOfferSkills;

    @OneToMany(mappedBy = "jobOffer")
    @JsonIgnore
    @ToString.Exclude
    private List<Match> matches;
}
