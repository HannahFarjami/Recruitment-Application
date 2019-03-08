package recruitment.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Represents the roles that a Person can have.
 */
@Entity
@Table(name = "ROLE")
public class Role {

    @Id
    @GeneratedValue
    @Column(name="ID",updatable = false)
    private Integer id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<Person> persons;

    public Role() {
    }

    public Role(String name) {
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

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}
