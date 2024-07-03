package com.wolfhack.service.admin.service;

import com.wolfhack.common.model.dto.OrderDTO;
import com.wolfhack.service.admin.adapter.client.OrderClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderClient orderClient;

	public List<OrderDTO> getOrders() {
		return orderClient.getOrders();
	}

}
