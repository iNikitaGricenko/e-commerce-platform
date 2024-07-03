package com.wolfhack.service.admin.adapter.client;

import com.wolfhack.service.admin.config.FeignConfig;
import com.wolfhack.common.model.dto.UserDTO;
import com.wolfhack.service.admin.model.dto.UserCreateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "user", configuration = FeignConfig.class)
public interface UserClient {

	@GetMapping("/api/users/{id}")
	UserDTO getUserById(@PathVariable("id") Long id);

	@GetMapping("/api/users")
	List<UserDTO> getAll();

	@PostMapping("/api/users")
	void createUser(@RequestBody UserCreateDTO user);

}
