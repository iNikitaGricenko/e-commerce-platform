package com.wolfhack.service.user.model.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.service.user.model.domain.User}
 */
public record UserProfileEditDTO(
	String username,
	String email,
	String firstName,
	String lastName,
	String phoneNumber,
	String address,
	String password
) implements Serializable { }