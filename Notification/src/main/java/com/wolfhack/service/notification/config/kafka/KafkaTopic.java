package com.wolfhack.service.notification.config.kafka;

import com.wolfhack.service.notification.config.kafka.model.KafkaTopics;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopic {

	@Value(value = "${spring.kafka.bootstrap-servers}")
	private String bootstrapAddress;

	@Bean
	public KafkaAdmin kafkaAdmin() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		return new KafkaAdmin(configs);
	}

	@Bean
	public NewTopic userRegisterMessageTopic(Map<String, KafkaTopics> kafkaTopics) {
		final String topic = "user-register";

		kafkaTopics.put("register", new KafkaTopics(topic));

		return TopicBuilder.name(topic).build();
	}

	@Bean
	public NewTopic userResetTopic(Map<String, KafkaTopics> kafkaTopics) {
		final String topic = "user-reset";

		kafkaTopics.put("reset", new KafkaTopics(topic));

		return TopicBuilder.name(topic).build();
	}

	@Bean
	public NewTopic multicastMessageTopic(Map<String, KafkaTopics> kafkaTopics) {
		final String topic = "multiple-message";

		kafkaTopics.put(topic, new KafkaTopics(topic));

		return TopicBuilder.name(topic).build();
	}

	@Bean
	public NewTopic subscriptionTopic(Map<String, KafkaTopics> kafkaTopics) {
		final String topic = "subscribe";

		kafkaTopics.put(topic, new KafkaTopics(topic));

		return TopicBuilder.name("subscribe").build();
	}

	@Bean
	public NewTopic topicMessageTopic(Map<String, KafkaTopics> kafkaTopics) {
		final String topic = "single-message";

		kafkaTopics.put(topic, new KafkaTopics(topic));

		return TopicBuilder.name("single-message").build();
	}

	@Bean
	public Map<String, KafkaTopics> kafkaTopics() {
		return Map.of("default", new KafkaTopics("default"));
	}

}
