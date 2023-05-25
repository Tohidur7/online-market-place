package com.onlinemarketplace.reviewService.exception;

import com.onlinemarketplace.reviewService.responseData.ReviewErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ReviewServiceExceptionHandler extends ResponseEntityExceptionHandler {

    // handle specific exception
    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ReviewErrorResponse> handleReviewNotFoundException(ReviewNotFoundException exception, WebRequest webRequest) {
        ReviewErrorResponse reviewErrorResponse = new ReviewErrorResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<ReviewErrorResponse>(reviewErrorResponse, HttpStatus.NOT_FOUND);
    }


}
