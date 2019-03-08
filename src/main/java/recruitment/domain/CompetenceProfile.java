package recruitment.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Represents a competence for a person
 */
@Entity
@Table(name = "COMPETENCE_PROFILE")
public class CompetenceProfile {

    @Id
    @GeneratedValue
    @Column(name="ID",updatable = false)
    private Integer id;

    @NotNull
    @ManyToOne
    private Person person;

    @NotNull
    @ManyToOne
    private Competence competence;

    @NotNull
    @Column(name="years_of_experience")
    private float yearsOfExperience;

    public CompetenceProfile() {
    }


}
