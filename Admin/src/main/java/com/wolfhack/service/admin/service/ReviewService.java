package com.wolfhack.service.admin.service;

import com.wolfhack.service.admin.adapter.client.ReviewClient;
import com.wolfhack.service.admin.mapper.ReviewMapper;
import com.wolfhack.service.admin.model.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewClient reviewClient;

	private final ReviewMapper reviewMapper;

	public List<Review> getAllReviews() {
		return reviewClient.getAllReviews()
			.stream()
			.map(reviewMapper::toModel)
			.toList();
	}

	public List<Review> getReviewsByProduct(Long id) {
		return reviewClient.getReviewsByProduct(id)
			.stream()
			.map(reviewMapper::toModel)
			.toList();
	}

	public List<Review> getReviewsByUser(Long id) {
		return reviewClient.getReviewsByUser(id)
			.stream()
			.map(reviewMapper::toModel)
			.toList();
	}

	public Review getReview(Long id) {
		return reviewMapper.toModel(
			reviewClient.getReview(id)
		);
	}


}
