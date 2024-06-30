package com.wolfhack.service.notification.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MailConfigPOJO {

	private String host;

	private String port;

	private String username;

	private String password;

	@JsonProperty("smtp.auth")
	private boolean smtpAuth;

	@JsonProperty("smtp.starttls.enable")
	private boolean smtpStarttlsEnable;

	private boolean debug;

}
