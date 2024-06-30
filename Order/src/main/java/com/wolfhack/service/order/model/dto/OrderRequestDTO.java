package com.wolfhack.service.order.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

public record OrderRequestDTO(
	@NotNull
	Long userId,

	@NotBlank
	String shippingAddress,

	@NotEmpty @Valid
	List<OrderItemRequestDTO> items
) implements Serializable { }
