package gr.bitsplease.bitsplease.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int skillsId;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skills skills = (Skills) o;
        return Objects.equals(name, skills.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Skills(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "skills")
    @JsonIgnore
    private List<JobOfferSkills> jobOfferSkills;

    @OneToMany(mappedBy = "skills")
    @JsonIgnore
    private List<ApplicantSkills> applicantSkills;
}
