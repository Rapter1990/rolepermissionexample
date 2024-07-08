package com.security.rolepermissionexample.product.service.impl;

import com.security.rolepermissionexample.base.AbstractBaseServiceTest;
import com.security.rolepermissionexample.product.exception.ProductAlreadyExistException;
import com.security.rolepermissionexample.product.model.Product;
import com.security.rolepermissionexample.product.model.dto.request.ProductCreateRequest;
import com.security.rolepermissionexample.product.model.entity.ProductEntity;
import com.security.rolepermissionexample.product.model.mapper.ProductCreateRequestToProductEntityMapper;
import com.security.rolepermissionexample.product.model.mapper.ProductEntityToProductMapper;
import com.security.rolepermissionexample.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductCreateServiceImplTest extends AbstractBaseServiceTest {

    @InjectMocks
    private ProductCreateServiceImpl productCreateService;

    @Mock
    private ProductRepository productRepository;

    private final ProductCreateRequestToProductEntityMapper productCreateRequestToProductEntityMapper =
            ProductCreateRequestToProductEntityMapper.initialize();

    private final ProductEntityToProductMapper productEntityToProductMapper = ProductEntityToProductMapper.initialize();

    @Test
    void givenProductCreateRequest_whenProductCreated_thenReturnProduct() {

        // Given
        String productName = "Test Product";
        ProductCreateRequest productCreateRequest = ProductCreateRequest.builder()
                .name(productName)
                .unitPrice(BigDecimal.valueOf(12))
                .amount(BigDecimal.valueOf(5))
                .build();

        ProductEntity productEntity = productCreateRequestToProductEntityMapper.mapForSaving(productCreateRequest);

        Product expected = productEntityToProductMapper.map(productEntity);

        // When
        when(productRepository.existsProductEntityByName(productName)).thenReturn(false);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);

        // Then
        Product createdProduct = productCreateService.createProduct(productCreateRequest);

        assertNotNull(createdProduct);
        assertEquals(expected.getName(), createdProduct.getName());
        assertEquals(expected.getAmount(), createdProduct.getAmount());
        assertEquals(expected.getUnitPrice(), createdProduct.getUnitPrice());

        // Verify
        verify(productRepository, times(1)).existsProductEntityByName(productName);
        verify(productRepository, times(1)).save(any(ProductEntity.class));

    }

    @Test
    void givenProductCreateRequest_whenProductAlreadyExists_ThenReturnProductAlreadyExistException() {

        // Given
        String productName = "Existing Product";
        ProductCreateRequest productCreateRequest = new ProductCreateRequest();
        productCreateRequest.setName(productName);

        // When
        when(productRepository.existsProductEntityByName(productName)).thenReturn(true);

        // Then
        ProductAlreadyExistException productAlreadyExistException =
                assertThrows(ProductAlreadyExistException.class, () -> productCreateService.createProduct(productCreateRequest));

        assertEquals("Product already exist!\n There is another product with given name: " + productName,
                productAlreadyExistException.getMessage());

        // Verify
        verify(productRepository, times(1)).existsProductEntityByName(productName);
        verify(productRepository, never()).save(any(ProductEntity.class));

    }

}