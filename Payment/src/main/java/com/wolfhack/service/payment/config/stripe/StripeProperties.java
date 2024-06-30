package com.wolfhack.service.payment.config.stripe;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "stripe")
public class StripeProperties {

	private Resource resource;

	/**
	 * @return the serviceAccount
	 */
	public Resource getProperties() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setProperties(Resource resource) {
		this.resource = resource;
	}
}
