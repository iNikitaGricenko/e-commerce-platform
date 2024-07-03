package com.wolfhack.service.admin.view.page.analytics;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.wolfhack.service.admin.service.AnalyticsService;
import jakarta.annotation.PostConstruct;

@Route("/analytics")
public class AnalyticsPage extends VerticalLayout {

	private final AnalyticsService analyticsService;

	private final Chart chart = new Chart(ChartType.LINE);

	public AnalyticsPage(AnalyticsService analyticsService) {
		this.analyticsService = analyticsService;
		add(chart);
	}

	@PostConstruct
	public void init() {
		Configuration configuration = chart.getConfiguration();
		configuration.setTitle("User Activity Analytics");

		DataSeries series = new DataSeries();
		analyticsService.getUserActivity().forEach(activity ->
			series.add(new DataSeriesItem(activity.date(), activity.count()))
		);

		configuration.addSeries(series);
	}

}
