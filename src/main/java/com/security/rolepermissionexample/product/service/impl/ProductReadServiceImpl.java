package com.security.rolepermissionexample.product.service.impl;

import com.security.rolepermissionexample.common.model.CustomPage;
import com.security.rolepermissionexample.product.exception.ProductNotFoundException;
import com.security.rolepermissionexample.product.model.Product;
import com.security.rolepermissionexample.product.model.dto.request.ProductPagingRequest;
import com.security.rolepermissionexample.product.model.entity.ProductEntity;
import com.security.rolepermissionexample.product.model.mapper.ListProductEntityToListProductMapper;
import com.security.rolepermissionexample.product.model.mapper.ProductEntityToProductMapper;
import com.security.rolepermissionexample.product.repository.ProductRepository;
import com.security.rolepermissionexample.product.service.ProductReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductReadServiceImpl implements ProductReadService {

    private final ProductRepository productRepository;

    private final ProductEntityToProductMapper productEntityToProductMapper = ProductEntityToProductMapper.initialize();

    private final ListProductEntityToListProductMapper listProductEntityToListProductMapper =
            ListProductEntityToListProductMapper.initialize();

    @Override
    public Product getProductById(String productId) {

        final ProductEntity productEntityFromDB = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("With given productID = " + productId));

        return productEntityToProductMapper.map(productEntityFromDB);
    }

    @Override
    public CustomPage<Product> getProducts(ProductPagingRequest productPagingRequest) {

        final Page<ProductEntity> productEntityPage = productRepository.findAll(productPagingRequest.toPageable());

        if (productEntityPage.getContent().isEmpty()) {
            throw new ProductNotFoundException("Couldn't find any Product");
        }

        final List<Product> productDomainModels = listProductEntityToListProductMapper
                .toProductList(productEntityPage.getContent());

        return CustomPage.of(productDomainModels, productEntityPage);

    }

}
