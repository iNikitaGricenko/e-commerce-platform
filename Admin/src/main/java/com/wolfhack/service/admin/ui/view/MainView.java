package com.wolfhack.service.admin.ui.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends AppLayout {

	public MainView() {
		createHeader();
		createDrawer();
	}

	private void createHeader() {
		H1 logo = new H1("Admin Dashboard");
		logo.addClassName("logo");

		Button logout = new Button("Logout");
		logout.addClickListener(e -> {
			// TODO: Add logout logic here
		});

		addToNavbar(logo, logout);
	}

	private void createDrawer() {
		VerticalLayout menuLayout = new VerticalLayout();
		menuLayout.add(new NativeLabel("Catalog Management"));
		menuLayout.add(new NativeLabel("Order Management"));
		menuLayout.add(new NativeLabel("User Management"));
		menuLayout.add(new NativeLabel("Review Management"));
		setDrawerOpened(true);
		addToDrawer(menuLayout);
	}

}
