package com.wolfhack.service.notification.config.kafka;

import com.wolfhack.service.notification.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class KafkaListenerConfig {

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserRegisteredNotificationDTO> userRegisterKafkaListenerContainerFactory(
		ConsumerFactory<String, UserRegisteredNotificationDTO> consumerFactory
	) {
		ConcurrentKafkaListenerContainerFactory<String, UserRegisteredNotificationDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserResetNotificationDTO> userResetKafkaListenerContainerFactory(
		ConsumerFactory<String, UserResetNotificationDTO> consumerFactory
	) {
		ConcurrentKafkaListenerContainerFactory<String, UserResetNotificationDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, MulticastMessagePOJO> multicastMessageKafkaListenerContainerFactory(
		ConsumerFactory<String, MulticastMessagePOJO> consumerFactory
	) {
		ConcurrentKafkaListenerContainerFactory<String, MulticastMessagePOJO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, SubscriptionPOJO> subscriptionKafkaListenerContainerFactory(
		ConsumerFactory<String, SubscriptionPOJO> consumerFactory
	) {
		ConcurrentKafkaListenerContainerFactory<String, SubscriptionPOJO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, TopicMessagePOJO> topicMessageKafkaListenerContainerFactory(
		ConsumerFactory<String, TopicMessagePOJO> consumerFactory
	) {
		ConcurrentKafkaListenerContainerFactory<String, TopicMessagePOJO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

}
