package com.wolfhack.service.payment.adapter.payment.processors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import com.wolfhack.service.payment.adapter.payment.processors.model.PaymentProcess;
import com.wolfhack.service.payment.model.domain.PayPalConfigPOJO;
import com.wolfhack.service.payment.model.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaypalPaymentService implements PaymentGatewayService {

	private final PayPalHttpClient payPalHttpClient;

	private final PayPalConfigPOJO paypalConfig;

	@Override
	public PaymentProcess process(Payment payment) {
		OrderRequest orderRequest = new OrderRequest()
			.checkoutPaymentIntent("CAPTURE")
			.purchaseUnits(
				List.of(
					new PurchaseUnitRequest()
						.amountWithBreakdown(
							new AmountWithBreakdown()
								.currencyCode(paypalConfig.getCurrency())
								.value(payment.getAmount().toString())
						)
				)
			);

		OrdersCreateRequest request = new OrdersCreateRequest().requestBody(orderRequest);
		try {
			HttpResponse<Order> response = payPalHttpClient.execute(request);

			if (response.statusCode() == 201) {
				payment.success();

				payment.setPaypalOrderId(response.result().id());
				payment.setPaypalLink(getApproveLink(response.result()));

				return PaymentProcess.SUCCESS;
			} else {
				throw new RuntimeException("Unable to create PayPal order: " + response.result());
			}
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public PaymentProcess cancel(Payment payment) {

		throw new IllegalStateException("This payment method is not support payment cancellation");
	}

	private String getApproveLink(Order order) {
		return order
			.links()
			.stream()
			.filter(link -> "approve".equalsIgnoreCase(link.rel()))
			.findFirst()
			.map(LinkDescription::href)
			.orElse(null);
	}

}
