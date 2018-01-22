package com.vadimrostov.uyutp.security.validation;

public class LoginExistException extends Throwable{

    public LoginExistException(final String message) {
        super(message);
    }
}
