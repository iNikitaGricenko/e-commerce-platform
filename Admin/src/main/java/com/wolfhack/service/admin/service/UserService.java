package com.wolfhack.service.admin.service;

import com.wolfhack.service.admin.adapter.client.UserClient;
import com.wolfhack.service.admin.mapper.UserMapper;
import com.wolfhack.service.admin.model.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserClient userClient;

	private final UserMapper userMapper;

	public List<User> getAllUsers() {
		return userClient.getAllUsers()
			.getContent()
			.stream()
			.map(userMapper::toModel)
			.toList();
	}

	public User getUser(String name) {
		return userMapper.toModel(
			userClient.getUser(name)
		);
	}

	public Long saveUser(User user) {
		return userClient.saveUser(
			userMapper.toDTO(user)
		);
	}

	public Long partialUpdateUser(Long id, User user) {
		return userClient.partialUpdateUser(
			id, userMapper.toDTO(user)
		);
	}

	public void deleteUser(Long id) {
		userClient.deleteUser(id);
	}


}
