package com.wolfhack.service.notification.service.notification;

import com.google.firebase.messaging.*;
import com.wolfhack.service.notification.annotations.aop.AspectLog;
import com.wolfhack.service.notification.model.MessagePOJO;
import com.wolfhack.service.notification.model.MulticastMessagePOJO;
import com.wolfhack.service.notification.model.SubscriptionPOJO;
import com.wolfhack.service.notification.model.TopicMessagePOJO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationSender implements INotificationSender {

	private final FirebaseMessaging firebaseMessaging;

	@Override
	@AspectLog
	public String send(MessagePOJO message) {
		Message firebaseMessage = Message.builder()
				.setToken(message.registrationToken())
				.putData("body", message.message())
				.build();

		try {
			return firebaseMessaging.send(firebaseMessage);
		} catch (FirebaseMessagingException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	@AspectLog
	public String send(TopicMessagePOJO message) {
		Message firebaseMessage = Message.builder()
				.setTopic(message.topic())
				.putData("body", message.message())
				.build();

		try {
			return firebaseMessaging.send(firebaseMessage);
		} catch (FirebaseMessagingException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	@AspectLog
	public List<String> send(MulticastMessagePOJO message) {
		MulticastMessage firebaseMessage = MulticastMessage.builder()
				.addAllTokens(message.registrationTokens())
				.putData("body", message.message())
				.build();

		try {
			return firebaseMessaging.sendEachForMulticast(firebaseMessage).getResponses().stream()
					.map(SendResponse::getMessageId)
					.toList();
		} catch (FirebaseMessagingException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	@AspectLog
	public void subscribe(SubscriptionPOJO subscription) {
		try {
			firebaseMessaging.subscribeToTopic(List.of(subscription.registrationToken()), subscription.topic());
		} catch (FirebaseMessagingException exception) {
			throw new RuntimeException(exception);
		}
	}

}
