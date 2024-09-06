package com.wolfhack.service.admin.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.wolfhack.service.admin.model.domain.Order;
import com.wolfhack.service.admin.service.OrderService;

@Route("orders")
public class OrderManagementView extends VerticalLayout {

	private final Grid<Order> orderGrid = new Grid<>(Order.class);

	private final TextField searchField = new TextField("Search Orders");

	private final Button refreshButton = new Button("Refresh");

	private final OrderService orderService;

	public OrderManagementView(OrderService orderService) {
		this.orderService = orderService;
		setupGrid();
		setupToolbar();
		updateOrderList();
	}

	private void setupGrid() {
		orderGrid.setColumns("id", "userId", "orderStatus", "paymentId", "shippingAddress", "createdDate", "updatedDate");
		orderGrid.asSingleSelect().addValueChangeListener(event -> {
			// Optionally open a form or dialog to show detailed order or perform actions
		});
		add(searchField, refreshButton, orderGrid);
		refreshButton.addClickListener(e -> updateOrderList());
	}

	private void setupToolbar() {
		searchField.setPlaceholder("Search by User ID");
		searchField.setClearButtonVisible(true);
		searchField.addValueChangeListener(e -> updateOrderList());
	}

	private void updateOrderList() {
		orderGrid.setItems(
			orderService.getAllOrders()
				.getContent()
		);
	}

}
