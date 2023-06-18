package com.onlinemarketplace.productCatalogService.controller;

import com.onlinemarketplace.productCatalogService.requestData.ProductRequestData;
import com.onlinemarketplace.productCatalogService.responseData.AllProductResponseData;
import com.onlinemarketplace.productCatalogService.responseData.ProductResponseData;
import com.onlinemarketplace.productCatalogService.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class ProductController {

    private ProductService productCatalogService;

    @Autowired
    public ProductController(ProductService productCatalogService) {
        this.productCatalogService = productCatalogService;
    }

    @GetMapping(path = "/products")
    public ResponseEntity<AllProductResponseData> getAllListedProducts() {
        log.info("get controller has started");
        return new ResponseEntity<>(productCatalogService.getAllListedProducts(), HttpStatus.OK) ;
    }

    @PostMapping(path = "/products")
    public ResponseEntity<ProductResponseData> createProduct(@RequestBody ProductRequestData productRequestData) {
        log.info("post controller has started");
        return new ResponseEntity<>(productCatalogService.createProduct(productRequestData), HttpStatus.CREATED) ;
    }

    @PutMapping(path = "/products/{productId}")
    public ResponseEntity<ProductResponseData> updateProductDetails(@PathVariable(name = "productId") String productId, @RequestBody ProductRequestData productRequestData) {
        log.info("put controller has started");
        return new ResponseEntity<>(productCatalogService.updateProductDetails(productRequestData,productId), HttpStatus.OK) ;
    }

    @DeleteMapping(path = "/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "productId") String productId) {
        log.info("delete controller has started");
        return new ResponseEntity<>(productCatalogService.deleteProduct(productId), HttpStatus.OK) ;
    }


}
