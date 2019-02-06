package recruitment.presentation;

import recruitment.util.Util;

import javax.validation.constraints.*;

class CreatePersonForm {

    @Size(min = 1, max = 30, message = "Name must be longer than 1 character but shorter than 30")
    @Pattern(regexp = "^[\\p{L}\\p{M}*]*$", message = "Only letters are allowed")
    private String firstName;

    @Size(min = 1, max = 30, message = "Name must be longer than 1 character but shorter than 30")
    @Pattern(regexp = "^[\\p{L}\\p{M}*]*$", message = "Only letters are allowed")
    private String lastName;

    @Pattern(regexp = "^[\\p{N}*]*$", message = "Please specify your ssn only with numbers")
    @Size(min=12, max=12, message = "Please specify your ssn with 12 digits")
    private String ssn;

    @NotBlank(message = "Please specify your email")
    @Email
    private String mail;

    @Size(min = 4, max = 15, message = "Please enter a password with min 4 and max 15 characters ")
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
