package com.wolfhack.service.user.model.domain;

import com.wolfhack.service.user.model.Role;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class User implements Serializable, DomainModel {
	private Long id;
	private String username;
	private String email;
	private Role role;
	private String password;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String phoneNumber;
	private String address;
	private LocalDate registrationDate;
	private LocalDate lastLoginDate;

	public void register(PasswordEncoder encoder) {
		this.password = encoder.encode(password);
		role = Role.CUSTOMER;
		registrationDate = LocalDate.now();
	}
}