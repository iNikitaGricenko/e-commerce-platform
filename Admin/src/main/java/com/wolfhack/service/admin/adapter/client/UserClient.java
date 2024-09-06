package com.wolfhack.service.admin.adapter.client;

import com.wolfhack.common.model.dto.UserDTO;
import com.wolfhack.common.wrapper.DomainPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user")
public interface UserClient {

	@RequestMapping(
		method = RequestMethod.GET,
		value = "/api/users",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	DomainPage<UserDTO> getAllUsers();

	@RequestMapping(
		method = RequestMethod.GET,
		value = "/profile/{name}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	UserDTO getUser(@PathVariable("name") String name);

	@RequestMapping(
		method = RequestMethod.POST,
		value = "/register",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	Long saveUser(UserDTO product);

	@RequestMapping(
		method = RequestMethod.PATCH,
		value = "/profile/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	Long partialUpdateUser(@PathVariable("id") Long id, UserDTO user);

	@RequestMapping(
		method = RequestMethod.DELETE,
		value = "/profile/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	void deleteUser(@PathVariable("id") Long id);

}
