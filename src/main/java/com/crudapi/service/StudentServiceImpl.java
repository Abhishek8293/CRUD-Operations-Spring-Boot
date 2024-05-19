package com.crudapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crudapi.entity.Student;
import com.crudapi.exception.ResourceNotFoundException;
import com.crudapi.exception.StudentCreationException;
import com.crudapi.exception.StudentNotFoundException;
import com.crudapi.repository.StudentRepository;
import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;

	// Constructor Injection does not require @Autowired
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;

	}

	@Override
	public Student addStudent(Student student) {

		if (student.getFirstName() == null || student.getFirstName().isEmpty()) {
			throw new StudentCreationException("First name cannot be empty");
		}
		if (student.getLastName() == null || student.getLastName().isEmpty()) {
			throw new StudentCreationException("Last name cannot be empty");
		}
		if (student.getEmail() == null || student.getEmail().isEmpty()) {
			throw new StudentCreationException("Email cannot be empty");
		}
		if (student.getDateOfBirth() == null || student.getDateOfBirth().isAfter(LocalDate.now())) {
			throw new StudentCreationException("Invalid Date Of Birth");
		}

		return studentRepository.save(student);
	}

	@Override
	public List<Student> findAll() {
		List<Student> studentsList = studentRepository.findAll();
		if (studentsList.isEmpty()) {
			throw new ResourceNotFoundException("No student exists");
		}
		return studentsList;
	}

	@Override
	public Student findByEmail(String email) {
		Optional<Student> existingStudent = studentRepository.findByEmail(email);
		if (existingStudent.isEmpty()) {
			throw new StudentNotFoundException("Requested student does not exists.");
		}

		return existingStudent.get();
	}

	@Override
	@Transactional
	public void deleteByEmail(String email) {
		Optional<Student> existingStudent = studentRepository.findByEmail(email);
		if (existingStudent.isEmpty()) {
			throw new StudentNotFoundException("Requested student does not exists.");
		}
		studentRepository.deleteByEmail(email);
	}

	@Override
	public Student updateStudent(Student student, String email) {

		Optional<Student> existingStudent = studentRepository.findByEmail(email);
		if (existingStudent.isEmpty()) {
			throw new StudentNotFoundException("Requested student does not exists.");
		}
		Student updatedStudent = existingStudent.get();
		updatedStudent.setFirstName(student.getFirstName());
		updatedStudent.setLastName(student.getLastName());
		updatedStudent.setDateOfBirth(student.getDateOfBirth());
		updatedStudent.setEmail(student.getEmail());

		Student savedStudent = studentRepository.save(updatedStudent);
		return savedStudent;

	}

}
