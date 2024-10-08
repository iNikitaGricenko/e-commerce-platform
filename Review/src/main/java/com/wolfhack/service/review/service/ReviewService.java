package com.wolfhack.service.review.service;

import com.wolfhack.service.review.adapter.database.ReviewDatabaseAdapter;
import com.wolfhack.service.review.mapper.ReviewMapper;
import com.wolfhack.service.review.model.domain.Review;
import com.wolfhack.service.review.model.dto.ReviewCreatedEvent;
import com.wolfhack.service.review.model.dto.ReviewRequestDTO;
import com.wolfhack.service.review.model.dto.ReviewResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewMapper reviewMapper;

	private final ReviewDatabaseAdapter reviewDatabaseAdapter;

	private final EventService eventService;

    public Long createReview(ReviewRequestDTO dto) {
		Review review = reviewMapper.toModel(dto);

        review.setCreatedAt(LocalDateTime.now());

	    Long saved = reviewDatabaseAdapter.save(review);

	    eventService.sendEvent(
		    "new-review",
		    new ReviewCreatedEvent(
			    saved,
			    review.getProductId(),
			    review.getUserId())
	    );

	    return saved;
    }

    public List<ReviewResponseDTO> getReviewsByProductId(Long productId) {
	    return reviewDatabaseAdapter.getByProduct(productId)
		    .stream()
		    .map(reviewMapper::toResponse)
		    .toList();
    }

    public List<ReviewResponseDTO> getReviewsByUserId(Long userId) {
	    return reviewDatabaseAdapter.getByUser(userId)
		    .stream()
		    .map(reviewMapper::toResponse)
		    .toList();
    }

    public ReviewResponseDTO getReviewById(Long id) {
	    Review review = reviewDatabaseAdapter.getById(id);

	    return reviewMapper.toResponse(review);
    }

    public void deleteReview(Long id) {
        reviewDatabaseAdapter.delete(id);

	    eventService.sendEvent("review-deleted", id);
    }

}
