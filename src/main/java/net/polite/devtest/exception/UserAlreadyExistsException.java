package net.polite.devtest.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends ApplicationException {

    public UserAlreadyExistsException(String message, String code) {
        super(message);
        this.code = code;
        status = HttpStatus.CONFLICT;
    }
}
