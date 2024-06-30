package com.wolfhack.service.user.adapter.client;

import com.wolfhack.common.model.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "order")
public interface OrderClient {

	@GetMapping("/orders/user/{userId}")
	List<OrderDTO> getOrdersByUserId(@PathVariable("id") Long id);

}
