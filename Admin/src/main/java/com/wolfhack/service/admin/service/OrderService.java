package com.wolfhack.service.admin.service;

import com.wolfhack.common.wrapper.DomainPage;
import com.wolfhack.service.admin.adapter.client.OrderClient;
import com.wolfhack.service.admin.mapper.OrderMapper;
import com.wolfhack.service.admin.model.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderClient orderClient;

	private final OrderMapper orderMapper;

	public DomainPage<Order> getAllOrders() {
		return orderClient.getAllOrders()
			.map(orderMapper::toModel);
	}

	public Order getOrder(Long id) {
		return orderMapper.toModel(
			orderClient.getOrder(id)
		);
	}

	public void cancelOrder(Long id) {
		orderClient.cancelOrder(id);
	}

}
