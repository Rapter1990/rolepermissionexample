package com.security.rolepermissionexample.product.service.impl;

import com.security.rolepermissionexample.base.AbstractBaseServiceTest;
import com.security.rolepermissionexample.product.exception.ProductAlreadyExistException;
import com.security.rolepermissionexample.product.exception.ProductNotFoundException;
import com.security.rolepermissionexample.product.model.Product;
import com.security.rolepermissionexample.product.model.dto.request.ProductUpdateRequest;
import com.security.rolepermissionexample.product.model.entity.ProductEntity;
import com.security.rolepermissionexample.product.model.mapper.ProductEntityToProductMapper;
import com.security.rolepermissionexample.product.model.mapper.ProductUpdateRequestToProductEntityMapper;
import com.security.rolepermissionexample.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductUpdateServiceImplTest extends AbstractBaseServiceTest {

    @InjectMocks
    private ProductUpdateServiceImpl productUpdateService;

    @Mock
    private ProductRepository productRepository;

    private ProductUpdateRequestToProductEntityMapper productUpdateRequestToProductEntityMapper =
            ProductUpdateRequestToProductEntityMapper.initialize();

    private ProductEntityToProductMapper productEntityToProductMapper =
            ProductEntityToProductMapper.initialize();

    @Test
    void givenProductUpdateRequest_whenProductUpdated_thenReturnProduct() {

        // Given
        String productId = "1";
        String newProductName = "New Product Name";

        ProductUpdateRequest productUpdateRequest = ProductUpdateRequest.builder()
                .name(newProductName)
                .amount(BigDecimal.valueOf(5))
                .unitPrice(BigDecimal.valueOf(12))
                .build();

        ProductEntity existingProductEntity = ProductEntity.builder()
                .id(productId)
                .name(productUpdateRequest.getName())
                .unitPrice(productUpdateRequest.getUnitPrice())
                .amount(productUpdateRequest.getAmount())
                .build();

        productUpdateRequestToProductEntityMapper.mapForUpdating(existingProductEntity,productUpdateRequest);

        Product expected = productEntityToProductMapper.map(existingProductEntity);

        // When
        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProductEntity));
        when(productRepository.existsProductEntityByName(newProductName)).thenReturn(false);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(existingProductEntity);

        // Then
        Product updatedProduct = productUpdateService.updateProductById(productId, productUpdateRequest);

        // Then
        assertNotNull(updatedProduct);
        assertEquals(expected.getId(), updatedProduct.getId());
        assertEquals(expected.getName(), updatedProduct.getName());
        assertEquals(expected.getAmount(), updatedProduct.getAmount());
        assertEquals(expected.getUnitPrice(), updatedProduct.getUnitPrice());

        // Verify
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).existsProductEntityByName(newProductName);
        verify(productRepository, times(1)).save(any(ProductEntity.class));

    }

    @Test
    void givenProductUpdateRequest_whenProductNotFound_thenThrowProductNotFoundException() {

        // Given
        String productId = "1";
        ProductUpdateRequest productUpdateRequest = new ProductUpdateRequest();

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(ProductNotFoundException.class, () -> productUpdateService.updateProductById(productId, productUpdateRequest));

        // Verify
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, never()).existsProductEntityByName(anyString());
        verify(productRepository, never()).save(any(ProductEntity.class));

    }

    @Test
    void givenProductUpdateRequest_whenProductAlreadyExist_thenThrowProductAlreadyExistException() {

        // Given
        String productId = "1";
        String existingProductName = "Existing Product";
        ProductUpdateRequest productUpdateRequest = new ProductUpdateRequest();
        productUpdateRequest.setName(existingProductName);

        ProductEntity existingProductEntity = new ProductEntity();
        existingProductEntity.setId(productId);
        existingProductEntity.setName(existingProductName);

        when(productRepository.existsProductEntityByName(existingProductName)).thenReturn(true);

        // When/Then
        assertThrows(ProductAlreadyExistException.class, () -> productUpdateService.updateProductById(productId, productUpdateRequest));

        // Verify
        verify(productRepository, times(1)).existsProductEntityByName(existingProductName);
        verify(productRepository, never()).findById(productId);
        verify(productRepository, never()).save(any(ProductEntity.class));

    }

}