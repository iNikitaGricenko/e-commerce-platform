package com.wolfhack.common.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ReviewDTO(
	Long id,
	Long productId,
	Long userId,
	String comment,
	Integer rating,
	LocalDateTime createdAt
) implements Serializable {}