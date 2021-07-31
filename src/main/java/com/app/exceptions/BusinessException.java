package com.app.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException{

	private String errorCode;
	private String errorMessage;
	
	public BusinessException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
}
