package com.onlinemarketplace.reviewService.service;

import com.onlinemarketplace.reviewService.requestData.ReviewRequestObject;
import com.onlinemarketplace.reviewService.responseData.CreateReviewResponseData;
import com.onlinemarketplace.reviewService.responseData.GetReviewResponseData;
import com.onlinemarketplace.reviewService.responseData.ReviewResponseObject;
import com.onlinemarketplace.reviewService.responseData.UpdateReviewResponseData;

public interface ReviewService {

    CreateReviewResponseData createReview(String productId, ReviewRequestObject reviewRequestObject);

    UpdateReviewResponseData updateReview(String productId, ReviewRequestObject reviewRequestObject, String reviewId);

    String deleteReview(String productId,String reviewId);

    GetReviewResponseData getReviews(String productId);
}
