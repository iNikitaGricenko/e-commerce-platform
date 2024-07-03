package com.wolfhack.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class OrderDTO {

	private Long id;

	private String productName;

	private Integer totalAmount;

	private Double totalPrice;

	private String status;

}
