package com.wolfhack.service.payment.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PayPalConfigPOJO {

	@JsonProperty("id")
	private String clientId;

	@JsonProperty("secret")
	private String clientSecret;

	@JsonProperty("mode")
	private String mode;

    @JsonProperty("returnUrl")
    private String returnUrl;

    @JsonProperty("cancelUrl")
    private String cancelUrl;

    @JsonProperty("currency")
    private String currency;

}
