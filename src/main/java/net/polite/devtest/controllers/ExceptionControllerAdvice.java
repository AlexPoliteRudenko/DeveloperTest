package net.polite.devtest.controllers;

import net.polite.devtest.exceptions.UserAlreadyExistsException;
import net.polite.devtest.repository.entities.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(Exception ex) {
        ErrorMessage error = new ErrorMessage("INTERNAL_SERVER_ERROR", "Please contact your administrator");
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> UserAlreadyExistsExceptionHandler(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getError(), HttpStatus.CONFLICT);
    }
}
