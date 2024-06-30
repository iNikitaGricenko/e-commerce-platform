package com.wolfhack.service.payment.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StripeConfigPOJO {

	@JsonProperty("publishableKey")
	private String publicKey;

	@JsonProperty("secretKey")
	private String secretKey;

	@JsonProperty("currency")
	private String currency;

}
