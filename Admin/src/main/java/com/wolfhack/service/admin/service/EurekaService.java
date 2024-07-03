package com.wolfhack.service.admin.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EurekaService {

	private final EurekaClient eurekaClient;

	public Map<String, Map<String, Long>> getServices() {
		return eurekaClient
			.getApplications()
			.getRegisteredApplications()
			.stream()
			.flatMap(application -> application.getInstances().stream())
			.collect(
				Collectors.groupingBy(
					InstanceInfo::getAppName,
					Collectors.groupingBy(
						o -> o.getStatus().name(),
						Collectors.counting()
					)
				)
			);
	}

}
