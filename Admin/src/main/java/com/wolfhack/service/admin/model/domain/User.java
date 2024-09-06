package com.wolfhack.service.admin.model.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {

	private Long id;

	private String username;

	private String email;

	private String role;

	private String password;

	private String firstName;

	private String lastName;

	private LocalDate birthDate;

	private String phoneNumber;

	private String address;

	private LocalDate registrationDate;

	private LocalDate lastLoginDate;

}
