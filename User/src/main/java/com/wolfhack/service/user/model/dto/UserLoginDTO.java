package com.wolfhack.service.user.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.service.user.model.domain.User}
 */
public record UserLoginDTO(
	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 5)
	String username,

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 7)
	String password
) implements Serializable { }
