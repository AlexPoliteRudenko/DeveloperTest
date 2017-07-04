package net.polite.devtest.exception;

import org.springframework.http.HttpStatus;

public class ApplicationException extends Exception {

    private String code;
    private HttpStatus status;

    public String getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ApplicationException(String message, String code, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }


}
