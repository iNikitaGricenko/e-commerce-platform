package com.wolfhack.service.admin.ui.view;

import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("monitoring")
public class MonitoringView extends VerticalLayout {

	public MonitoringView() {
		setSizeFull();
		IFrame adminFrame = new IFrame("/admin");
		adminFrame.setSizeFull();
		add(adminFrame);
	}

}
