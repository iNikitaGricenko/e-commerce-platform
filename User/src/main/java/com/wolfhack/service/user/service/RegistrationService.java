package com.wolfhack.service.user.service;

import com.wolfhack.service.user.adapter.database.PasswordResetRequestDatabaseAdapter;
import com.wolfhack.service.user.adapter.database.UserDatabaseAdapter;
import com.wolfhack.service.user.logging.annotations.AOPLogging;
import com.wolfhack.service.user.model.domain.PasswordResetRequest;
import com.wolfhack.service.user.model.domain.User;
import com.wolfhack.service.user.model.dto.UserRegisteredNotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

	private final PBKDF2Encoder pbkdf2Encoder;

	private final UserDatabaseAdapter userDatabaseAdapter;

	private final PasswordResetRequestDatabaseAdapter passwordResetRequestDatabaseAdapter;

	private final NotificationSender notificationSender;

	@AOPLogging
	public long register(User user) {
		user.register(pbkdf2Encoder);

		Long userId = userDatabaseAdapter.save(user);

		PasswordResetRequest passwordResetRequest = new PasswordResetRequest(userId);

		passwordResetRequestDatabaseAdapter.save(passwordResetRequest);

		try {
			return userId;
		} finally {
			UserRegisteredNotificationDTO notificationDTO = new UserRegisteredNotificationDTO(user);

			notificationSender.sendRegistration(notificationDTO);
		}
	}

}
