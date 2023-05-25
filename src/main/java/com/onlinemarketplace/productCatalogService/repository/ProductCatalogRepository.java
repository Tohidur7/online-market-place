package com.onlinemarketplace.productCatalogService.repository;

import com.onlinemarketplace.productCatalogService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCatalogRepository extends JpaRepository<Product, String> {

}
