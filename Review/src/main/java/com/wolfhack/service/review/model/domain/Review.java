package com.wolfhack.service.review.model.domain;

import com.wolfhack.common.model.domain.DomainModel;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.wolfhack.service.review.model.entity.ReviewEntity}
 */
@Data
public class Review implements DomainModel, Serializable {

	private Long id;

	private Long productId;

	private Long userId;

	private String comment;

	private Integer rating;

	private LocalDateTime createdAt;

}