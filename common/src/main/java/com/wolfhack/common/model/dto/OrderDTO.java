package com.wolfhack.common.model.dto;

public record OrderDTO(
	Long id,
    String productName,
    Integer totalAmount,
    Double totalPrice,
    String status
) { }
