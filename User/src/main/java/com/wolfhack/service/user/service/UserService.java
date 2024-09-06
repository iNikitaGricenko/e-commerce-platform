package com.wolfhack.service.user.service;

import com.wolfhack.common.model.dto.UserDTO;
import com.wolfhack.common.wrapper.DomainPage;
import com.wolfhack.service.user.mapper.UserMapper;
import com.wolfhack.service.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	private final UserMapper userMapper;

	public DomainPage<UserDTO> getPage(Pageable pageable) {
		return new DomainPage<>(
			userRepository.findAll(pageable)
		).map(userMapper::toDTO);
	}

	public List<UserDTO> getAll() {
		return userRepository.findAll()
			.stream()
			.map(userMapper::toDTO)
			.toList();
	}

}
