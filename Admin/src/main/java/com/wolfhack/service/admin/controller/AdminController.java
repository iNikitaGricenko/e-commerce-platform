package com.wolfhack.service.admin.controller;

import com.wolfhack.common.model.dto.OrderDTO;
import com.wolfhack.common.model.dto.UserDTO;
import com.wolfhack.service.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController("/api/admin")
public class AdminController {

	private final AdminService adminService;

	@GetMapping("/users/{id}")
	public UserDTO getUserById(@PathVariable("id") Long id) {
		return adminService.getUserById(id);
	}

	@GetMapping("/orders/{id}")
	public OrderDTO getOrderById(@PathVariable("id") Long id) {
		return adminService.getOrderById(id);
	}

	@PostMapping("/users")
	public void sendUser(@RequestBody UserDTO user) {
		adminService.sendUserCreationEvent(user);
	}

	@PostMapping("/orders")
	public void sendOrder(@RequestBody OrderDTO order) {
		adminService.sendOrderCreationEvent(order);
	}

}
