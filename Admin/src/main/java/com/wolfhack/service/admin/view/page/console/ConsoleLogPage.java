package com.wolfhack.service.admin.view.page.console;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.wolfhack.service.admin.model.dto.LogEntryDTO;
import com.wolfhack.service.admin.service.LogService;
import com.wolfhack.service.admin.view.component.layout.MainLayout;
import jakarta.annotation.PostConstruct;

import java.util.List;

@Route(value = "/console/log", layout = MainLayout.class)
public class ConsoleLogPage extends VerticalLayout {

	private final LogService logService;

	private final Grid<LogEntryDTO> logGrid = new Grid<>(LogEntryDTO.class);

	public ConsoleLogPage(LogService logService) {
		this.logService = logService;
		add(logGrid);
	}

	@PostConstruct
	public void init() {
		List<LogEntryDTO> logs = logService.getLatestLogs();
		logGrid.setItems(logs);
	}

}
