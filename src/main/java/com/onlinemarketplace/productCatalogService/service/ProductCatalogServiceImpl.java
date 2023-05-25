package com.onlinemarketplace.productCatalogService.service;

import com.onlinemarketplace.productCatalogService.constant.ProductConstants;
import com.onlinemarketplace.productCatalogService.entity.Product;
import com.onlinemarketplace.productCatalogService.exception.ProductDetailsNotFound;
import com.onlinemarketplace.productCatalogService.repository.ProductCatalogRepository;
import com.onlinemarketplace.productCatalogService.requestData.ProductRequestData;
import com.onlinemarketplace.productCatalogService.responseData.AllProductResponseData;
import com.onlinemarketplace.productCatalogService.responseData.ProductResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductCatalogServiceImpl implements ProductCatalogService {

    private ProductCatalogRepository productCatalogRepository ;

    @Autowired
    public ProductCatalogServiceImpl(ProductCatalogRepository productCatalogRepository) {
        this.productCatalogRepository = productCatalogRepository;
    }


    @Override
    public AllProductResponseData getAllListedProducts() {

        List<Product> products = productCatalogRepository.findAll();
        log.info("product details has fetched successfully from the database");
        List<ProductResponseData> productResponseDataList = products.stream().map(product -> {

            ProductResponseData productResponseData = new ProductResponseData();

            productResponseData.setId(product.getId());
            productResponseData.setName(product.getName());
            productResponseData.setDescription(product.getDescription());
            productResponseData.setPrice(product.getPrice());
            productResponseData.setCategory(product.getCategory());
            productResponseData.setImage(product.getImage());
            productResponseData.setStockQuantity(product.getStockQuantity());

            return productResponseData;
        }).collect(Collectors.toList());

        AllProductResponseData all = new AllProductResponseData();
        all.setProductResponseDataList(productResponseDataList);

        return  all ;
    }
    //todo make an api to find the products by product id. the response should contains list of reviews as well
    // todo make a different response object. it is better


    @Override
    public ProductResponseData createProduct(ProductRequestData productRequestData) {

        Product product = new Product();
        ProductResponseData productResponseData = new ProductResponseData();

        String temp="" ;

        temp = "product"+ UUID.randomUUID();
        product.setId(temp);
        productResponseData.setId(temp);

        temp = productRequestData.getName() ;
        if (temp != null) {
            product.setName(temp);
            productResponseData.setName(temp);
        }

        temp = productRequestData.getDescription();
        if (temp != null) {
            product.setDescription(temp);
            productResponseData.setDescription(temp);
        }

        if (productRequestData.getPrice() != 0) {
            product.setPrice(productRequestData.getPrice());
            productResponseData.setPrice(productRequestData.getPrice());
        }

        temp = productRequestData.getCategory();
        if (temp != null) {
            product.setCategory(temp);
            productResponseData.setCategory(temp);
        }

        temp = productRequestData.getImage();
        if (temp != null) {
            product.setImage(temp);
            productResponseData.setImage(temp);
        }

        if (productRequestData.getStockQuantity() != 0) {
            product.setStockQuantity(productRequestData.getStockQuantity());
            productResponseData.setStockQuantity(productRequestData.getStockQuantity());
        }

        productCatalogRepository.save(product);
        return productResponseData ;
    }

    @Override
    public ProductResponseData updateProductDetails(ProductRequestData productRequestData, String productId) {

        String temp = "" ;

        Optional<Product> byId = productCatalogRepository.findById(productId);
        if (byId.isEmpty()) {
            log.error(String.valueOf(byId));
            throw new ProductDetailsNotFound("product does not exist with the given id");
        }

        Product product = byId.get();
        ProductResponseData productResponseData = new ProductResponseData();

        temp = product.getId() ;
        if (temp!=null) {
            productResponseData.setId(temp);
        }

        temp = productRequestData.getName() ;
        if (temp != null) {
            product.setName(temp);
            productResponseData.setName(temp);
        }

        temp = productRequestData.getDescription() ;
        if (temp != null) {
            product.setDescription(temp);
            productResponseData.setDescription(temp);
        }

        if (productRequestData.getPrice() != 0) {
            product.setPrice(productRequestData.getPrice());
            productResponseData.setPrice(productRequestData.getPrice());
        }

        temp = productRequestData.getCategory() ;
        if (temp != null) {
            product.setCategory(temp);
            productResponseData.setCategory(temp);
        }

        temp = productRequestData.getImage() ;
        if (temp != null) {
            product.setImage(temp);
            productResponseData.setImage(temp);
        }

        if (productRequestData.getStockQuantity() != 0) {
            product.setStockQuantity(productRequestData.getStockQuantity());
            productResponseData.setStockQuantity(productRequestData.getStockQuantity());
        }

        productCatalogRepository.save(product);
        log.info("entity has saved");
        return productResponseData ;

    }

    @Override
    public String deleteProduct(String productId) {

        Optional<Product> byId = productCatalogRepository.findById(productId);
        if (byId.isEmpty()) {
            log.error(String.valueOf(byId));
            throw new ProductDetailsNotFound("product does not exist with the given id");
        }

        //todo before deleting the product catalog , the comments under it should also get deleted. make this method as transactional

        Product product = byId.get();
        productCatalogRepository.delete(product);
        log.info("entity deleted successfully");

        return ProductConstants.PRODUCT_DELETE_MESSAGE;
    }


}
