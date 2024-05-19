package com.crudapi.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_details_table")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	@NotBlank(message = "firstName can't be empty !!")
	private String firstName;

	@NotBlank(message = "lastName can't be empty !!")
	private String lastName;

	@Past(message = "Date can't be of future !!")
	@NotNull
	private LocalDate dateOfBirth;

	@Column(unique = true)
	@Email(message = "Invalid email !!")
	private String email;

	@Transient
	private int age;

}
