package com.wolfhack.service.admin.model.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserProfileEditDTO {

	private Long Id;

	private String username;

	@Email
	private String email;

	private String firstName;

	private String lastName;

	private String phoneNumber;

	private String address;

	private String password;

}
