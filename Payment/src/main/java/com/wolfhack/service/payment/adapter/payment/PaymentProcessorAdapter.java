package com.wolfhack.service.payment.adapter.payment;

import com.wolfhack.service.payment.adapter.payment.processors.CreditCardPaymentService;
import com.wolfhack.service.payment.adapter.payment.processors.PaymentGatewayService;
import com.wolfhack.service.payment.adapter.payment.processors.PaypalPaymentService;
import com.wolfhack.service.payment.adapter.payment.processors.StripePaymentService;
import com.wolfhack.service.payment.adapter.payment.processors.model.PaymentProcess;
import com.wolfhack.service.payment.model.PaymentMethod;
import com.wolfhack.service.payment.model.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentProcessorAdapter {

	private final Map<PaymentMethod, PaymentGatewayService> paymentGatewayServices = new EnumMap<>(PaymentMethod.class);

	public PaymentProcessorAdapter(
		CreditCardPaymentService creditCardPaymentService,
		PaypalPaymentService payPalPaymentService,
		StripePaymentService stripePaymentService
	) {
		paymentGatewayServices.put(PaymentMethod.CreditCard, creditCardPaymentService);
		paymentGatewayServices.put(PaymentMethod.PayPal, payPalPaymentService);
		paymentGatewayServices.put(PaymentMethod.Stripe, stripePaymentService);
	}

	public PaymentProcess process(Payment payment) {
		PaymentMethod paymentMethod = payment.getMethod();

		PaymentGatewayService service = paymentGatewayServices.get(paymentMethod);

		if (service != null) {
			return service.process(payment);
		}

		throw new IllegalStateException("Unsupported payment method " + paymentMethod);
	}

	public PaymentProcess cancel(Payment payment) {
		PaymentMethod paymentMethod = payment.getMethod();

		PaymentGatewayService service = paymentGatewayServices.get(paymentMethod);

		if (service != null) {
			return service.cancel(payment);
		}

		throw new IllegalStateException("Unsupported payment method " + paymentMethod);
	}

}
