package com.everyhanghae.user.exception;

import com.everyhanghae.common.exception.ExceptionResponse;
import com.everyhanghae.user.controller.UserController;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = UserController.class)
public class UserExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handle(IllegalArgumentException e) {
        String errorMessage = e.getMessage();
        ExceptionResponse response = new ExceptionResponse(BAD_REQUEST.value(), errorMessage);

        return new ResponseEntity<>(response, BAD_REQUEST);
    }



}
