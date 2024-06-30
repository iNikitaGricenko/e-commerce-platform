package com.wolfhack.service.order.service;

import com.wolfhack.service.order.adapter.database.OrderDatabaseAdapter;
import com.wolfhack.service.order.mapper.OrderMapper;
import com.wolfhack.service.order.model.OrderStatus;
import com.wolfhack.service.order.model.domain.Order;
import com.wolfhack.service.order.model.domain.OrderItem;
import com.wolfhack.service.order.model.dto.OrderRequestDTO;
import com.wolfhack.service.order.model.dto.OrderResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderDatabaseAdapter orderItemDatabaseGateway;

	private OrderMapper orderMapper;

	public Long createOrder(OrderRequestDTO dto) {
		Order order = orderMapper.toModel(dto);

		order.create();

		return orderItemDatabaseGateway.save(order);
	}

    public List<OrderResponseDTO> getOrdersByUserId(Long userId) {
	    return orderItemDatabaseGateway.getByUser(userId)
		    .stream()
		    .map(orderMapper::toResponse)
		    .toList();
    }

    public OrderResponseDTO getOrderById(Long id) {
	    Order order = orderItemDatabaseGateway.getById(id);

	    return orderMapper.toResponse(order);
    }

    public void cancelOrder(Long id) {
        Order order = orderItemDatabaseGateway.getById(id);

        order.cancel();

        orderItemDatabaseGateway.save(order);
    }

}
