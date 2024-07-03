package com.wolfhack.service.admin.view.page.orders;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.wolfhack.common.model.dto.OrderDTO;
import com.wolfhack.service.admin.service.OrderService;
import com.wolfhack.service.admin.view.component.layout.MainLayout;
import org.apache.commons.lang3.StringUtils;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@PageTitle("Orders")
@Route(value = "/orders", layout = MainLayout.class)
public class OrderPage extends Div {

	private GridPro<OrderDTO> grid;

	private GridListDataView<OrderDTO> gridListDataView;

	private Grid.Column<OrderDTO> idColumn;

	private Grid.Column<OrderDTO> productColumn;

	private Grid.Column<OrderDTO> amountColumn;

	private Grid.Column<OrderDTO> priceColumn;

	private Grid.Column<OrderDTO> statusColumn;

	private final OrderService orderService;

	public OrderPage(OrderService orderService) {
		this.orderService = orderService;

		addClassName("orders-view");
		setSizeFull();
		createGrid();
		add(grid);
	}

	private void createGrid() {
		createGridComponent();
		addColumnsToGrid();
		addFiltersToGrid();
	}

	private void createGridComponent() {
		grid = new GridPro<>();
		grid.setSelectionMode(SelectionMode.MULTI);
		grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
		grid.setHeight("100%");

		List<OrderDTO> orderDTOS = getOrders();
		gridListDataView = grid.setItems(orderDTOS);
	}

	private void addColumnsToGrid() {
		createIdColumn();
		createProductColumn();
		createAmountColumn();
		createPriceColumn();
		createStatusColumn();
	}

	private void createIdColumn() {
		idColumn = grid.addColumn(new ComponentRenderer<>(orderDTO -> {
			HorizontalLayout hl = new HorizontalLayout();
			hl.setAlignItems(Alignment.CENTER);
			Span span = new Span();
			span.setClassName("ID");
			span.setText(orderDTO.getId().toString());
			hl.add(span);
			return hl;
		})).setComparator(OrderDTO::getProductName).setHeader("ID");
	}

	private void createProductColumn() {
		productColumn = grid.addColumn(new ComponentRenderer<>(orderDTO -> {
			HorizontalLayout hl = new HorizontalLayout();
			hl.setAlignItems(Alignment.CENTER);
			Span span = new Span();
			span.setClassName("product name");
			span.setText(orderDTO.getProductName());
			hl.add(span);
			return hl;
		})).setComparator(OrderDTO::getProductName).setHeader("Product");
	}

	private void createAmountColumn() {
		amountColumn = grid
			.addEditColumn(OrderDTO::getTotalAmount,
				new NumberRenderer<>(OrderDTO::getTotalAmount, NumberFormat.getCurrencyInstance(Locale.US)))
			.text((item, newValue) -> item.setTotalAmount(Integer.parseInt(newValue)))
			.setComparator(OrderDTO::getTotalAmount).setHeader("Amount");
	}

	private void createPriceColumn() {
		priceColumn = grid
			.addEditColumn(OrderDTO::getTotalPrice,
				new NumberRenderer<>(OrderDTO::getTotalPrice, NumberFormat.getCurrencyInstance(Locale.US)))
			.text((item, newValue) -> item.setTotalPrice(Double.parseDouble(newValue)))
			.setComparator(OrderDTO::getTotalPrice).setHeader("Price");
	}

	private void createStatusColumn() {
		statusColumn = grid.addEditColumn(OrderDTO::getProductName, new ComponentRenderer<>(client -> {
				Span span = new Span();
				span.setText(client.getStatus());
				span.getElement().setAttribute("theme", "badge " + client.getStatus().toLowerCase());
				return span;
			})).select(OrderDTO::setStatus, Arrays.asList("Pending", "Success", "Error"))
			.setComparator(OrderDTO::getStatus).setHeader("Status");
	}

	private void addFiltersToGrid() {
		HeaderRow filterRow = grid.appendHeaderRow();

		NumberField idFilter = new NumberField();
		idFilter.setPlaceholder("Filter");
		idFilter.setClearButtonVisible(true);
		idFilter.setWidth("100%");
		idFilter.setValueChangeMode(ValueChangeMode.EAGER);
		idFilter.addValueChangeListener(event -> gridListDataView
			.addFilter(orderDTO -> orderDTO.getId().equals(idFilter.getValue().longValue())));
		filterRow.getCell(idColumn).setComponent(idFilter);

		TextField productFilter = new TextField();
		productFilter.setPlaceholder("Filter");
		productFilter.setClearButtonVisible(true);
		productFilter.setWidth("100%");
		productFilter.setValueChangeMode(ValueChangeMode.EAGER);
		productFilter.addValueChangeListener(event -> gridListDataView
			.addFilter(orderDTO -> StringUtils.containsIgnoreCase(orderDTO.getProductName(), productFilter.getValue())));
		filterRow.getCell(productColumn).setComponent(productFilter);

		TextField amountFilter = new TextField();
		amountFilter.setPlaceholder("Filter");
		amountFilter.setClearButtonVisible(true);
		amountFilter.setWidth("100%");
		amountFilter.setValueChangeMode(ValueChangeMode.EAGER);
		amountFilter.addValueChangeListener(event -> gridListDataView.addFilter(orderDTO -> StringUtils
			.containsIgnoreCase(Double.toString(orderDTO.getTotalAmount()), amountFilter.getValue())));
		filterRow.getCell(amountColumn).setComponent(amountFilter);

		TextField priceFilter = new TextField();
		priceFilter.setPlaceholder("Filter");
		priceFilter.setClearButtonVisible(true);
		priceFilter.setWidth("100%");
		priceFilter.setValueChangeMode(ValueChangeMode.EAGER);
		priceFilter.addValueChangeListener(event -> gridListDataView.addFilter(orderDTO -> StringUtils
			.containsIgnoreCase(Double.toString(orderDTO.getTotalPrice()), priceFilter.getValue())));
		filterRow.getCell(priceColumn).setComponent(amountFilter);

		ComboBox<String> statusFilter = new ComboBox<>();
		statusFilter.setItems(Arrays.asList("Pending", "Success", "Error"));
		statusFilter.setPlaceholder("Filter");
		statusFilter.setClearButtonVisible(true);
		statusFilter.setWidth("100%");
		statusFilter.addValueChangeListener(
			event -> gridListDataView.addFilter(orderDTO -> areStatusesEqual(orderDTO, statusFilter)));
		filterRow.getCell(statusColumn).setComponent(statusFilter);
	}

	private boolean areStatusesEqual(OrderDTO orderDTO, ComboBox<String> statusFilter) {
		String statusFilterValue = statusFilter.getValue();
		if (statusFilterValue != null) {
			return StringUtils.equals(orderDTO.getStatus(), statusFilterValue);
		}
		return true;
	}

	private List<OrderDTO> getOrders() {
		return orderService.getOrders();
	}

}
