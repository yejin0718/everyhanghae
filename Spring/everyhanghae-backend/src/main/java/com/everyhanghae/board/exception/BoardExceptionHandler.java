package com.everyhanghae.board.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.everyhanghae.board.controller.BoardController;
import com.everyhanghae.common.exception.ExceptionMessage;
import com.everyhanghae.common.exception.ExceptionResponse;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = BoardController.class)
public class BoardExceptionHandler {

	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<ExceptionResponse> handleBadRequest(MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();
		String errorMessage = bindingResult.getFieldError().getDefaultMessage();
		ExceptionMessage exceptionMessage = ExceptionMessage.of(errorMessage);

		ExceptionResponse exceptionResponse;
		if (exceptionMessage == null) {
			exceptionResponse = new ExceptionResponse(BAD_REQUEST.value(), e.getMessage());
		} else {
			exceptionResponse = new ExceptionResponse(exceptionMessage);
		}

		return new ResponseEntity<>(exceptionResponse, BAD_REQUEST);
	}

	@ResponseStatus(BAD_REQUEST)
	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<ExceptionResponse> handleBadRequest(IllegalArgumentException e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(BAD_REQUEST.value(), e.getMessage());
		return new ResponseEntity<>(exceptionResponse, BAD_REQUEST);
	}


}
