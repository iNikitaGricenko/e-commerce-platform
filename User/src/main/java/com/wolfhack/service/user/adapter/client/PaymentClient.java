package com.wolfhack.service.user.adapter.client;

import com.wolfhack.common.model.dto.PaymentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "payment")
public interface PaymentClient {

	@GetMapping("/payments/user/{userId}")
	List<PaymentDTO> getPaymentsByUserId(@PathVariable("id") Long id);

}
