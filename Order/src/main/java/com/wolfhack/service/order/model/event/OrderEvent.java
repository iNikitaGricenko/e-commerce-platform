package com.wolfhack.service.order.model.event;

import com.wolfhack.service.order.model.domain.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class OrderEvent {

	private Long orderId;

	private List<OrderItem> items;

}
