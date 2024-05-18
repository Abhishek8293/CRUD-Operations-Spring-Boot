package com.crudapi.service;

import java.util.List;

import com.crudapi.entity.Student;

public interface StudentService {

	Student addStudent(Student student);

	List<Student> findAll();

	Student findByEmail(String email);

	boolean deleteByEmail(String email);

	Student updateStudent(Student student, String email);

}
