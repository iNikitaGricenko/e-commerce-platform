package com.wolfhack.service.payment.config.paypal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.wolfhack.service.payment.model.domain.PayPalConfigPOJO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(PaypalProperties.class)
public class PaypalConfig {

	private final PaypalProperties paypalProperties;

	@Bean
	public PayPalConfigPOJO getMailConfig() throws Exception {
		Resource properties = paypalProperties.getProperties();

		if (properties != null) {
			try (InputStream inputStream = properties.getInputStream()) {
				return new ObjectMapper().readValue(inputStream, PayPalConfigPOJO.class);
			} catch (IOException exception) {
				throw new RuntimeException(exception);
			}
		}

		throw new Exception("PayPal configuration is not available");
	}

	@Bean
	public PayPalHttpClient payPalHttpClient(PayPalConfigPOJO paypalConfig) {
		PayPalEnvironment environment = new PayPalEnvironment.Sandbox(paypalConfig.getClientId(), paypalConfig.getClientSecret());
		return new PayPalHttpClient(environment);
	}

}
