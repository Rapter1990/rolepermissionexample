package com.security.rolepermissionexample.product.service.impl;

import com.security.rolepermissionexample.product.exception.ProductAlreadyExistException;
import com.security.rolepermissionexample.product.model.Product;
import com.security.rolepermissionexample.product.model.dto.request.ProductCreateRequest;
import com.security.rolepermissionexample.product.model.entity.ProductEntity;
import com.security.rolepermissionexample.product.model.mapper.ProductCreateRequestToProductEntityMapper;
import com.security.rolepermissionexample.product.model.mapper.ProductEntityToProductMapper;
import com.security.rolepermissionexample.product.repository.ProductRepository;
import com.security.rolepermissionexample.product.service.ProductCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCreateServiceImpl implements ProductCreateService {

    private final ProductRepository productRepository;

    private final ProductCreateRequestToProductEntityMapper productCreateRequestToProductEntityMapper =
            ProductCreateRequestToProductEntityMapper.initialize();

    private final ProductEntityToProductMapper productEntityToProductMapper = ProductEntityToProductMapper.initialize();

    @Override
    public Product createProduct(ProductCreateRequest productCreateRequest) {

        checkUniquenessProductName(productCreateRequest.getName());

        final ProductEntity productEntityToBeSave = productCreateRequestToProductEntityMapper.mapForSaving(productCreateRequest);

        ProductEntity savedProductEntity = productRepository.save(productEntityToBeSave);

        return productEntityToProductMapper.map(savedProductEntity);

    }

    private void checkUniquenessProductName(final String productName) {
        if (productRepository.existsProductEntityByName(productName)) {
            throw new ProductAlreadyExistException("There is another product with given name: " + productName);
        }
    }

}
