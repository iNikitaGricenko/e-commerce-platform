package com.wolfhack.service.admin.model.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItem {

	private Long id;

	private Long productId;

	private Integer totalAmount;

	private BigDecimal unitPrice;

}
