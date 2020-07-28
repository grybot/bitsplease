package gr.bitsplease.bitsplease.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
    @Column(nullable=false)
    private String companyName;
    @Column(nullable=false)
    private String titleOfPosition;
    @Column(nullable=false)
    private String edLevel;
    @Column(nullable=false)
    private String region;
    private boolean fulfilled = false;

    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<JobOfferSkills> jobOfferSkills;

    @OneToMany(mappedBy = "jobOffer")
    @JsonIgnore
    private List<Match> matches;
}
