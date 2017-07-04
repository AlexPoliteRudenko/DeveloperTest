package net.polite.devtest.exceptions;

import net.polite.devtest.repository.entities.ErrorMessage;

public class UserAlreadyExistsException extends Exception {

    ErrorMessage error;

    public UserAlreadyExistsException(ErrorMessage error) {
        super(error.getDescription());
        this.error = error;
    }

    public ErrorMessage getError() {
        return error;
    }
}
