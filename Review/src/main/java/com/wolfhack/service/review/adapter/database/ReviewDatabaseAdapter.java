package com.wolfhack.service.review.adapter.database;

import com.wolfhack.common.adapter.database.DatabaseAdapter;
import com.wolfhack.service.review.model.domain.Review;

import java.util.List;

public interface ReviewDatabaseAdapter extends DatabaseAdapter<Review> {

	List<Review> getByProduct(Long productId);

	List<Review> getByUser(Long userId);

}
