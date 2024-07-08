package com.security.rolepermissionexample.product.service.impl;

import com.security.rolepermissionexample.product.exception.ProductAlreadyExistException;
import com.security.rolepermissionexample.product.exception.ProductNotFoundException;
import com.security.rolepermissionexample.product.model.Product;
import com.security.rolepermissionexample.product.model.dto.request.ProductUpdateRequest;
import com.security.rolepermissionexample.product.model.entity.ProductEntity;
import com.security.rolepermissionexample.product.model.mapper.ProductEntityToProductMapper;
import com.security.rolepermissionexample.product.model.mapper.ProductUpdateRequestToProductEntityMapper;
import com.security.rolepermissionexample.product.repository.ProductRepository;
import com.security.rolepermissionexample.product.service.ProductUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductUpdateServiceImpl implements ProductUpdateService {

    private final ProductRepository productRepository;

    private final ProductUpdateRequestToProductEntityMapper productUpdateRequestToProductEntityMapper =
            ProductUpdateRequestToProductEntityMapper.initialize();

    private final ProductEntityToProductMapper productEntityToProductMapper =
            ProductEntityToProductMapper.initialize();

    @Override
    public Product updateProductById(String productId, ProductUpdateRequest productUpdateRequest) {

        checkProductNameUniqueness(productUpdateRequest.getName());

        final ProductEntity productEntityToBeUpdate = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("With given productID = " + productId));

        productUpdateRequestToProductEntityMapper.mapForUpdating(productEntityToBeUpdate, productUpdateRequest);

        ProductEntity updatedProductEntity = productRepository.save(productEntityToBeUpdate);

        return productEntityToProductMapper.map(updatedProductEntity);

    }

    private void checkProductNameUniqueness(final String productName) {
        if (productRepository.existsProductEntityByName(productName)) {
            throw new ProductAlreadyExistException("With given product name = " + productName);
        }
    }

}
