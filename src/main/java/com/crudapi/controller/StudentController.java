package com.crudapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crudapi.entity.Student;
import com.crudapi.service.StudentServiceImpl;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

	private StudentServiceImpl studentServiceImpl;

	public StudentController(StudentServiceImpl studentServiceImpl) {
		this.studentServiceImpl = studentServiceImpl;
	}

	@PostMapping
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {

		Student savedStudent = this.studentServiceImpl.addStudent(student);
		if (savedStudent == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Student>(savedStudent, HttpStatus.CREATED);
	}

}
