package com.wolfhack.service.notification.config.google;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "email.google")
public class GoogleEmailProperties {

	private Resource serviceAccount;

	/**
	 * @return the serviceAccount
	 */
	public Resource getProperties() {
		return serviceAccount;
	}

	/**
	 * @param serviceAccount the serviceAccount to set
	 */
	public void setProperties(Resource serviceAccount) {
		this.serviceAccount = serviceAccount;
	}
}
