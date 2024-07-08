package com.security.rolepermissionexample.product.service.impl;

import com.security.rolepermissionexample.base.AbstractBaseServiceTest;
import com.security.rolepermissionexample.common.model.CustomPage;
import com.security.rolepermissionexample.common.model.CustomPaging;
import com.security.rolepermissionexample.product.exception.ProductNotFoundException;
import com.security.rolepermissionexample.product.model.Product;
import com.security.rolepermissionexample.product.model.dto.request.ProductPagingRequest;
import com.security.rolepermissionexample.product.model.entity.ProductEntity;
import com.security.rolepermissionexample.product.model.mapper.ListProductEntityToListProductMapper;
import com.security.rolepermissionexample.product.model.mapper.ProductEntityToProductMapper;
import com.security.rolepermissionexample.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductReadServiceImplTest extends AbstractBaseServiceTest {

    @InjectMocks
    private ProductReadServiceImpl productReadService;

    @Mock
    private ProductRepository productRepository;

    private final ProductEntityToProductMapper productEntityToProductMapper = ProductEntityToProductMapper.initialize();

    private final ListProductEntityToListProductMapper listProductEntityToListProductMapper =
            ListProductEntityToListProductMapper.initialize();

    @Test
    void givenProductEntity_whenFindProductById_thenReturnProduct() {

        // Given
        String productId = "1";
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productId);

        Product expected = productEntityToProductMapper.map(productEntity);

        // When
        when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity));

        // Then
        Product result = productReadService.getProductById(productId);

        assertNotNull(result);
        assertEquals(expected.getId(), result.getId());

        // Verify
        verify(productRepository, times(1)).findById(productId);

    }

    @Test
    void givenProductEntity_whenProductNotFound_thenThrowProductNotFoundException() {

        // Given
        String productId = "1";

        // When
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Then
        assertThrows(ProductNotFoundException.class, () -> productReadService.getProductById(productId));

        // Verify
        verify(productRepository, times(1)).findById(productId);

    }

    @Test
    void givenProductPagingRequest_WhenProductPageList_ThenReturnCustomPageProductList() {

        // Given
        ProductPagingRequest pagingRequest = ProductPagingRequest.builder()
                .pagination(
                        CustomPaging.builder()
                                .pageSize(1)
                                .pageNumber(1)
                                .build()
                ).build();

        Page<ProductEntity> productEntityPage = new PageImpl<>(Collections.singletonList(new ProductEntity()));

        List<Product> products = listProductEntityToListProductMapper.toProductList(productEntityPage.getContent());

        CustomPage<Product> expected = CustomPage.of(products, productEntityPage);

        // When
        when(productRepository.findAll(any(Pageable.class))).thenReturn(productEntityPage);

        // Then
        CustomPage<Product> result = productReadService.getProducts(pagingRequest);

        assertNotNull(result);
        assertFalse(result.getContent().isEmpty());
        assertEquals(expected.getPageNumber(), result.getPageNumber());
        assertEquals(expected.getContent().get(0).getId(), result.getContent().get(0).getId());
        assertEquals(expected.getTotalPageCount(), result.getTotalPageCount());
        assertEquals(expected.getTotalElementCount(), result.getTotalElementCount());

        // Verify
        verify(productRepository, times(1)).findAll(any(Pageable.class));

    }

    @Test
    void givenProductPagingRequest_WhenNoProductPageList_ThenThrowProductNotFoundException() {

        // Given
        ProductPagingRequest pagingRequest = ProductPagingRequest.builder()
                .pagination(
                        CustomPaging.builder()
                                .pageSize(1)
                                .pageNumber(1)
                                .build()
                ).build();

        Page<ProductEntity> productEntityPage = new PageImpl<>(Collections.emptyList());

        // When
        when(productRepository.findAll(any(Pageable.class))).thenReturn(productEntityPage);

        // Then
        assertThrows(ProductNotFoundException.class, () -> productReadService.getProducts(pagingRequest));

        // Verify
        verify(productRepository, times(1)).findAll(any(Pageable.class));

    }

}