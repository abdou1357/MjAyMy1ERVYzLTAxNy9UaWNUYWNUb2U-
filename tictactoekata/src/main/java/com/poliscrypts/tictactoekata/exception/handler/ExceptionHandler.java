package com.poliscrypts.tictactoekata.exception.handler;

import com.poliscrypts.tictactoekata.exception.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<Error> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(errorMessage(HttpStatus.BAD_REQUEST, ex, request));
    }

    private Error errorMessage(HttpStatus httpStatus, IllegalArgumentException ex, WebRequest request) {
        var error = Error.builder()
                .status(httpStatus)
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .build();
        return error;
    }
}
