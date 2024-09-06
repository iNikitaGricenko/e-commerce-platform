package com.wolfhack.service.admin.adapter.client;

import com.wolfhack.common.model.dto.ReviewDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "review")
public interface ReviewClient {

	@RequestMapping(
		method = RequestMethod.GET,
		value = "/api/reviews",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	List<ReviewDTO> getAllReviews();

	@RequestMapping(
		method = RequestMethod.GET,
		value = "/api/reviews/product/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	List<ReviewDTO> getReviewsByProduct(@PathVariable("id") Long id);

	@RequestMapping(
		method = RequestMethod.GET,
		value = "/api/reviews/user/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	List<ReviewDTO> getReviewsByUser(@PathVariable("id") Long id);

	@RequestMapping(
		method = RequestMethod.GET,
		value = "/api/reviews/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	ReviewDTO getReview(@PathVariable("id") Long id);

}
