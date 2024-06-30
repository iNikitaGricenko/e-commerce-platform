package com.wolfhack.service.user.model.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.wolfhack.service.user.model.domain.User}
 */
@Value
public class UserRegisteredNotificationDTO implements Serializable {

	Long id;

	String email;

	String firstName;

	String lastName;

	LocalDate birthDate;

	String address;

	String phoneNumber;

	public UserRegisteredNotificationDTO(com.wolfhack.service.user.model.domain.User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.birthDate = user.getBirthDate();
		this.address = user.getAddress();
		this.phoneNumber = user.getPhoneNumber();
	}

}