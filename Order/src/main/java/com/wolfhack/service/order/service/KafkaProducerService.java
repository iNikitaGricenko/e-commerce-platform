package com.wolfhack.service.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

	private final KafkaTemplate<String, Object> kafkaTemplate;

	public void sendOrderEvent(String topic, Object messageObject) {
		kafkaTemplate.send(topic, messageObject);
	}

}
