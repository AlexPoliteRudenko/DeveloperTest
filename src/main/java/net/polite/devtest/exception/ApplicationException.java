package net.polite.devtest.exception;

import org.springframework.http.HttpStatus;

public class ApplicationException extends Exception {

    protected String code;
    protected HttpStatus status;

    public String getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ApplicationException(String message) {
        super(message);
    }


}
