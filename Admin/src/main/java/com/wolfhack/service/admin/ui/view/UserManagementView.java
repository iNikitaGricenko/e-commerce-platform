package com.wolfhack.service.admin.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.wolfhack.service.admin.model.domain.User;
import com.wolfhack.service.admin.service.UserService;

@Route("users")
public class UserManagementView extends VerticalLayout {

	private final Grid<User> userGrid = new Grid<>(User.class);

	private final TextField searchField = new TextField("Search Users");

	private final Button addButton = new Button("Add User");

	private final UserService userService;

	public UserManagementView(UserService userService) {
		this.userService = userService;
		setupGrid();
		setupToolbar();
		updateUserList();
	}

	private void setupGrid() {
		userGrid.setColumns("id", "username", "email", "role", "firstName", "lastName", "phoneNumber", "address");
		userGrid.asSingleSelect().addValueChangeListener(event -> {
			// Optionally open a form or dialog to edit user details
		});
		add(searchField, addButton, userGrid);
		addButton.addClickListener(e -> openUserForm(new User()));
	}

	private void setupToolbar() {
		searchField.setPlaceholder("Search Users by Username");
		searchField.setClearButtonVisible(true);
		searchField.addValueChangeListener(e -> updateUserList());
	}

	private void updateUserList() {
		userGrid.setItems(userService.getAllUsers());
	}

	private void openUserForm(User user) {
		// Open a form to edit or add a new user
	}

}
