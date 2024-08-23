package com.wolfhack.service.review.service;

import com.wolfhack.service.review.model.dto.ReviewCreatedEvent;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class EventServiceTest {

	@Mock
	private KafkaTemplate<String, Object> kafkaTemplate;

	@InjectMocks
	private EventService eventService;

	public EventServiceTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSendEvent() {
		String topic = "new-review";
		Object message = new ReviewCreatedEvent(1L, 1L, 1L);

		eventService.sendEvent(topic, message);

		verify(kafkaTemplate, times(1)).send(topic, message);
	}

}