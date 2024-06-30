package com.wolfhack.service.user.service;

import com.wolfhack.service.user.config.KafkaTopics;
import com.wolfhack.service.user.logging.annotations.AOPLogging;
import com.wolfhack.service.user.model.domain.User;
import com.wolfhack.service.user.model.dto.UserRegisteredNotificationDTO;
import com.wolfhack.service.user.model.dto.UserResetNotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationSender {

	private final Map<String, KafkaTopics> kafkaTopics;

	private final KafkaTemplate<String, UserRegisteredNotificationDTO> kafkaRegisteredTemplate;

	private final KafkaTemplate<String, User> kafkaResetTemplate;

	@Async
	@AOPLogging
	public void sendRegistration(UserRegisteredNotificationDTO user) {
		Message<UserRegisteredNotificationDTO> message = MessageBuilder
			.withPayload(user)
			.setHeader(
				KafkaHeaders.TOPIC,
				kafkaTopics.get("register").topic()
			)
			.build();

		kafkaRegisteredTemplate.send(message);
	}

	@Async
	@AOPLogging
	public void sendReset(UserResetNotificationDTO user) {
		Message<UserResetNotificationDTO> message = MessageBuilder
			.withPayload(user)
			.setHeader(
				KafkaHeaders.TOPIC,
				kafkaTopics.get("reset").topic()
			)
			.build();

		kafkaResetTemplate.send(message);
	}

}
