package com.wolfhack.service.order.model.dto;

import java.io.Serializable;

public record OrderResponseDTO(
	Long id,
	String productName,
	Integer totalAmount,
	Double totalPrice,
	String status
) implements Serializable { }
