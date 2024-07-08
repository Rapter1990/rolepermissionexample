package com.security.rolepermissionexample.product.service.impl;

import com.security.rolepermissionexample.product.exception.ProductNotFoundException;
import com.security.rolepermissionexample.product.model.entity.ProductEntity;
import com.security.rolepermissionexample.product.repository.ProductRepository;
import com.security.rolepermissionexample.product.service.ProductDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDeleteServiceImpl implements ProductDeleteService {

    private final ProductRepository productRepository;

    @Override
    public void deleteProductById(String productId) {

        final ProductEntity productEntityToBeDelete = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("With given productID = " + productId));

        productRepository.delete(productEntityToBeDelete);

    }

}
