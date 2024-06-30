package com.wolfhack.service.payment.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wolfhack.service.payment.model.PaymentMethod;
import com.wolfhack.service.payment.model.PaymentStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.wolfhack.service.payment.model.entity.PaymentEntity}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PaymentResponseDTO(
	Long id,
	Long orderId,
	PaymentMethod method,
	Double amount,
	PaymentStatus status,
	LocalDateTime successDate,
	LocalDateTime cancelDate
) implements Serializable { }