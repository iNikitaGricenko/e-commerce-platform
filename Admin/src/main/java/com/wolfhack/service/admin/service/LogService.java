package com.wolfhack.service.admin.service;

import com.wolfhack.service.admin.model.dto.LogEntryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

	public List<LogEntryDTO> getLatestLogs() {
		// TODO retrieve all log entries from other services
		return List.of(
			new LogEntryDTO("2024-06-01T12:00:00", "INFO", "Application Started"),
			new LogEntryDTO("2024-06-01T12:01:00", "ERROR", "Database connection failed")
		);
	}

}
