package com.wolfhack.service.review.persistence;

import com.wolfhack.common.exception.NotFoundException;
import com.wolfhack.common.wrapper.DomainPage;
import com.wolfhack.service.review.adapter.database.ReviewDatabaseAdapter;
import com.wolfhack.service.review.mapper.ReviewMapper;
import com.wolfhack.service.review.model.domain.Review;
import com.wolfhack.service.review.model.entity.ReviewEntity;
import com.wolfhack.service.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewDatabaseGateway implements ReviewDatabaseAdapter {

	private final ReviewRepository reviewRepository;

	private final ReviewMapper reviewMapper;

	@Override
	public Long save(Review model) {
		ReviewEntity entity = reviewMapper.toEntity(model);

		return reviewRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, Review model) {
		ReviewEntity updated = reviewRepository.findById(id)
			.map(categoryEntity -> reviewMapper.partialUpdate(model, categoryEntity))
			.orElseThrow(NotFoundException::new);

		return reviewRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, Review model) {
		if (!exists(id)) {
			throw new NotFoundException("Review does not exist");
		}

		ReviewEntity entity = reviewMapper.toEntity(model);
		reviewMapper.update(model, entity);
		return reviewRepository.save(entity).getId();
	}

	@Override
	public Review getById(Long id) {
		return reviewRepository
			.findById(id)
			.map(reviewMapper::toModel)
			.orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return reviewRepository.existsById(id);
	}

	@Override
	public Collection<Review> getById(Collection<Long> ids) {
		return reviewRepository
			.findAllById(ids)
			.stream()
			.map(reviewMapper::toModel)
			.toList();
	}

	@Override
	public List<Review> getAll() {
		return reviewRepository
			.findAll()
			.stream()
			.map(reviewMapper::toModel)
			.toList();
	}

	@Override
	public DomainPage<Review> getPage(Pageable pageable) {
		Page<Review> page = reviewRepository
			.findAll(pageable)
			.map(reviewMapper::toModel);

		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		reviewRepository.deleteById(id);
	}

	@Override
	public List<Review> getByProduct(Long productId) {
		return reviewRepository
			.findByProductId(productId)
			.stream()
			.map(reviewMapper::toModel)
			.toList();
	}

	@Override
	public List<Review> getByUser(Long userId) {
		return reviewRepository
			.findByUserId(userId)
			.stream()
			.map(reviewMapper::toModel)
			.toList();
	}

}
