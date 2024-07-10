package com.wolfhack.service.catalog.model.dto;

public record OrderItem(
	Long productId,
	Integer totalAmount
) {}
