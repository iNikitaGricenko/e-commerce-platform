package com.wolfhack.service.catalog.model.event;

import com.wolfhack.service.catalog.model.dto.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class OrderEvent {

	private Long orderId;

	private List<OrderItem> items;

}
