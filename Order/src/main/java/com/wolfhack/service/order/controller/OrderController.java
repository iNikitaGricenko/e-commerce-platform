package com.wolfhack.service.order.controller;

import com.wolfhack.service.order.model.dto.OrderRequestDTO;
import com.wolfhack.service.order.model.dto.OrderResponseDTO;
import com.wolfhack.service.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController("/api/orders")
public class OrderController {

	private final OrderService orderService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long createOrder(@Valid @RequestBody OrderRequestDTO dto) {
		return orderService.createOrder(dto);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public OrderResponseDTO getOrderById(@PathVariable Long id) {
		return orderService.getOrderById(id);
	}

	@GetMapping("/user/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public List<OrderResponseDTO> getOrdersByUserId(@PathVariable Long userId) {
		return orderService.getOrdersByUserId(userId);
	}

	@DeleteMapping("/{id}/cancel")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelOrder(@PathVariable Long id) {
		orderService.cancelOrder(id);
	}

}
