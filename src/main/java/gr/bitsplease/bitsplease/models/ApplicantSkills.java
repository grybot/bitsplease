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
public class ApplicantSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable=false)
    private String level;

    @ManyToOne
    private Applicant applicant;
    @ManyToOne(cascade = CascadeType.ALL)
    private Skills skills;
}
