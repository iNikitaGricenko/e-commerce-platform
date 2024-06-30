package com.wolfhack.service.notification.service.message;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.wolfhack.service.notification.annotations.aop.AspectLog;
import com.wolfhack.service.notification.model.SMSMessagePOJO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TwilioSMSSender implements ISMSSender {

	@Value("${twilio.generated.phone:NONE}")
	public String phoneNumber;

	@Value("${twilio.account.sid:TWILIO_ACCOUNT_SID}")
	public String twilioAccountSid;

	@Value("${twilio.auth.token:TWILIO_AUTH_TOKEN}")
	public String twilioAuthToken;

	@Override
	@AspectLog
	public void send(SMSMessagePOJO sms) {
		if (this.twilioAccountSid == null || this.twilioAuthToken == null || this.phoneNumber == null) {
			return;
		}

		Twilio.init(this.twilioAccountSid, this.twilioAuthToken);

		PhoneNumber phoneNumberTo = new PhoneNumber(sms.to());
		PhoneNumber phoneNumberFrom = new PhoneNumber(this.phoneNumber);

		Message message = Message
			.creator(
				phoneNumberTo,
				phoneNumberFrom,
				sms.body()
			)
			.create();

		log.debug("twilio message sid {}", message.getSid());
	}

}
