package com.security.rolepermissionexample.product.controller;

import com.security.rolepermissionexample.common.model.CustomPage;
import com.security.rolepermissionexample.common.model.dto.response.CustomPagingResponse;
import com.security.rolepermissionexample.common.model.dto.response.CustomResponse;
import com.security.rolepermissionexample.product.model.Product;
import com.security.rolepermissionexample.product.model.dto.request.ProductCreateRequest;
import com.security.rolepermissionexample.product.model.dto.request.ProductPagingRequest;
import com.security.rolepermissionexample.product.model.dto.request.ProductUpdateRequest;
import com.security.rolepermissionexample.product.model.dto.response.ProductResponse;
import com.security.rolepermissionexample.product.model.mapper.CustomPageToCustomPagingResponseMapper;
import com.security.rolepermissionexample.product.model.mapper.ProductToProductResponseMapper;
import com.security.rolepermissionexample.product.service.ProductCreateService;
import com.security.rolepermissionexample.product.service.ProductDeleteService;
import com.security.rolepermissionexample.product.service.ProductReadService;
import com.security.rolepermissionexample.product.service.ProductUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductCreateService productCreateService;
    private final ProductReadService productReadService;
    private final ProductUpdateService productUpdateService;
    private final ProductDeleteService productDeleteService;

    private final ProductToProductResponseMapper productToProductResponseMapper = ProductToProductResponseMapper.initialize();

    private final CustomPageToCustomPagingResponseMapper customPageToCustomPagingResponseMapper =
            CustomPageToCustomPagingResponseMapper.initialize();

    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin:create')")
    public CustomResponse<String> createProduct(@RequestBody @Valid final ProductCreateRequest productCreateRequest) {

        final Product createdProduct = productCreateService
                .createProduct(productCreateRequest);

        return CustomResponse.successOf(createdProduct.getId());
    }

    @GetMapping("/{productId}")
    @PreAuthorize("hasAnyAuthority('admin:get', 'user:get')")
    public CustomResponse<ProductResponse> getProductById(@PathVariable @UUID final String productId) {

        final Product product = productReadService.getProductById(productId);

        final ProductResponse productResponse = productToProductResponseMapper.map(product);

        return CustomResponse.successOf(productResponse);

    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:get', 'user:get')")
    public CustomResponse<CustomPagingResponse<ProductResponse>> getProducts(
            @RequestBody @Valid final ProductPagingRequest productPagingRequest) {

        final CustomPage<Product> productPage = productReadService.getProducts(productPagingRequest);

        final CustomPagingResponse<ProductResponse> productPagingResponse =
                customPageToCustomPagingResponseMapper.toPagingResponse(productPage);

        return CustomResponse.successOf(productPagingResponse);

    }

    @PutMapping("/{productId}")
    @PreAuthorize("hasAnyAuthority('admin:update')")
    public CustomResponse<ProductResponse> updatedProductById(
            @RequestBody @Valid final ProductUpdateRequest productUpdateRequest,
            @PathVariable @UUID final String productId) {

        final Product updatedProduct = productUpdateService.updateProductById(productId, productUpdateRequest);

        final ProductResponse productResponse = productToProductResponseMapper.map(updatedProduct);

        return CustomResponse.successOf(productResponse);
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasAnyAuthority('admin:delete')")
    public CustomResponse<Void> deleteProductById(@PathVariable @UUID final String productId) {

        productDeleteService.deleteProductById(productId);
        return CustomResponse.SUCCESS;
    }

}
