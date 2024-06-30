package com.wolfhack.service.admin.adapter.client;

import com.wolfhack.common.model.dto.PaymentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "payment")
public interface PaymentClient {

	@GetMapping("/payments/order/{orderId}")
	PaymentDTO getPaymentByOrderId(@PathVariable("orderId") Long orderId);

}
