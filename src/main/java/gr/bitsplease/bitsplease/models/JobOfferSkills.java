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
public class JobOfferSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private JobOffer jobOffer;

    @ManyToOne(cascade = CascadeType.ALL)
    private Skills skills;
}
