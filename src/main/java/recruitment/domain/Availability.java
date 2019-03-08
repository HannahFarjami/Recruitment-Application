package recruitment.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Represents a availability period for a person
 */
@Entity
@Table(name = "Availability")
public class Availability {

    @Id
    @GeneratedValue
    @Column(name="ID",updatable = false)
    private Integer id;

    @NotNull
    @ManyToOne()
    private Person person;

    @NotNull
    @Column(name="from_date")
    private LocalDate fromDate;

    @NotNull
    @Column(name="to_date")
    private LocalDate toDate;

    public Availability() {
    }

    public Availability(@NotNull Person person, @NotNull LocalDate fromDate, @NotNull LocalDate toDate) {
        this.person = person;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
