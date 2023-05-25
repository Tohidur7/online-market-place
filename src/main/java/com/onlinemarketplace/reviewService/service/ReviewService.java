package com.onlinemarketplace.reviewService.service;

import com.onlinemarketplace.reviewService.requestData.ReviewRequestObject;
import com.onlinemarketplace.reviewService.responseData.ReviewResponseObject;

public interface ReviewService {

    ReviewResponseObject createReview(String productId,ReviewRequestObject reviewRequestObject);

    ReviewResponseObject updateReview(String productId,ReviewRequestObject reviewRequestObject, String reviewId);

    String deleteReview(String productId,String reviewId);
}
