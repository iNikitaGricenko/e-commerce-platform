package com.wolfhack.service.review.repository;

import com.wolfhack.service.review.model.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

	List<ReviewEntity> findByProductId(Long productId);

	List<ReviewEntity> findByUserId(Long userId);

}