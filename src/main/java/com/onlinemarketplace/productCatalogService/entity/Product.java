package com.onlinemarketplace.productCatalogService.entity;

import com.onlinemarketplace.reviewService.entity.Review;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {

    @Id
    private String id;

    @Column(nullable = false)
    private String name ;

    @Column(nullable = false)
    private String description ;

    @Column(nullable = false)
    private Double price ;

    @Column(nullable = false)
    private String category ;

    @Column(nullable = false)
    private String image ;

    @Column(nullable = false)
    private Integer stockQuantity ;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Review> reviews ;



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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}
