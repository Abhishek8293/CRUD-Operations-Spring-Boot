package com.crudapi.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentException {

	private final String message;
	private final Throwable throwable;
	private final HttpStatus httpStatus;

	public StudentException(String message, Throwable throwable, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.throwable = throwable;
		this.httpStatus = httpStatus;
	}

}
