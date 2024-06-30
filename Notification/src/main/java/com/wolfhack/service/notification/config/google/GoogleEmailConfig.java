package com.wolfhack.service.notification.config.google;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolfhack.service.notification.model.MailConfigPOJO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(GoogleEmailProperties.class)
public class GoogleEmailConfig {

	private final GoogleEmailProperties googleEmailProperties;

	@Bean
	public JavaMailSender getJavaMailSender(MailConfigPOJO mailConfigPOJO) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername(mailConfigPOJO.getUsername());
		mailSender.setPassword(mailConfigPOJO.getPassword());

		Properties properties = mailSender.getJavaMailProperties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", mailConfigPOJO.isSmtpAuth());
		properties.put("mail.smtp.starttls.enable", mailConfigPOJO.isSmtpStarttlsEnable());
		properties.put("mail.debug", mailConfigPOJO.isDebug());

		return mailSender;
	}

	@Bean
	public MailConfigPOJO getMailConfig() throws Exception {
		Resource properties = googleEmailProperties.getProperties();

		if (properties != null) {
			try (InputStream inputStream = properties.getInputStream()) {
				return new ObjectMapper().readValue(inputStream, MailConfigPOJO.class);
			} catch (IOException exception) {
				throw new RuntimeException(exception);
			}
		}
		throw new Exception("Email configuration is not available");
	}

}
