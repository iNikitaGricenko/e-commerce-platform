package com.wolfhack.service.review.persistence;

import com.wolfhack.service.review.mapper.ReviewMapper;
import com.wolfhack.service.review.model.domain.Review;
import com.wolfhack.service.review.model.entity.ReviewEntity;
import com.wolfhack.service.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ReviewDatabaseGatewayTest {

	@Autowired
	private ReviewDatabaseGateway reviewDatabaseGateway;

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private ReviewMapper reviewMapper;

	@BeforeEach
	void setUp() {
		reviewRepository.deleteAll();
	}

	@Test
	void testSaveReview() {
		ReviewEntity entity = new ReviewEntity();
		entity.setUserId(1L);
		entity.setProductId(1L);
		entity.setComment("Great product!");
		entity.setRating(5);
		entity.setCreatedAt(LocalDateTime.now());

		Review review = reviewMapper.toModel(entity);

		Long id = reviewDatabaseGateway.save(review);

		assertThat(id).isNotNull();

		ReviewEntity savedReview = reviewRepository.findById(id).orElse(null);

		assertThat(savedReview).isNotNull();
		assertThat(savedReview.getComment()).isEqualTo("Great product!");
	}

	@Test
	void testGetReviewById() {
		ReviewEntity entity1 = new ReviewEntity();
		entity1.setUserId(1L);
		entity1.setProductId(1L);
		entity1.setComment("Nice product!");
		entity1.setRating(4);
		entity1.setCreatedAt(LocalDateTime.now());

		ReviewEntity entity2 = new ReviewEntity();
		entity2.setUserId(2L);
		entity2.setProductId(1L);
		entity2.setComment("Amazing product!");
		entity2.setRating(5);
		entity2.setCreatedAt(LocalDateTime.now());

		reviewRepository.save(entity1);
		reviewRepository.save(entity2);

		List<Review> reviews = reviewDatabaseGateway.getByProduct(1L);

		assertThat(reviews.size()).isEqualTo(2);
	}

	@Test
	void testDeleteReview() {
		ReviewEntity entity = new ReviewEntity();
		entity.setUserId(1L);
		entity.setProductId(1L);
		entity.setComment("Great product!");
		entity.setRating(9);
		entity.setCreatedAt(LocalDateTime.now());

		reviewRepository.save(entity);

		reviewDatabaseGateway.delete(entity.getId());

		boolean exists = reviewRepository.existsById(entity.getId());
		assertThat(exists).isFalse();
	}

}