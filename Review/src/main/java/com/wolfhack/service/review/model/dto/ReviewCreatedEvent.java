package com.wolfhack.service.review.model.dto;

public record ReviewCreatedEvent(
	Long reviewId,
	Long productId,
	Long userId
) {
}
