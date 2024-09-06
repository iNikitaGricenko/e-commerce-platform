package com.wolfhack.service.admin.listener;

import com.wolfhack.service.admin.ui.view.EventBroadcast;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaEventConsumer {

	private final EventBroadcast eventBroadcast;

	@KafkaListener(topicPattern = "events-.*", groupId = "admin-group")
	public void listenEvents(String message) {
		eventBroadcast.broadcast(message);
	}

}
