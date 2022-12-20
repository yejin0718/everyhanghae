package com.everyhanghae.board.exception;

import com.everyhanghae.board.controller.BoardController;
import com.everyhanghae.common.exception.ExceptionResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = BoardController.class)
public class BoardExceptionHandler {

	@ResponseStatus(BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ExceptionResponse> handle(IllegalArgumentException e) {
		ExceptionResponse response = new ExceptionResponse(BAD_REQUEST.value(), e.getMessage());
		return new ResponseEntity<>(response, BAD_REQUEST);
	}

	@ResponseStatus(BAD_REQUEST)
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ExceptionResponse> handle(NoSuchElementException e) {
		ExceptionResponse response = new ExceptionResponse(BAD_REQUEST.value(), e.getMessage());
		return new ResponseEntity<>(response, BAD_REQUEST);
	}


}
