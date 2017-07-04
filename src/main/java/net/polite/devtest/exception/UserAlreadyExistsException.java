package net.polite.devtest.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends ApplicationException {

    public UserAlreadyExistsException() {
        super("A user with the given username already exists", "USER_ALREADY_EXISTS", HttpStatus.CONFLICT);
    }
}
