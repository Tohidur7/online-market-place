package com.onlinemarketplace.reviewService.controller;

import com.onlinemarketplace.reviewService.requestData.ReviewRequestObject;
import com.onlinemarketplace.reviewService.responseData.CreateReviewResponseData;
import com.onlinemarketplace.reviewService.responseData.GetReviewResponseData;
import com.onlinemarketplace.reviewService.responseData.ReviewResponseObject;
import com.onlinemarketplace.reviewService.responseData.UpdateReviewResponseData;
import com.onlinemarketplace.reviewService.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(path = "/products/{productId}/reviews")
    public ResponseEntity<CreateReviewResponseData> createReviews(@PathVariable(name ="productId")String productId, @RequestBody ReviewRequestObject reviewRequestObject) {
        log.info("review service: post controller has started");
        return new ResponseEntity<>(reviewService.createReview(productId,reviewRequestObject), HttpStatus.CREATED);
    }

    @PutMapping(path = "/products/{productId}/reviews/{reviewId}")
    public ResponseEntity<UpdateReviewResponseData> updateReviews(@PathVariable(name ="productId")String productId, @RequestBody ReviewRequestObject reviewRequestObject, @PathVariable(name = "reviewId") String reviewId) {
        log.info("review service: put controller has started");
        return new ResponseEntity<>(reviewService.updateReview(productId, reviewRequestObject, reviewId), HttpStatus.OK);
    }

    @DeleteMapping(path = "/products/{productId}/reviews/{reviewId}")
    public ResponseEntity<String> deleteReviews( @PathVariable(name ="productId")String productId,  @PathVariable(name = "reviewId") String reviewId) {
        log.info("review service: delete controller has started");
        return new ResponseEntity<>(reviewService.deleteReview(productId, reviewId), HttpStatus.OK);
    }


    @GetMapping(path = "/products/{productId}/reviews")
    public ResponseEntity<GetReviewResponseData> getAllReviews(@PathVariable(name ="productId")String productId) {
        log.info("review service: get controller has started");
        return new ResponseEntity<>(reviewService.getReviews(productId), HttpStatus.OK);
    }

}
