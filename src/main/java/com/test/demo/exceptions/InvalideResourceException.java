package com.test.demo.exceptions;

import java.util.List;

import lombok.Getter;

public class InvalideResourceException extends RuntimeException{

	private ErrorCodes errorCode;
	
	@Getter
	private List<String> errors;

    private static final long serialVersionUID = 1L;

    public InvalideResourceException(String message){
        super(message);
    }
    
    public InvalideResourceException(String message, Throwable cause){
        super(message, cause);
    }
    
    public InvalideResourceException(String message, Throwable cause, ErrorCodes errorCode){
        super(message, cause);
        this.errorCode = errorCode;
    }
    public InvalideResourceException(String message, ErrorCodes errorCode,List<String> errors){
        super(message);
        this.errorCode = errorCode;
        this.errors = errors;
    }
}
