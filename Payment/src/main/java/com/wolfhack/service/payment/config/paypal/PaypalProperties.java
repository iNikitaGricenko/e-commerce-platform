package com.wolfhack.service.payment.config.paypal;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "paypal")
public class PaypalProperties {

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
