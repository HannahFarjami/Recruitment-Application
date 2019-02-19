package recruitment.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "COMPETENCE")
public class Competence {

    @Id
    @GeneratedValue
    @Column(name="ID",updatable = false)
    private Integer id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "competence")
    private Set<CompetenceProfile> competenceProfiles;

    public Competence() {
    }

    public Competence(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
