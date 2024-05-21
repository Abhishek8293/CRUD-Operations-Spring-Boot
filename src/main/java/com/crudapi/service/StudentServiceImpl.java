package com.crudapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crudapi.dto.StudentDto;
import com.crudapi.entity.Student;
import com.crudapi.exception.ResourceNotFoundException;
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
	public Student addStudent(StudentDto studentDto) {
		Student student = new Student();
		student.setFirstName(studentDto.getFirstName());
		student.setLastName(studentDto.getLastName());
		student.setDateOfBirth(studentDto.getDateOfBirth());
		student.setEmail(studentDto.getEmail());

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
	public Student updateStudent(StudentDto studentDto, String email) {

		Optional<Student> existingStudent = studentRepository.findByEmail(email);
		if (existingStudent.isEmpty()) {
			throw new StudentNotFoundException("Requested student does not exists.");
		}
		Student updatedStudent = existingStudent.get();
		updatedStudent.setFirstName(studentDto.getFirstName());
		updatedStudent.setLastName(studentDto.getLastName());
		updatedStudent.setDateOfBirth(studentDto.getDateOfBirth());
		updatedStudent.setEmail(studentDto.getEmail());

		Student savedStudent = studentRepository.save(updatedStudent);
		return savedStudent;

	}

}
