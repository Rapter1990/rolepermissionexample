package com.security.rolepermissionexample.product.service.impl;

import com.security.rolepermissionexample.base.AbstractBaseServiceTest;
import com.security.rolepermissionexample.product.exception.ProductNotFoundException;
import com.security.rolepermissionexample.product.model.entity.ProductEntity;
import com.security.rolepermissionexample.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductDeleteServiceImplTest extends AbstractBaseServiceTest {

    @InjectMocks
    private ProductDeleteServiceImpl productDeleteService;

    @Mock
    private ProductRepository productRepository;


    @Test
    void givenProductId_whenDeleteProduct_thenReturnProductDeleted() {

        // Given
        String productId = "1";
        ProductEntity existingProductEntity = new ProductEntity();
        existingProductEntity.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProductEntity));
        doNothing().when(productRepository).delete(existingProductEntity);

        // When
        productDeleteService.deleteProductById(productId);

        // Then
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).delete(existingProductEntity);

    }

    @Test
    void givenProductId_whenProductNotFound_thenThrowProductNotFoundException() {

        // Given
        String productId = "1";
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(ProductNotFoundException.class, () -> productDeleteService.deleteProductById(productId));

        // Verify
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, never()).delete(any());

    }

}