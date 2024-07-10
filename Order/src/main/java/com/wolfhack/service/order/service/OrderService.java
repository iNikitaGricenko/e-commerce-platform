package com.wolfhack.service.order.service;

import com.wolfhack.service.order.adapter.database.OrderDatabaseAdapter;
import com.wolfhack.service.order.mapper.OrderMapper;
import com.wolfhack.service.order.model.domain.Order;
import com.wolfhack.service.order.model.dto.OrderRequestDTO;
import com.wolfhack.service.order.model.dto.OrderResponseDTO;
import com.wolfhack.service.order.model.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderDatabaseAdapter orderItemDatabaseGateway;

	private KafkaTemplate<String, OrderEvent> kafkaTemplate;

	private OrderMapper orderMapper;

	public Long createOrder(OrderRequestDTO dto) {
		Order order = orderMapper.toModel(dto);

		order.create();

		Long id = orderItemDatabaseGateway.save(order);

		OrderEvent event = new OrderEvent();
		event.setOrderId(id);
		event.setItems(order.getItems());

		kafkaTemplate.send("order-created", event);

		return id;
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
