package com.vadimrostov.uyutp.web.dto;

import com.vadimrostov.uyutp.security.validation.ValidEmail;
import com.vadimrostov.uyutp.security.validation.ValidPassword;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

    @NotNull
   // @Size(min = 2, message = "{Size.userDto.login}")
    private String login;

    @ValidPassword
    private String password;

    @NotNull
    @Size(min = 1)
    private String matchingPassword;

    @ValidEmail
    @NotNull
    @Size(min = 1, message = "{Size.userDto.email}")
    private String email;

    private boolean makeadmin;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isMakeadmin() {
        return makeadmin;
    }

    public void setMakeadmin(boolean makeadmin) {
        this.makeadmin = makeadmin;
    }
}
