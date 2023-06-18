package com.onlinemarketplace.reviewService.service;


import com.onlinemarketplace.productCatalogService.entity.Product;
import com.onlinemarketplace.productCatalogService.exception.ProductDetailsNotFound;
import com.onlinemarketplace.productCatalogService.repository.ProductRepository;
import com.onlinemarketplace.reviewService.constant.ReviewConstants;
import com.onlinemarketplace.reviewService.entity.Review;
import com.onlinemarketplace.reviewService.exception.ReviewNotFoundException;
import com.onlinemarketplace.reviewService.repository.ReviewRepository;
import com.onlinemarketplace.reviewService.requestData.ReviewRequestObject;
import com.onlinemarketplace.reviewService.responseData.CreateReviewResponseData;
import com.onlinemarketplace.reviewService.responseData.GetReviewResponseData;
import com.onlinemarketplace.reviewService.responseData.ReviewResponseObject;
import com.onlinemarketplace.reviewService.responseData.UpdateReviewResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository ;
    private ProductRepository productCatalogRepository ;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductRepository productCatalogRepository) {
        this.reviewRepository = reviewRepository;
        this.productCatalogRepository = productCatalogRepository;
    }

    @Override
    public CreateReviewResponseData createReview(String productId, ReviewRequestObject reviewRequestObject) {

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

        reviewRepository.save(review);
        log.info("review service: review has saved successfully");


        // sending response in proper format with product details and list of reviews
        CreateReviewResponseData responseData = new CreateReviewResponseData();

        responseData.setId(product.getId());
        responseData.setName(product.getName());
        responseData.setDescription(product.getDescription());
        responseData.setPrice(product.getPrice());
        responseData.setCategory(product.getCategory());
        responseData.setImage(product.getImage());
        responseData.setStockQuantity(product.getStockQuantity());
        responseData.setReviewResponseObject(reviewResponseObject);
        return responseData ;

    }

    @Override
    public UpdateReviewResponseData updateReview(String productId, ReviewRequestObject reviewRequestObject, String reviewId) {

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

        reviewRepository.save(review);
        log.info("review has updated successfully");


        // sending response in proper format with product details and list of reviews
        UpdateReviewResponseData responseData = new UpdateReviewResponseData();

        responseData.setId(product.getId());
        responseData.setName(product.getName());
        responseData.setDescription(product.getDescription());
        responseData.setPrice(product.getPrice());
        responseData.setCategory(product.getCategory());
        responseData.setImage(product.getImage());
        responseData.setStockQuantity(product.getStockQuantity());
        responseData.setReviewResponseObject(reviewResponseObject);
        return responseData ;

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

    @Override
    public GetReviewResponseData getReviews(String productId) {


        Optional<Product> byId1 = productCatalogRepository.findById(productId);
        if (byId1.isEmpty()) {
            log.error("review service: product does not exist with the given id anymore");
            throw new ProductDetailsNotFound("product details does not exist with the given id anymore");
        }
        Product product = byId1.get();

        List<Review> reviews = product.getReviews();
        log.info("review service: all reviews has been fetched successfully from the database");

        //converting to entity into dto
        List<ReviewResponseObject> collect = reviews.stream().map(review -> {

            ReviewResponseObject reviewResponseObject = new ReviewResponseObject();

            reviewResponseObject.setReviewId(review.getReviewId());
            reviewResponseObject.setRating(review.getRating());
            reviewResponseObject.setUserId(review.getUserId());
            reviewResponseObject.setUserName(review.getUserName());
            reviewResponseObject.setComment(review.getComment());
            return reviewResponseObject;
        }).collect(Collectors.toList());


        // sending response in proper format with product details and list of reviews
        GetReviewResponseData getReviewResponseData = new GetReviewResponseData();

        getReviewResponseData.setId(product.getId());
        getReviewResponseData.setName(product.getName());
        getReviewResponseData.setDescription(product.getDescription());
        getReviewResponseData.setPrice(product.getPrice());
        getReviewResponseData.setCategory(product.getCategory());
        getReviewResponseData.setImage(product.getImage());
        getReviewResponseData.setStockQuantity(product.getStockQuantity());
        getReviewResponseData.setReviewResponseObjects(collect);

        log.info("review service: all reviews has been returned successfully");
        return getReviewResponseData ;

    }

}
