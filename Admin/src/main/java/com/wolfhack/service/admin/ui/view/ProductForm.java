package com.wolfhack.service.admin.ui.view;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import com.wolfhack.service.admin.model.domain.Product;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductForm extends FormLayout {

	private final TextField name = new TextField("Product Name");

	private final TextField category = new TextField("Category");

	private final BigDecimalField price = new BigDecimalField("Price");

	private final NumberField stock = new NumberField("Stock");

	private final Button save = new Button("Save");

	private final Button delete = new Button("Delete");

	private final Button close = new Button("Cancel");

	private final Binder<Product> binder = new Binder<>(Product.class);

	private Product product;

	public ProductForm() {
		add(name, category, price, stock, createButtonsLayout());
		binder.bindInstanceFields(this);
	}

	public void setProduct(Product product) {
		this.product = product;
		binder.readBean(product);
		setVisible(true);
		name.focus();
	}

	private HorizontalLayout createButtonsLayout() {
		save.addClickListener(event -> validateAndSave());
		delete.addClickListener(event -> fireEvent(new DeleteEvent(this, product)));
		close.addClickListener(event -> setVisible(false));

		return new HorizontalLayout(save, delete, close);
	}

	public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
		return addListener(SaveEvent.class, listener);
	}

	public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
		return addListener(DeleteEvent.class, listener);
	}

	private void validateAndSave() {
		try {
			binder.writeBean(product);
			fireEvent(new SaveEvent(this, product));
		} catch (ValidationException e) {
			log.error("Exception occurs while validate ProductForm for save", e);
		}
	}

	@Getter
	public static abstract class ProductFormEvent extends ComponentEvent<ProductForm> {

		private final Product product;

		protected ProductFormEvent(ProductForm source, Product product) {
			super(source, false);
			this.product = product;
		}

	}

	public static class SaveEvent extends ProductFormEvent {

		SaveEvent(ProductForm source, Product product) {
			super(source, product);
		}

	}

	public static class DeleteEvent extends ProductFormEvent {

		DeleteEvent(ProductForm source, Product product) {
			super(source, product);
		}

	}

}
