package com.wolfhack.service.notification.service.mail;

import com.wolfhack.service.notification.model.MailMessagePOJO;

public interface IMailSender {

	void send(MailMessagePOJO mailMessagePOJO);

}
