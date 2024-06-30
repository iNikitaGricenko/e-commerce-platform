package com.wolfhack.service.user.model.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UserToken implements Serializable, DomainModel {
	private Long id;
	private Long userId;
	private String token;
	private LocalDate expirationDate;
}