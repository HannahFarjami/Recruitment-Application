package recruitment.presentation.conv;

import recruitment.util.Util;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

class LoginForm {

    @NotBlank(message = "Please specify your email")
    @Email
    private String mail;

    @Size(min = 4, max = 15, message = "Please enter a password with min 4 and max 15 characters ")
    private String password;

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
