package com.test.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	
	private ErrorCodes errorCode;

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message){
        super(message);
    }
    public ResourceNotFoundException(String message, Throwable cause, ErrorCodes errorCode){
        super(message, cause);
        this.errorCode = errorCode;
    }
    public ResourceNotFoundException(String message, ErrorCodes errorCode){
        super(message);
        this.errorCode = errorCode;
    }
    
}