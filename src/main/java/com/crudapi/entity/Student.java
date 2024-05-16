package com.crudapi.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	
	private String firstName;
	
	private String lastName;
	
	private LocalDate dateOfBirth;
	
	private String email;
	
	private int age;

}
