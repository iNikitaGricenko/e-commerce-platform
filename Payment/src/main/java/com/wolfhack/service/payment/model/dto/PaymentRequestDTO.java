package com.wolfhack.service.payment.model.dto;

import com.wolfhack.service.payment.model.PaymentMethod;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.service.payment.model.entity.PaymentEntity}
 */
public record PaymentRequestDTO(
	@NotNull(message = "Order Id should not be null")
	@Min(message = "Order ID could not be below than 1", value = 1)
	Long orderId,

	@NotNull(message = "Payment Method could not be null")
	@NotEmpty(message = "Payment Method could not be empty")
	PaymentMethod method,

	Double amount
) implements Serializable { }