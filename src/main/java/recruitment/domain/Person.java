package recruitment.domain;

import recruitment.util.Util;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PERSON")
public class Person implements PersonDTO {
    private static final String SEQUENCE_NAME_KEY = "SEQ_NAME";


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME_KEY)
    @SequenceGenerator(name = SEQUENCE_NAME_KEY, sequenceName = "PERSON_SEQUENCE", initialValue = 100)
    @Column(name="PERSON_ID", updatable=false)
    private long id;

    @NotNull(message = "{firstName.missing}")
    // The regex below should permit only characters, but asterisk is
    // unfortunately also valid.
    @Pattern(regexp = "^[\\p{L}\\p{M}*]*$", message = "{firstName.invalid-char}")
    @Size(min = 1, max = 30, message = "{firstName.length}")
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull(message = "{lastName.missing}")
    // The regex below should permit only characters, but asterisk is
    // unfortunately also valid.
    @Pattern(regexp = "^[\\p{L}\\p{M}*]*$", message = "{lastName.invalid-char}")
    @Size(min = 1, max = 30, message = "{lastName.length}")
    @Column(name = "LAST_NAME")
    private String lastName;

    @Pattern(regexp = "^[\\p{N}*]*$", message = "{ssn.invalid-number}")
    @Size(min = 12, max = 12, message = "{ssn.length}")
    @Column(name = "SSN")
    private String ssn;

    @Email
    @Column
    private String mail;

    @Size(min = 4, max = 15, message = "{password.length}")
    @Column
    private String password;

    public Person(String firstName, String lastName, String ssn, String mail, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.mail = mail;
        this.password = password;
    }

    public Person() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return Util.toString(this);
    }


}