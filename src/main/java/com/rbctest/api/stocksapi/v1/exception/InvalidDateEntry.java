package com.rbctest.api.stocksapi.v1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidDateEntry extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidDateEntry(String message) {
		super(message);
	}
}