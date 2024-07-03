package com.wolfhack.service.admin.service;

import com.wolfhack.common.model.dto.OrderDTO;
import com.wolfhack.service.admin.adapter.client.OrderClient;
import com.wolfhack.service.admin.adapter.client.UserClient;
import com.wolfhack.common.model.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

	private final UserClient userClient;

	private final OrderClient orderClient;

	private final KafkaProducerService kafkaProducerService;

	public UserDTO getUserById(Long userId) {
		return userClient.getUserById(userId);
	}

	public List<UserDTO> getAllUsers() {
		return userClient.getAll();
	}

	public OrderDTO getOrderById(Long orderId) {
        return orderClient.getOrderById(orderId);
    }

	public void sendUserCreationEvent(UserDTO userDTO) {
		kafkaProducerService.sendMessage("user-creation-topic", userDTO);
	}

	public void sendOrderCreationEvent(OrderDTO orderDTO) {
        kafkaProducerService.sendMessage("order-creation-topic", orderDTO);
    }

}
