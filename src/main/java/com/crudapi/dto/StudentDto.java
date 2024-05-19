package com.crudapi.dto;

import java.time.LocalDate;

import jakarta.persistence.Column;
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
public class StudentDto {

	@NotBlank(message = "firstName can't be empty or null !!")
	private String firstName;

	@NotBlank(message = "lastName can't be empty or null !!")
	private String lastName;

	@Past(message = "Date can't be of future date !!")
	@NotNull
	private LocalDate dateOfBirth;

	@Column(unique = true)
	@Email(message = "Invalid email !!")
	private String email;

	@Transient
	private int age;

}
