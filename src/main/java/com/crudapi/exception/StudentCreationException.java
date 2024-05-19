package com.crudapi.exception;

public class StudentCreationException extends RuntimeException {

	public StudentCreationException(String message, Throwable cause) {
		super(message, cause);
	}

	public StudentCreationException(String message) {
		super(message);
	}

	

}
