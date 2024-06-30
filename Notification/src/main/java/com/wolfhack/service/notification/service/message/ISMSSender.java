package com.wolfhack.service.notification.service.message;

import com.wolfhack.service.notification.model.SMSMessagePOJO;

public interface ISMSSender {

	void send(SMSMessagePOJO sms);

}
