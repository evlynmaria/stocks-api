package com.rbctest.api.stocksapi.v1.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class ErrorInfo {
	
	private Date timestamp;
	private String message;
	private String error_info;

}
