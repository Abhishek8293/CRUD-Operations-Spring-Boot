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

	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents() {

		List<Student> studentList = this.studentServiceImpl.findAll();
		if (studentList.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(studentList, HttpStatus.OK);
	}

	@GetMapping("/{email}")
	public ResponseEntity<Student> getStudent(@PathVariable String email) {
		Student student = this.studentServiceImpl.findByEmail(email);
		if (student == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@DeleteMapping("/{email}")
	public ResponseEntity<?> deleteStudent(@PathVariable String email) {
		boolean isDeleted = this.studentServiceImpl.deleteByEmail(email);
		if (isDeleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@PutMapping("/{email}")
	public ResponseEntity<?> updateStudent(@RequestBody Student student, @PathVariable String email) {
		Student updatedStudent = this.studentServiceImpl.updateStudent(student, email);
		if (updatedStudent != null) {
			return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
