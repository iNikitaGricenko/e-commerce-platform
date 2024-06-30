package com.wolfhack.service.review.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.service.review.model.entity.ReviewEntity}
 */
public record ReviewRequestDTO(
	@NotNull(message = "Product ID Could not be null")
	@Min(message = "Product ID not allowed to be negative or zero", value = 1)
	Long productId,

	@NotNull(message = "User ID Could not be null")
	@Min(message = "User ID not allowed to be negative or zero", value = 1)
	Long userId,

	String comment,

	@PositiveOrZero(message = "Rating should be positive or zero")
	@Range(message = "Rating should be in range of 0-10", min = 0, max = 10)
	Integer rating
) implements Serializable { }