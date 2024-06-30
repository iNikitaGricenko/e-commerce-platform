package com.wolfhack.service.payment.adapter.payment.processors;

import com.wolfhack.service.payment.adapter.payment.processors.model.PaymentProcess;
import com.wolfhack.service.payment.model.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditCardPaymentService implements PaymentGatewayService {

	@Override
	public PaymentProcess process(Payment payment) {

		throw new IllegalStateException("This payment method is not supported yet");
	}

	@Override
	public PaymentProcess cancel(Payment payment) {

		throw new IllegalStateException("This payment method is not supported yet");
	}

}
