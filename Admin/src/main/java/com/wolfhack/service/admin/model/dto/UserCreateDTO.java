package com.wolfhack.service.admin.model.dto;

public record UserCreateDTO(
	String name,
	String email,
	String password,
	String passwordConfirm,
	String role,
	boolean active
) {
}
