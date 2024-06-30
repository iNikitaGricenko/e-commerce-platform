package com.wolfhack.service.notification.listeners;

import com.wolfhack.service.notification.model.MailMessagePOJO;
import com.wolfhack.service.notification.model.UserRegisteredNotificationDTO;
import com.wolfhack.service.notification.model.UserResetNotificationDTO;
import com.wolfhack.service.notification.service.mail.SimpleEmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailListeners {

	private final SimpleEmailSender simpleEmailSender;

	@KafkaListener(
		topics = "user-register",
		groupId = "group-id",
		containerFactory = "userRegisterKafkaListenerContainerFactory"
	)
	public void listenUserRegistration(UserRegisteredNotificationDTO message) {
		String subject = "We are glad, that you are here";
		String body = "%s Welcome to our e-commerce site".formatted(message.firstName());

		MailMessagePOJO mailMessagePOJO = new MailMessagePOJO(message.email(), subject, body);

		simpleEmailSender.send(mailMessagePOJO);
	}

	@KafkaListener(
		topics = "user-reset",
		groupId = "group-id",
		containerFactory = "userResetKafkaListenerContainerFactory"
	)
	public void listenUserResetPassword(UserResetNotificationDTO message) {
		String subject = "Password reset request";

		String body = "%s Go directly by this link and insert your new password localhost:8080/account-management/password/reset/%s"
			.formatted(
				message.username(),
				message.resetToken()
			);

		MailMessagePOJO mailMessagePOJO = new MailMessagePOJO(message.email(), subject, body);

		simpleEmailSender.send(mailMessagePOJO);
	}
}
