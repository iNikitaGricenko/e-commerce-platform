package com.wolfhack.service.admin.service;

import com.wolfhack.service.admin.adapter.client.UserClient;
import com.wolfhack.service.admin.model.dto.UserCreateDTO;
import com.wolfhack.service.admin.model.dto.UserProfileEditDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserClient userClient;

	public void addUser(UserCreateDTO user) {
		userClient.createUser(user);
	}

    public Optional<UserProfileEditDTO> get(Long id) {
        return Optional.empty();
    }

    public List<UserProfileEditDTO> get() {
        return Collections.emptyList();
    }

    public Page<UserProfileEditDTO> get(Pageable pageable) {
        return Page.empty(pageable);
    }

    public UserProfileEditDTO update(UserProfileEditDTO entity) {
        return null;
    }

}
