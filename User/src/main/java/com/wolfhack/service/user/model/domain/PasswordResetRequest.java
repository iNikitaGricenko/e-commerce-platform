package com.wolfhack.service.user.model.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class PasswordResetRequest implements Serializable, DomainModel {
	private Long id;
	private Long userId;
	private String resetToken;
	private LocalDate expirationDate;

	public PasswordResetRequest() { }

	public PasswordResetRequest(Long userId) {
		this.userId = userId;
		resetToken = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		expirationDate = LocalDate.now().plusYears(1);
	}

}