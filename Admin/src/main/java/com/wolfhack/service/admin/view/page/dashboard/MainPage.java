package com.wolfhack.service.admin.view.page.dashboard;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.wolfhack.service.admin.model.dto.ServiceStatusDTO;
import com.wolfhack.service.admin.service.ServiceAvailabilityService;
import com.wolfhack.service.admin.view.component.layout.MainLayout;
import jakarta.annotation.PostConstruct;

import java.util.List;

@Route(value = "/main", layout = MainLayout.class)
public class MainPage extends VerticalLayout {

	private final ServiceAvailabilityService availabilityService;

	private final Grid<ServiceStatusDTO> serviceStatusGrid = new Grid<>();

	public MainPage(ServiceAvailabilityService availabilityService) {
		this.availabilityService = availabilityService;

		add(serviceStatusGrid);
	}

	@PostConstruct
	public void init() {
		List<ServiceStatusDTO> services = availabilityService.getAllServicesStatus();
		serviceStatusGrid.setItems(services);
	}

}
