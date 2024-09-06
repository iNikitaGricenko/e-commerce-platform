package com.wolfhack.service.admin.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.wolfhack.service.admin.model.domain.Review;
import com.wolfhack.service.admin.service.ReviewService;

@Route("reviews")
public class ReviewManagementView extends VerticalLayout {

	private final Grid<Review> reviewGrid = new Grid<>(Review.class);

	private final TextField searchField = new TextField("Search Reviews");

	private final Button approveButton = new Button("Approve");

	private final Button rejectButton = new Button("Reject");

	private final ReviewService reviewService;

	public ReviewManagementView(ReviewService reviewService) {
		this.reviewService = reviewService;
		setupGrid();
		setupToolbar();
		updateReviewList();
	}

	private void setupGrid() {
		reviewGrid.setColumns("id", "productId", "userId", "comment", "rating", "createdAt");
		reviewGrid.asSingleSelect().addValueChangeListener(event -> {
			// Optionally open a form or dialog to approve/reject the review
		});
		add(searchField, approveButton, rejectButton, reviewGrid);
		approveButton.addClickListener(e -> approveSelectedReview());
		rejectButton.addClickListener(e -> rejectSelectedReview());
	}

	private void setupToolbar() {
		searchField.setPlaceholder("Search Reviews");
		searchField.setClearButtonVisible(true);
		searchField.addValueChangeListener(e -> updateReviewList());
	}

	private void updateReviewList() {
		reviewGrid.setItems(reviewService.getAllReviews());
	}

	private void approveSelectedReview() {
		Review selected = reviewGrid.asSingleSelect().getValue();
		if (selected != null) {
			// Send approval status to backend
		}
	}

	private void rejectSelectedReview() {
		Review selected = reviewGrid.asSingleSelect().getValue();
		if (selected != null) {
			// Send rejection status to backend
		}
	}

}
