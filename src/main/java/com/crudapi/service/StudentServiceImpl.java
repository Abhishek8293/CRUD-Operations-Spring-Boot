package com.crudapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crudapi.entity.Student;
import com.crudapi.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;

	}

	@Override
	public Student addStudent(Student student) {
		return this.studentRepository.save(student);
	}

	@Override
	public List<Student> findAll() {
		return this.studentRepository.findAll();
	}

	@Override
	public Student findByEmail(String email) {
		return this.studentRepository.findByEmail(email).get();
	}

	@Override
	public String deleteByEmail(String email) {
		this.studentRepository.ddeleteByEmail(email);
		return "Student " + email + " deleted successfully";

	}

	@Override
	public Student updateStudent(Student student, String email) {
		Student existingStudent = this.studentRepository.findByEmail(email).get();
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setDateOfBirth(student.getDateOfBirth());

		return this.studentRepository.save(existingStudent);
	}

}
