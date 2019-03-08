package recruitment.presentation;

import recruitment.util.Util;

import javax.validation.constraints.*;

/**
 * Object contains and validates data from the register form on the register page
 */
class CreatePersonForm {

    @Size(min = 1, max = 30, message = "{create-person.first-name.missing}")
    @Pattern(regexp = "^[\\p{L}\\p{M}*]*$", message = "{create-person.first-name.letters}")
    private String firstName;

    @Size(min = 1, max = 30, message = "{create-person.last-name.missing}")
    @Pattern(regexp = "^[\\p{L}\\p{M}*]*$", message = "{create-person.last-name.letters}")
    private String lastName;

    @Pattern(regexp = "^[\\p{N}*]*$", message = "{create-person.ssn.numbers}")
    @Size(min=12, max=12, message = "{create-person.ssn.missing}")
    private String ssn;

    @NotBlank(message = "{create-person.mail.missing}")
    @Email
    private String mail;

    @Size(min = 4, max = 15, message = "{create-person.password}")
    private String password;

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

    @Override
    public String toString() {
        return Util.toString(this);
    }
}
