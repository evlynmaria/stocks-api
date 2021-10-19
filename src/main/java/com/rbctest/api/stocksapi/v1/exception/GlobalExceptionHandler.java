/**
 * 
 */
package com.rbctest.api.stocksapi.v1.exception;

import java.text.ParseException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import org.springframework.http.MediaType;

/**
 * This class is to handle the exceptions globally
 *
 */
@ControllerAdvice  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE) 
public class GlobalExceptionHandler {
	// specific exceptions
	@ExceptionHandler(ResourceNotFound.class)
	 @ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleResourceNotFound(ResourceNotFound exception, WebRequest request) {
		ErrorInfo error = new ErrorInfo(new Date(), exception.getMessage(), request.getDescription(false));

		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}

	// Generic exceptions
	@ExceptionHandler(Exception.class)
	 @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> globalExceptionHandling(Exception exception, WebRequest request) {
		ErrorInfo error = new ErrorInfo(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ParseException.class)	
	 @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> globalExceptionHandling(ParseException exception, WebRequest request) {
		ErrorInfo error = new ErrorInfo(new Date(), "Date format must be dd/mm/yyyy", request.getDescription(false));
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	 @ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<Object> handleCustomValidationExceptions(MethodArgumentNotValidException ex) {
		ErrorInfo error = new ErrorInfo(new Date(), "Validation Error",
				ex.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}
}
