package com.crudapi.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {

		StudentException studentException = new StudentException(resourceNotFoundException.getMessage(),
				resourceNotFoundException.getCause(), HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(studentException, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<Object> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException methodArgumentNotValidException) {
		Map<String, String> responseMap = new HashMap<>();
		List<ObjectError> erroList = methodArgumentNotValidException.getBindingResult().getAllErrors();
		for (ObjectError error : erroList) {
			String fieldName = ((FieldError) error).getField();
			String messageString = error.getDefaultMessage();
			responseMap.put(fieldName, messageString);
		}

		return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
	}
}
