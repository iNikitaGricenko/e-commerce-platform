package com.wolfhack.service.admin.model.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Review {

	private Long id;

	private Long productId;

	private Long userId;

	private String comment;

	private Integer rating;

	private LocalDateTime createdAt;

}
