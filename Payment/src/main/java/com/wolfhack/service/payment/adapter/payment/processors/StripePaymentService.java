package com.wolfhack.service.payment.adapter.payment.processors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.RefundCreateParams;
import com.wolfhack.service.payment.adapter.payment.processors.model.PaymentProcess;
import com.wolfhack.service.payment.model.domain.Payment;
import com.wolfhack.service.payment.model.domain.StripeConfigPOJO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StripePaymentService implements PaymentGatewayService {

	private final StripeConfigPOJO stripeConfig;

	@PostConstruct
	public void init() {
		Stripe.apiKey = stripeConfig.getSecretKey();
	}

	@Override
	public PaymentProcess process(Payment payment) {
		String currency = stripeConfig.getCurrency();

		PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
			.setCurrency(currency)
			.setAmount((long) (payment.getAmount() * 100))
			.setDescription("Payment for Order ID: %s".formatted(payment.getOrderId()))
			.build();

		try {
			PaymentIntent paymentIntent = PaymentIntent.create(createParams);

			String status = paymentIntent.getStatus();

			if (status.equalsIgnoreCase("succeeded")) {
				payment.success();

				payment.setPaymentIntentId(paymentIntent.getId());

				return PaymentProcess.SUCCESS;
			}

		} catch (StripeException exception) {
			throw new RuntimeException(exception);
		}

		return PaymentProcess.FAILURE;
	}

	@Override
	public PaymentProcess cancel(Payment payment) {
		if (payment.getPaymentIntentId() == null) {
			throw new IllegalArgumentException("Payment intent ID is required to cancel the payment.");
		}

		RefundCreateParams params = RefundCreateParams.builder()
			.setPaymentIntent(payment.getPaymentIntentId())
			.build();

		try {
			Refund refund = Refund.create(params);

			String status = refund.getStatus();

			if (status.equalsIgnoreCase("succeeded")) {
				payment.cancel();

				return PaymentProcess.SUCCESS;
			}
		} catch (StripeException exception) {
			throw new RuntimeException(exception);
		}

		return PaymentProcess.FAILURE;
	}

}
