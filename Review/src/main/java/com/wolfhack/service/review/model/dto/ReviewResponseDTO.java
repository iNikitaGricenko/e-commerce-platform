package com.wolfhack.service.review.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.wolfhack.service.review.model.entity.ReviewEntity}
 */
public record ReviewResponseDTO(
	Long id,
	Long productId,
	Long userId,
	String comment,
	Integer rating,
	LocalDateTime createdAt
) implements Serializable { }