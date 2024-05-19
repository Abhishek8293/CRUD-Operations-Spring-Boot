package com.crudapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crudapi.entity.Student;
import com.crudapi.response.ResponseHandler;
import com.crudapi.service.StudentServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

	private StudentServiceImpl studentServiceImpl;

	public StudentController(StudentServiceImpl studentServiceImpl) {
		this.studentServiceImpl = studentServiceImpl;
	}

	@PostMapping
	public ResponseEntity<Object> addStudent(@Valid @RequestBody Student student) {

		Student savedStudent = studentServiceImpl.addStudent(student);
		return ResponseHandler.responseBuilder("Student is added successfully", HttpStatus.CREATED, savedStudent);
	}

	@GetMapping
	public ResponseEntity<Object> getAllStudents() {

		List<Student> studentList = studentServiceImpl.findAll();
		return ResponseHandler.responseBuilder("List of students", HttpStatus.OK, studentList);
	}

	@GetMapping("/{email}")
	public ResponseEntity<Object> getStudent(@PathVariable String email) {
		Student student = studentServiceImpl.findByEmail(email);
		return ResponseHandler.responseBuilder("Requested Student Details ", HttpStatus.OK, student);
	}

	@DeleteMapping("/{email}")
	public ResponseEntity<Object> deleteStudent(@PathVariable String email) {
		studentServiceImpl.deleteByEmail(email);
		return ResponseHandler.responseBuilder("Requested student is deleted successfully", HttpStatus.OK, null);
	}

	@PutMapping("/{email}")
	public ResponseEntity<Object> updateStudent(@Valid @RequestBody Student student, @PathVariable String email) {
		Student updatedStudent = studentServiceImpl.updateStudent(student, email);
		return ResponseHandler.responseBuilder("Requested student is updated successfully", HttpStatus.OK, updatedStudent);
	}

}
