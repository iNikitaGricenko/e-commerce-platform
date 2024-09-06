package com.wolfhack.service.admin.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.wolfhack.service.admin.model.domain.Product;
import com.wolfhack.service.admin.service.ProductService;

@Route("catalog")
public class CatalogManagementView extends VerticalLayout {

	private final Grid<Product> productGrid = new Grid<>(Product.class);

	private final TextField searchField = new TextField();

	private final Button addProductButton = new Button("Add Product");

	private ProductForm productForm;

	private final ProductService productService;

	public CatalogManagementView(ProductService productService) {
		this.productService = productService;
		setupGrid();
		setupForm();

		HorizontalLayout toolbar = new HorizontalLayout(searchField, addProductButton);
		add(toolbar, productGrid, productForm);

		addProductButton.addClickListener(click -> {
			productGrid.asSingleSelect().clear();
			productForm.setProduct(new Product());
		});

		searchField.setPlaceholder("Search products...");
		searchField.addValueChangeListener(e -> updateProductList());
	}

	private void setupGrid() {
		productGrid.setColumns("id", "name", "description", "price", "categoryId");
		productGrid.asSingleSelect()
			.addValueChangeListener(event -> productForm.setProduct(event.getValue()));
	}

	private void setupForm() {
		productForm = new ProductForm();
		productForm.setVisible(false);
		productForm.addSaveListener(this::saveProduct);
		productForm.addDeleteListener(this::deleteProduct);
	}

	private void updateProductList() {
		if (searchField.getValue().isEmpty()) {
			productGrid.setItems(productService.findAll());
		} else {
			productGrid.setItems(productService.findByName(searchField.getValue()));
		}
	}

	private void saveProduct(ProductForm.SaveEvent event) {
		productService.save(event.getProduct());
		updateProductList();
		productForm.setVisible(false);
	}

	private void deleteProduct(ProductForm.DeleteEvent event) {
		productService.delete(event.getProduct());
		updateProductList();
		productForm.setVisible(false);
	}

}
