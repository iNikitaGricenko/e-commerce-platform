package com.wolfhack.service.order.model.domain;

import com.wolfhack.common.model.domain.DomainModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderItem implements Serializable, DomainModel {

	private Long id;

	private Long productId;

	private Integer totalAmount;

	private BigDecimal unitPrice;

	private Order order;

}
