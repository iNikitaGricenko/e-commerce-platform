package com.wolfhack.service.payment.config.stripe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolfhack.service.payment.model.domain.StripeConfigPOJO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(StripeProperties.class)
public class StripeConfig {

	private final StripeProperties stripeProperties;

	@Bean
	public StripeConfigPOJO getMailConfig() throws Exception {
		Resource properties = stripeProperties.getProperties();

		if (properties != null) {
			try (InputStream inputStream = properties.getInputStream()) {
				return new ObjectMapper().readValue(inputStream, StripeConfigPOJO.class);
			} catch (IOException exception) {
				throw new RuntimeException(exception);
			}
		}

		throw new Exception("Stripe configuration is not available");
	}

}
