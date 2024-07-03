package com.wolfhack.service.catalog.adapter.client;

import com.wolfhack.common.model.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user")
public interface UserClient {

	@GetMapping("/profile/{id}")
	UserDTO getUserById(@PathVariable("id") Long id);

}
