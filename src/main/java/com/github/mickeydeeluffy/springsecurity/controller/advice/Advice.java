package com.github.mickeydeeluffy.springsecurity.controller.advice;

import com.github.mickeydeeluffy.springsecurity.dto.ErrorResponse;
import com.github.mickeydeeluffy.springsecurity.exception.EntityNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class Advice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
   public ResponseEntity<ErrorResponse> handleAuthorizationException(Exception ex) {
       var error = ErrorResponse.builder()
               .status(HttpStatus.ACCEPTED)
               .name("mickey")
               .title("Error aquired")
               .message("Error occured")
               .build();

       return ResponseEntity
               .status(HttpStatus.UNAUTHORIZED).body(error);
   }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleAuethorizationException(Exception ex) {
        var error = ErrorResponse.builder()
                .status(HttpStatus.ACCEPTED)
                .title("Error aquired")
                .message("Error occured")
                .build();

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception ex) {
        var error = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .title("Cannot find The thing")
                .message(ex.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND).body(error);
    }

}
