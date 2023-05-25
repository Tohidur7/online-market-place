package com.onlinemarketplace.productCatalogService.exception;

import com.onlinemarketplace.productCatalogService.responseData.ProductErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ProductCatalogExceptionHandler extends ResponseEntityExceptionHandler {

    //handle specific exception
    @ExceptionHandler(ProductDetailsNotFound.class)
    public ResponseEntity<ProductErrorResponse> handleProductDetailsNotFoundException(ProductDetailsNotFound exception , WebRequest webRequest) {

        ProductErrorResponse productErrorResponse = new ProductErrorResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(productErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ProductErrorResponse> handleAccessDeniedException(AccessDeniedException exception , WebRequest webRequest) {

        ProductErrorResponse productErrorResponse = new ProductErrorResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(productErrorResponse, HttpStatus.UNAUTHORIZED);
    }
}
