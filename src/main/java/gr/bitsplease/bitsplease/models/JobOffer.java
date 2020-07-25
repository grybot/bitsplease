package gr.bitsplease.bitsplease.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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


    List<JobOfferSkills> jobOfferSkills;

    List<Match> matches;
}
