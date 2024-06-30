package com.wolfhack.service.order.model.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

public record OrderItemRequestDTO(
	@NotNull
	Long productId,

	@NotNull
	Integer totalAmount,

	@NotNull
	@DecimalMin(value = "0.99")
	BigDecimal unitPrice
) implements Serializable { }
