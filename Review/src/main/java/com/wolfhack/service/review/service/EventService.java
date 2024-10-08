package com.wolfhack.service.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

	private final KafkaTemplate<String, Object> kafkaTemplate;

	public void sendEvent(String topic, Object messageObject) {
		kafkaTemplate.send(topic, messageObject);
	}

}
