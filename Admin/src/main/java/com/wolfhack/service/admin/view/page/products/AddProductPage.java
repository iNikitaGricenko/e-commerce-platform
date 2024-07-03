package com.wolfhack.service.admin.view.page.products;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.wolfhack.service.admin.model.dto.ProductCreateDTO;
import com.wolfhack.service.admin.service.ProductService;
import com.wolfhack.service.admin.view.component.layout.MainLayout;

@Route(value = "/products/add", layout = MainLayout.class)
public class AddProductPage extends VerticalLayout {

	private final ProductService productService;

	private final TextField nameField = new TextField("Name");

	private final TextField priceField = new TextField("Price");

	private final Button saveButton = new Button("Save");

	public AddProductPage(ProductService productService) {
		this.productService = productService;

		FormLayout formLayout = new FormLayout();
		formLayout.add(nameField, priceField, saveButton);

		add(formLayout);

		saveButton.addClickListener(this::addProduct);
	}

	private void addProduct(ClickEvent<Button> buttonClickEvent) {
		ProductCreateDTO product = new ProductCreateDTO(nameField.getValue(), Double.parseDouble(priceField.getValue()));
		productService.addProduct(product);
	}

}
