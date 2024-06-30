package com.wolfhack.service.notification.listeners;

import com.wolfhack.service.notification.model.MulticastMessagePOJO;
import com.wolfhack.service.notification.model.SubscriptionPOJO;
import com.wolfhack.service.notification.model.TopicMessagePOJO;
import com.wolfhack.service.notification.service.notification.NotificationSender;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationListener {

	private final NotificationSender messaging;

	@KafkaListener(
		topics = "single-message",
		groupId = "group-id",
		containerFactory = "topicMessageKafkaListenerContainerFactory"
	)
	public void listenSingleMessage(TopicMessagePOJO message) {
		messaging.send(message);
	}

	@KafkaListener(
		topics = "multiple-message",
		groupId = "group-id",
		containerFactory = "multicastMessageKafkaListenerContainerFactory"
	)
	public void listenMultipleMessage(MulticastMessagePOJO message) {
		messaging.send(message);
	}

	@KafkaListener(
		topics = "subscribe",
		groupId = "group-id",
		containerFactory = "subscriptionKafkaListenerContainerFactory"
	)
	public void subscribeToFCM(SubscriptionPOJO subscription) {
		messaging.subscribe(subscription);
	}
}
