package com.wolfhack.service.review.adapter.client;

import com.wolfhack.common.model.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "user")
public interface UserClient {

	@RequestMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	UserDTO getUserById(@PathVariable("id") Long id);

}
