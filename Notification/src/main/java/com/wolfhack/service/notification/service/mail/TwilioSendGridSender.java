package com.wolfhack.service.notification.service.mail;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.wolfhack.service.notification.annotations.aop.AspectLog;
import com.wolfhack.service.notification.model.MailMessagePOJO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @NotSupported This service is not supported yet
 * */
@Slf4j
@Service
@RequiredArgsConstructor
public class TwilioSendGridSender implements IMailSender {

	@Value("${sendgrid.api.key:SENDGRID_API_KEY}")
	private String sendGridApiKey;

	@Value("${mail.send.from:noreply@wolfhack.com}")
	private String mailFrom;

	@Override
	@AspectLog
	public void send(MailMessagePOJO messagePOJ) {
		SendGrid sendGrid = new SendGrid(sendGridApiKey);

		Email from = new Email(mailFrom);
		String subject = messagePOJ.subject();
		Email to = new Email(messagePOJ.to());
		Content content = new Content("text/plain", messagePOJ.body());

		Mail mail = new Mail(from, subject, to, content);
		try {
			Request request = new Request();
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());

			Response response = sendGrid.api(request);

			log.debug(
				"Sent with SendGrid response [{}; {}; {}]",
				response.getStatusCode(),
				response.getBody(),
				response.getHeaders()
			);
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

}
