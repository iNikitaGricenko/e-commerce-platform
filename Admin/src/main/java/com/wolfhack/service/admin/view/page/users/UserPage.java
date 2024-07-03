package com.wolfhack.service.admin.view.page.users;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.wolfhack.service.admin.model.dto.UserProfileEditDTO;
import com.wolfhack.service.admin.service.UserService;
import com.wolfhack.service.admin.view.component.layout.MainLayout;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

@PageTitle("Users")
@Route(value = "/users", layout = MainLayout.class)
@Uses(Icon.class)
public class UserPage extends Div implements BeforeEnterObserver {


	private final String SAMPLEPERSON_ID = "samplePersonID";

	private final String SAMPLEPERSON_EDIT_ROUTE_TEMPLATE = "master-detail/%s/edit";

	private final Grid<UserProfileEditDTO> grid = new Grid<>(UserProfileEditDTO.class, false);

	private TextField username;

	private TextField email;

	private TextField firstName;

	private TextField lastName;

	private TextField phoneNumber;

	private TextField address;

	private PasswordField password;

	private final Button cancel = new Button("Cancel");

	private final Button save = new Button("Save");

	private final BeanValidationBinder<UserProfileEditDTO> binder;

	private UserProfileEditDTO userProfileEditDTO;

	private final UserService userService;

	public UserPage(UserService userService) {
		this.userService = userService;
		addClassNames("users-view");

		// Create UI
		SplitLayout splitLayout = new SplitLayout();

		createGridLayout(splitLayout);
		createEditorLayout(splitLayout);

		add(splitLayout);

		// Configure Grid
		grid.addColumn("username").setAutoWidth(true);
		grid.addColumn("email").setAutoWidth(true);
		grid.addColumn("firstName").setAutoWidth(true);
		grid.addColumn("lastName").setAutoWidth(true);
		grid.addColumn("phoneNumber").setAutoWidth(true);
		grid.addColumn("address").setAutoWidth(true);
		grid.addColumn("password").setAutoWidth(true);

		grid.setItems(query -> userService.get(
				PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
			.stream());
		grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

		// when a row is selected or deselected, populate form
		grid.asSingleSelect().addValueChangeListener(event -> {
			if (event.getValue() != null) {
				UI.getCurrent().navigate(String.format(SAMPLEPERSON_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
			} else {
				clearForm();
				UI.getCurrent().navigate(UserPage.class);
			}
		});

		// Configure Form
		binder = new BeanValidationBinder<>(UserProfileEditDTO.class);

		// Bind fields. This is where you'd define e.g. validation rules

		binder.bindInstanceFields(this);

		cancel.addClickListener(e -> {
			clearForm();
			refreshGrid();
		});

		save.addClickListener(e -> {
			try {
				if (this.userProfileEditDTO == null) {
					this.userProfileEditDTO = new UserProfileEditDTO();
				}
				binder.writeBean(this.userProfileEditDTO);
				userService.update(this.userProfileEditDTO);
				clearForm();
				refreshGrid();
				Notification.show("Data updated");
				UI.getCurrent().navigate(UserPage.class);
			} catch (ValidationException validationException) {
				Notification.show("Failed to update the data. Check again that all values are valid");
			} catch (Exception exception) {
				Notification n = Notification.show(
					"Error updating the data. Somebody else has updated the record while you were making changes.");
				n.setPosition(Position.MIDDLE);
				n.addThemeVariants(NotificationVariant.LUMO_ERROR);
			}
		});
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		Optional<Long> samplePersonId = event.getRouteParameters().get(SAMPLEPERSON_ID).map(Long::parseLong);
		if (samplePersonId.isPresent()) {
			Optional<UserProfileEditDTO> samplePersonFromBackend = userService.get(samplePersonId.get());
			if (samplePersonFromBackend.isPresent()) {
				populateForm(samplePersonFromBackend.get());
			} else {
				Notification.show(
					String.format("The requested samplePerson was not found, ID = %s", samplePersonId.get()), 3000,
					Notification.Position.BOTTOM_START);
				// when a row is selected but the data is no longer available,
				// refresh grid
				refreshGrid();
				event.forwardTo(UserPage.class);
			}
		}
	}

	private void createEditorLayout(SplitLayout splitLayout) {
		Div editorLayoutDiv = new Div();
		editorLayoutDiv.setClassName("editor-layout");

		Div editorDiv = new Div();
		editorDiv.setClassName("editor");
		editorLayoutDiv.add(editorDiv);

		FormLayout formLayout = new FormLayout();
		username = new TextField("Username");
		email = new TextField("Email");
		firstName = new TextField("First Name");
		lastName = new TextField("Last Name");
		phoneNumber = new TextField("Phone Number");
		address = new TextField("Address");
		password = new PasswordField("Password");
		formLayout.add(username, email, firstName, lastName, phoneNumber, address, password);

		editorDiv.add(formLayout);
		createButtonLayout(editorLayoutDiv);

		splitLayout.addToSecondary(editorLayoutDiv);
	}

	private void createButtonLayout(Div editorLayoutDiv) {
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setClassName("button-layout");
		cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		buttonLayout.add(save, cancel);
		editorLayoutDiv.add(buttonLayout);
	}

	private void createGridLayout(SplitLayout splitLayout) {
		Div wrapper = new Div();
		wrapper.setClassName("grid-wrapper");
		splitLayout.addToPrimary(wrapper);
		wrapper.add(grid);
	}

	private void refreshGrid() {
		grid.select(null);
		grid.getDataProvider().refreshAll();
	}

	private void clearForm() {
		populateForm(null);
	}

	private void populateForm(UserProfileEditDTO value) {
		this.userProfileEditDTO = value;
		binder.readBean(this.userProfileEditDTO);
	}

}
