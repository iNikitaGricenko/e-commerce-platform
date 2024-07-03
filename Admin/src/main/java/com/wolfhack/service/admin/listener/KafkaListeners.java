package com.wolfhack.service.admin.listener;

import com.wolfhack.common.model.dto.OrderDTO;
import com.wolfhack.common.model.dto.UserDTO;
import org.springframework.kafka.annotation.KafkaListener;

//@Service
public class KafkaListeners {

	@KafkaListener(topics = "user-topic", groupId = "group-id")
	public void consumeUser(UserDTO userDTO) {

	}

	@KafkaListener(topics = "order-topic", groupId = "group-id")
	public void consumeUser(OrderDTO orderDTO) {

	}

}
