package com.wolfhack.common.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

public record UserDTO(
	Long id,
	String username,
	String email,
	String role,
	String password,
	String firstName,
	String lastName,
	LocalDate birthDate,
	String phoneNumber,
	String address,
	LocalDate registrationDate,
	LocalDate lastLoginDate
) implements Serializable {}