package com.wolfhack.service.admin.service;

import com.wolfhack.service.admin.adapter.client.UserClient;
import com.wolfhack.service.admin.model.dto.UserActivityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

	private final UserClient userClient;

	public List<UserActivityDTO> getUserActivity() {
		//		return userClient.getUserActivity();
		return List.of(
			new UserActivityDTO("2024-06-01", 100),
			new UserActivityDTO("2024-06-02", 150),
			new UserActivityDTO("2024-06-03", 200)
		);
	}

}
