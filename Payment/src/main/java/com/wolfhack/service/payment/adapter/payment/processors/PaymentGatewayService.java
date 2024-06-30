package com.wolfhack.service.payment.adapter.payment.processors;

import com.wolfhack.service.payment.adapter.payment.processors.model.PaymentProcess;
import com.wolfhack.service.payment.model.domain.Payment;

public interface PaymentGatewayService {

	PaymentProcess process(Payment payment);

	PaymentProcess cancel(Payment payment);

}
