package com.wolfhack.service.payment.model.domain;

import com.wolfhack.common.model.domain.DomainModel;
import com.wolfhack.service.payment.model.PaymentMethod;
import com.wolfhack.service.payment.model.PaymentStatus;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.wolfhack.service.payment.model.entity.PaymentEntity}
 */
@Data
public class Payment implements DomainModel, Serializable {

	private Long id;

	private Long orderId;

	private PaymentMethod method;

	private String methodDetails;

	private Double amount;

	private PaymentStatus status;

	private LocalDateTime successDate;

	private LocalDateTime cancelDate;

    private String paypalOrderId;

    private String paypalLink;

    private String paymentIntentId;

	public void success() {
		this.successDate = LocalDateTime.now();
		this.status = PaymentStatus.SUCCESS;
	}

	public void cancel() {
		this.cancelDate = LocalDateTime.now();
		this.status = PaymentStatus.CANCELED;
	}

}