package com.wolfhack.service.user.model.dto;

import com.wolfhack.service.user.model.Role;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.wolfhack.service.user.model.domain.User}
 */
public record UserLoginResponseDTO(
	Long id,
	String username,
	String email,
	String phoneNumber,
	Role role,
	LocalDate lastLoginDate
) implements Serializable { }