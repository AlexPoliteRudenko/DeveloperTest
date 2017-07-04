package net.polite.devtest.controller;

import net.polite.devtest.entity.ErrorMessage;
import net.polite.devtest.exception.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(Exception ex) {
        ErrorMessage error = new ErrorMessage("INTERNAL_SERVER_ERROR", "Please contact your administrator");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorMessage> applicationExceptionHandler(ApplicationException ex) {
        return new ResponseEntity<>(new ErrorMessage(ex.getCode(), ex.getMessage()), ex.getStatus());
    }
}
