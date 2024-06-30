package com.wolfhack.service.user.model.dto;

import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.wolfhack.service.user.model.domain.User}
 */
public record UserRegisterDTO(
	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 5)
	String username,

	@NotNull
	@Pattern(regexp = "^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$")
	@Email
	@NotEmpty
	@NotBlank
	String email,

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 7)
	String password,

	@NotNull
	@NotEmpty
	@NotBlank
	String firstName,

	@NotNull
	@NotEmpty
	@NotBlank
	String lastName,

	@NotNull
	@Past LocalDate birthDate,

	@NotNull
	@Pattern(regexp = "^(\\\\+\\\\d{1,3}( )?)?((\\\\(\\\\d{3}\\\\))|\\\\d{3})[- .]?\\\\d{3}[- .]?\\\\d{4}$" +
		"|^(\\\\+\\\\d{1,3}( )?)?(\\\\d{3} ?){2}\\\\d{3}$" +
		"|^(\\\\+\\\\d{1,3}( )?)?(\\\\d{3} ?)(\\\\d{2} ?){2}\\\\d{2}$")
	@NotEmpty
	@NotBlank
	String phoneNumber,

	String address
) implements Serializable { }