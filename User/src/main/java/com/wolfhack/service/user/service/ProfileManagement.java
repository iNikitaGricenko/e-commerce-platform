package com.wolfhack.service.user.service;

import com.wolfhack.service.user.adapter.database.UserDatabaseAdapter;
import com.wolfhack.service.user.adapter.database.UserTokenDatabaseAdapter;
import com.wolfhack.service.user.exception.ForbiddenException;
import com.wolfhack.service.user.exception.NotFoundException;
import com.wolfhack.service.user.logging.annotations.AOPLogging;
import com.wolfhack.service.user.model.domain.User;
import com.wolfhack.service.user.model.domain.UserToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ProfileManagement {

	private final PBKDF2Encoder encoder;

	private final UserDatabaseAdapter userDatabaseGateway;

	private final UserTokenDatabaseAdapter userTokenDatabaseAdapter;

	@AOPLogging
	public void login(String username, String password) {
		User user = userDatabaseGateway.getByUsername(username);

		if (!encoder.matches(user.getPassword(), password)) {
			throw new ForbiddenException("Invalid password");
		}
	}

	public UserToken login(String token) {
		return userTokenDatabaseAdapter.getByToken(token);
	}

	@AOPLogging
	public void setToken(Long userId, String token) {
		if (userTokenDatabaseAdapter.existsByUserId(userId)) {
			UserToken userToken = userTokenDatabaseAdapter.getByUserId(userId);
			userToken.setToken(token);
			userToken.setExpirationDate(
				LocalDate
					.now()
					.plusYears(1)
			);

			userTokenDatabaseAdapter.partialUpdate(userToken.getId(), userToken);

			return;
		}

		UserToken userToken = new UserToken();
		userToken.setUserId(userId);
		userToken.setToken(token);
		userToken.setExpirationDate(
			LocalDate
				.now()
				.plusYears(1)
		);

		userTokenDatabaseAdapter.save(userToken);
	}

	@AOPLogging
	public void update(Long userId, User user) {
		if (!userDatabaseGateway.exists(userId)) {
			throw new NotFoundException("User does not exist");
		}

		user.setPassword(encoder.encode(user.getPassword()));

		userDatabaseGateway.partialUpdate(userId, user);
	}

	public User getByUsername(String username) {
		return userDatabaseGateway.getByUsername(username);
	}

	public User get(Long userId) {
		return userDatabaseGateway.getById(userId);
	}
}
