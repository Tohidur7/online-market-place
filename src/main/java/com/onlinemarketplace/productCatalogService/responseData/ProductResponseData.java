package com.onlinemarketplace.productCatalogService.responseData;

import com.onlinemarketplace.reviewService.responseData.ReviewResponseObject;

import java.util.List;

public class ProductResponseData {

    private String id ;

    private String name ;

    private String description ;

    private Double price ;

    private String category ;

    private String image ;

    private Integer stockQuantity ;

    private List<ReviewResponseObject> reviewResponseObjects ;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public List<ReviewResponseObject> getReviewResponseObjects() {
        return reviewResponseObjects;
    }

    public void setReviewResponseObjects(List<ReviewResponseObject> reviewResponseObjects) {
        this.reviewResponseObjects = reviewResponseObjects;
    }
}
