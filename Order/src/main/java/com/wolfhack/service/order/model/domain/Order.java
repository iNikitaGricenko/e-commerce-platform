package com.wolfhack.service.order.model.domain;

import com.wolfhack.common.model.domain.DomainModel;
import com.wolfhack.service.order.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable, DomainModel {

	private Long id;

	private Long userId;

	private OrderStatus orderStatus;

	private Long paymentId;

	private String shippingAddress;

	private LocalDate createdDate;

	private LocalDate updatedDate;

	private List<OrderItem> items;

	public void create() {
		this.orderStatus = OrderStatus.PACKAGING;
		this.createdDate = LocalDate.now();
	}

	public void cancel() {
		this.orderStatus = OrderStatus.CANCELED;
		this.updatedDate = LocalDate.now();
	}

}