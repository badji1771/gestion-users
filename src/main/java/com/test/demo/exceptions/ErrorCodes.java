package com.test.demo.exceptions;

public enum ErrorCodes {

	USER_NOT_FOUND(1000);
	
	
	private int code;
	
	ErrorCodes(int code){
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
}
