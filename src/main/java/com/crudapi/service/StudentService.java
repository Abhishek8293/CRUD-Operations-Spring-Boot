package com.crudapi.service;

import java.util.List;

import com.crudapi.dto.StudentDto;
import com.crudapi.entity.Student;

public interface StudentService {

	Student addStudent(StudentDto studentDto);

	List<Student> findAll();

	Student findByEmail(String email);

	void deleteByEmail(String email);

	Student updateStudent(StudentDto studentDto, String email);

}
