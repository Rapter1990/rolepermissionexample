package com.security.rolepermissionexample.product.repository;

import com.security.rolepermissionexample.product.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    boolean existsProductEntityByName(final String name);

}
