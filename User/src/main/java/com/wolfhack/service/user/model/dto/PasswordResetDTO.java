package com.wolfhack.service.user.model.dto;

import jakarta.validation.constraints.*;

import java.io.Serializable;

public record PasswordResetDTO(
	@NotNull
	@Pattern(regexp = "^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$")
	@Email
	@NotEmpty
	@NotBlank
	String email
) implements Serializable { }
