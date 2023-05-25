package com.onlinemarketplace.reviewService.service;


import com.onlinemarketplace.productCatalogService.controller.ProductCatalogController;
import com.onlinemarketplace.productCatalogService.entity.Product;
import com.onlinemarketplace.productCatalogService.exception.ProductDetailsNotFound;
import com.onlinemarketplace.productCatalogService.repository.ProductCatalogRepository;
import com.onlinemarketplace.reviewService.constant.ReviewConstants;
import com.onlinemarketplace.reviewService.entity.Review;
import com.onlinemarketplace.reviewService.exception.ReviewNotFoundException;
import com.onlinemarketplace.reviewService.repository.ReviewRepository;
import com.onlinemarketplace.reviewService.requestData.ReviewRequestObject;
import com.onlinemarketplace.reviewService.responseData.ReviewResponseObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository ;
    private ProductCatalogRepository productCatalogRepository ;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductCatalogRepository productCatalogRepository) {
        this.reviewRepository = reviewRepository;
        this.productCatalogRepository = productCatalogRepository;
    }

    @Override
    public ReviewResponseObject createReview(String productId,ReviewRequestObject reviewRequestObject) {

        Optional<Product> byId = productCatalogRepository.findById(productId);
        if (byId.isEmpty()) {
            log.error("review service: product does not exist with the given id anymore");
            throw new ProductDetailsNotFound("product details does not exist with the given id anymore");
        }
        Product product = byId.get();


        String temp = "";

        Review review = new Review();
        ReviewResponseObject reviewResponseObject = new ReviewResponseObject();

        temp = "review:" + UUID.randomUUID();
        review.setReviewId(temp);
        reviewResponseObject.setReviewId(temp);


        temp = reviewRequestObject.getRating();
        if (temp!=null) {
            review.setRating(temp);
            reviewResponseObject.setRating(temp);
        }

        temp = reviewRequestObject.getUserId();
        if (temp!=null) {
            review.setUserId(temp);
            reviewResponseObject.setUserId(temp);
        }

        temp = reviewRequestObject.getUserName();
        if (temp!=null) {
            review.setUserName(temp);
            reviewResponseObject.setUserName(temp);
        }


        temp = reviewRequestObject.getComment();
        if (temp!=null) {
            review.setComment(temp);
            reviewResponseObject.setComment(temp);
        }

        review.setProduct(product);
        reviewResponseObject.setProduct(product);

        reviewRepository.save(review);
        log.info("review service: review has saved successfully");
        return reviewResponseObject ;
    }

    @Override
    public ReviewResponseObject updateReview(String productId,ReviewRequestObject reviewRequestObject, String reviewId) {

        Optional<Product> byId1 = productCatalogRepository.findById(productId);
        if (byId1.isEmpty()) {
            log.error("review service: product does not exist with the given id anymore");
            throw new ProductDetailsNotFound("product details does not exist with the given id anymore");
        }
        Product product = byId1.get();


        Optional<Review> byId = reviewRepository.findById(reviewId);
        if (byId.isEmpty()) {
            log.info("review service: review does not exist with the given id");
            throw new ReviewNotFoundException("review does not exist with the given id");
        }
        Review review = byId.get();
        ReviewResponseObject reviewResponseObject = new ReviewResponseObject();

        String temp = "";

        temp = reviewId ;
        reviewResponseObject.setReviewId(temp);

        temp = reviewRequestObject.getRating();
        if (temp!=null) {
            review.setRating(temp);
            reviewResponseObject.setRating(temp);
        }

        temp = reviewRequestObject.getUserId();
        if (temp!=null) {
            review.setUserId(temp);
            reviewResponseObject.setUserId(temp);
        }

        temp = reviewRequestObject.getUserName();
        if (temp!=null) {
            review.setUserName(temp);
            reviewResponseObject.setUserName(temp);
        }


        temp = reviewRequestObject.getComment();
        if (temp!=null) {
            review.setComment(temp);
            reviewResponseObject.setComment(temp);
        }

        review.setProduct(product);
        reviewResponseObject.setProduct(product);

        reviewRepository.save(review);
        log.info("review has updated successfully");
        return reviewResponseObject ;
    }

    @Override
    public String deleteReview(String productId, String reviewId) {

        Optional<Product> byId1 = productCatalogRepository.findById(productId);
        if (byId1.isEmpty()) {
            log.error("review service: product does not exist with the given id anymore");
            throw new ProductDetailsNotFound("product details does not exist with the given id anymore");
        }
        Product product = byId1.get();

        Optional<Review> byId = reviewRepository.findById(reviewId);
        if (byId.isEmpty()) {
            log.error("review service: review does not exist with the given id");
            throw new ReviewNotFoundException("review does not exist with the given id");
        }
        Review review = byId.get();
        reviewRepository.delete(review);
        log.info("review service: review has deleted successfully");
        return ReviewConstants.REVIEW_DELETE_MESSAGE ;
    }

}
