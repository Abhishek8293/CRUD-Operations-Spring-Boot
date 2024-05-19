package com.crudapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentExceptionHandler {

	@ExceptionHandler(value = { StudentNotFoundException.class })
	public ResponseEntity<Object> handleStudentNotFoundException(StudentNotFoundException studentNotFoundException) {
		StudentException studentException = new StudentException(studentNotFoundException.getMessage(),
				studentNotFoundException.getCause(), HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(studentException, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { StudentCreationException.class })
	public ResponseEntity<Object> handleStudentCreationException(StudentCreationException studentCreationException) {
		StudentException studentException = new StudentException(studentCreationException.getMessage(),
				studentCreationException.getCause(), HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(studentException, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = {ResourceNotFoundException.class})
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {

		StudentException studentException = new StudentException(resourceNotFoundException.getMessage(),
				resourceNotFoundException.getCause(), HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(studentException,HttpStatus.NOT_FOUND);
	}
}
