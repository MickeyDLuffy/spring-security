package com.github.mickeydeeluffy.springsecurity.controller.advice;

import com.github.mickeydeeluffy.springsecurity.dto.ErrorResponse;
import com.github.mickeydeeluffy.springsecurity.exception.EntityNotFoundException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class Advice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SignatureException.class)
    @ResponseBody
   public ResponseEntity<ErrorResponse> handleAuthorizationException(Exception ex) {

       var error = ErrorResponse.builder()
               .status(HttpStatus.UNAUTHORIZED)
               .title("Authentication Error")
               .message(ex.getMessage())
               .build();
       return ResponseEntity
               .status(HttpStatus.UNAUTHORIZED).body(error);
   }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleAuthorizationException2(ResponseStatusException ex) {
        var error = ErrorResponse.builder()
                .status(ex.getStatus())
                .title("Critical error occured")
                .message(ex.getReason())
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
