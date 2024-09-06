package com.wolfhack.common.model.dto;

import java.io.Serializable;

public record OrderDTO(
	Long id,
	String productName,
	Integer totalAmount,
	Double totalPrice,
	String status
) implements Serializable {}
