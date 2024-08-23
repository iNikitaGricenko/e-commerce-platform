package com.wolfhack.service.review.service;

import com.wolfhack.service.review.adapter.database.ReviewDatabaseAdapter;
import com.wolfhack.service.review.mapper.ReviewMapper;
import com.wolfhack.service.review.model.domain.Review;
import com.wolfhack.service.review.model.dto.ReviewCreatedEvent;
import com.wolfhack.service.review.model.dto.ReviewRequestDTO;
import com.wolfhack.service.review.model.dto.ReviewResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ReviewServiceTest {

	@Mock
	private ReviewMapper reviewMapper;

	@Mock
	private ReviewDatabaseAdapter reviewDatabaseAdapter;

	@Mock
	private EventService eventService;

	@InjectMocks
	private ReviewService reviewService;

	private Review review;

	private ReviewRequestDTO reviewRequestDTO;

	private ReviewResponseDTO reviewResponseDTO;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		reviewRequestDTO = new ReviewRequestDTO(1L, 1L, "Great product!", 9);
		review = new Review();
		review.setId(1L);
		review.setProductId(1L);
		review.setUserId(1L);
		review.setComment("Great product!");
		review.setRating(9);
		review.setCreatedAt(LocalDateTime.now());

		reviewResponseDTO = new ReviewResponseDTO(1L, 1L, 1L, "Great product!", 9, review.getCreatedAt());
	}

	@Test
	void createReview_ShouldSaveReviewAndSendEvent() {
		when(reviewMapper.toModel(reviewRequestDTO)).thenReturn(review);
		when(reviewDatabaseAdapter.save(review)).thenReturn(1L);

		Long savedReviewId = reviewService.createReview(reviewRequestDTO);

		assertEquals(1L, savedReviewId);

		ArgumentCaptor<ReviewCreatedEvent> eventCaptor = ArgumentCaptor.forClass(ReviewCreatedEvent.class);
		verify(eventService).sendEvent(eq("new-review"), eventCaptor.capture());
		ReviewCreatedEvent capturedEvent = eventCaptor.getValue();

		assertEquals(1L, capturedEvent.reviewId());
		assertEquals(1L, capturedEvent.productId());
		assertEquals(1L, capturedEvent.userId());

		verify(reviewDatabaseAdapter).save(review);
	}

	@Test
	void getReviewsByProductId_ShouldReturnReviewList() {
		when(reviewDatabaseAdapter.getByProduct(1L)).thenReturn(List.of(review));
		when(reviewMapper.toResponse(review)).thenReturn(reviewResponseDTO);

		List<ReviewResponseDTO> reviews = reviewService.getReviewsByProductId(1L);

		assertNotNull(reviews);
		assertEquals(1, reviews.size());
		assertEquals(reviewResponseDTO, reviews.get(0));

		verify(reviewDatabaseAdapter).getByProduct(1L);
		verify(reviewMapper).toResponse(review);
	}

	@Test
	void getReviewsByUserId_ShouldReturnReviewList() {
		when(reviewDatabaseAdapter.getByUser(1L)).thenReturn(List.of(review));
		when(reviewMapper.toResponse(review)).thenReturn(reviewResponseDTO);

		List<ReviewResponseDTO> reviews = reviewService.getReviewsByUserId(1L);

		assertNotNull(reviews);
		assertEquals(1, reviews.size());
		assertEquals(reviewResponseDTO, reviews.get(0));

		verify(reviewDatabaseAdapter).getByUser(1L);
		verify(reviewMapper).toResponse(review);
	}

	@Test
	void getReviewById_ShouldReturnReview() {
		when(reviewDatabaseAdapter.getById(1L)).thenReturn(review);
		when(reviewMapper.toResponse(review)).thenReturn(reviewResponseDTO);

		ReviewResponseDTO responseDTO = reviewService.getReviewById(1L);

		assertNotNull(responseDTO);
		assertEquals(reviewResponseDTO, responseDTO);

		verify(reviewDatabaseAdapter).getById(1L);
		verify(reviewMapper).toResponse(review);
	}

	@Test
	void deleteReview_ShouldDeleteReviewAndSendEvent() {
		doNothing().when(reviewDatabaseAdapter).delete(1L);

		reviewService.deleteReview(1L);

		verify(reviewDatabaseAdapter).delete(1L);
		verify(eventService).sendEvent("review-deleted", 1L);
	}

}