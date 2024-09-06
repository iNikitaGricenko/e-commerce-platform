package com.wolfhack.service.order.controller;

import com.wolfhack.common.wrapper.DomainPage;
import com.wolfhack.service.order.model.dto.OrderRequestDTO;
import com.wolfhack.service.order.model.dto.OrderResponseDTO;
import com.wolfhack.service.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long createOrder(@Valid @RequestBody OrderRequestDTO dto) {
		return orderService.createOrder(dto);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public DomainPage<OrderResponseDTO> getOrdersPage(Pageable pageable) {
		return orderService.getPage(pageable);
	}

	@GetMapping("/list")
	@ResponseStatus(HttpStatus.OK)
	public List<OrderResponseDTO> getOrders() {
		return orderService.getAll();
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
