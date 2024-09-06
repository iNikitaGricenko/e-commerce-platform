package com.wolfhack.service.admin.model.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Order {

	private Long id;

	private Long userId;

	private String orderStatus;

	private Long paymentId;

	private String shippingAddress;

	private LocalDate createdDate;

	private LocalDate updatedDate;

}
