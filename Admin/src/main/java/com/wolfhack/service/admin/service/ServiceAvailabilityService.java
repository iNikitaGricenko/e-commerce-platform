package com.wolfhack.service.admin.service;

import com.wolfhack.service.admin.model.dto.ServiceStatusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceAvailabilityService {


	private final EurekaService eurekaService;

	public List<ServiceStatusDTO> getAllServicesStatus() {
		Map<String, String> statuses = serviceStatuses();

		return statuses
			.entrySet()
			.stream()
			.map(entry -> new ServiceStatusDTO(entry.getKey(), entry.getValue()))
			.toList();
	}

	private Map<String, String> serviceStatuses() {
		Map<String, Map<String, Long>> services = eurekaService.getServices();

		return services
			.entrySet()
			.stream()
			.collect(
				Collectors.toMap(
					Map.Entry::getKey,
					entry -> entry
						.getValue()
						.entrySet()
						.stream()
						.map(subEntry -> "instances: %s of status: %s".formatted(subEntry.getValue(), subEntry.getKey()))
						.collect(Collectors.joining())
				)
			);
	}

}
