package com.crudapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crudapi.entity.Student;
import com.crudapi.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;

	// Constructor Injection, and in this @Autowired is not necessary.
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
	@Transactional
	public boolean deleteByEmail(String email) {
		Student student = null;
		try {
			student = this.studentRepository.findByEmail(email).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (student != null) {
			this.studentRepository.deleteByEmail(email);
			return true;
		}
		return false;
	}

	@Override
	public Student updateStudent(Student student, String email) {
		Student existingStudent = null;
		try {
			existingStudent = this.studentRepository.findByEmail(email).get();
			existingStudent.setFirstName(student.getFirstName());
			existingStudent.setLastName(student.getLastName());
			existingStudent.setDateOfBirth(student.getDateOfBirth());
			this.studentRepository.save(existingStudent);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return existingStudent;
	}

}
