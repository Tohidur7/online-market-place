package com.onlinemarketplace.productCatalogService.service;

import com.onlinemarketplace.productCatalogService.requestData.ProductRequestData;
import com.onlinemarketplace.productCatalogService.responseData.AllProductResponseData;
import com.onlinemarketplace.productCatalogService.responseData.ProductResponseData;

public interface ProductService {

    AllProductResponseData getAllListedProducts();

    ProductResponseData createProduct(ProductRequestData productRequestData);

    ProductResponseData updateProductDetails(ProductRequestData productRequestData, String productId);

    String deleteProduct(String productId);
}
