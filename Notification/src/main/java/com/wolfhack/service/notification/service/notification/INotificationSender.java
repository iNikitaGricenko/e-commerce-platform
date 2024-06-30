package com.wolfhack.service.notification.service.notification;

import com.wolfhack.service.notification.model.MessagePOJO;
import com.wolfhack.service.notification.model.MulticastMessagePOJO;
import com.wolfhack.service.notification.model.SubscriptionPOJO;
import com.wolfhack.service.notification.model.TopicMessagePOJO;

import java.util.List;

public interface INotificationSender {

	String send(MessagePOJO message);

	String send(TopicMessagePOJO message);

	List<String> send(MulticastMessagePOJO message);

	void subscribe(SubscriptionPOJO subscription);

}
