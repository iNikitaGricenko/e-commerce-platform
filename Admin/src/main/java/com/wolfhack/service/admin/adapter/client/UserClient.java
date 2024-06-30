package com.wolfhack.service.admin.adapter.client;

import com.wolfhack.service.admin.config.FeignConfig;
import com.wolfhack.common.model.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user", configuration = FeignConfig.class)
public interface UserClient {

	@GetMapping("/users/{id}")
	UserDTO getUserById(@PathVariable("id") Long id);

}
