package com.wolfhack.service.admin.view.page.users;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.wolfhack.service.admin.model.dto.UserCreateDTO;
import com.wolfhack.service.admin.service.UserService;
import com.wolfhack.service.admin.view.component.layout.MainLayout;

@Route(value = "/users/add", layout = MainLayout.class)
public class AddUserPage extends VerticalLayout {

	private final UserService userService;

	private final TextField nameField = new TextField("Name");

	private final TextField emailField = new TextField("Email");

	private final PasswordField passwordField = new PasswordField("Password");

	private final PasswordField passwordConfirmField = new PasswordField("Confirm Password");

	private final TextField role = new TextField("Role");

	private final TextField active = new TextField("Active");

	public AddUserPage(UserService userService) {
		this.userService = userService;
		Button saveButton = new Button("Save");

		FormLayout formLayout = new FormLayout();

		formLayout.add(nameField, emailField, passwordField, passwordConfirmField, role, active, saveButton);

		add(formLayout);

		saveButton.addClickListener(this::addUser);
	}

	private void addUser(ClickEvent<Button> event) {
		if (!passwordField.getValue().equals(passwordConfirmField.getValue())) {
			Notification.show("Passwords do not match");
			return;
		}

		UserCreateDTO userDTO = new UserCreateDTO(
			nameField.getValue(),
			emailField.getValue(),
			passwordField.getValue(),
			passwordConfirmField.getValue(),
			role.getValue(),
			Boolean.parseBoolean(active.getValue())
		);

		userService.addUser(userDTO);
	}

}
