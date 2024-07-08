package com.security.rolepermissionexample.product.service;

import com.security.rolepermissionexample.common.model.CustomPage;
import com.security.rolepermissionexample.product.model.Product;
import com.security.rolepermissionexample.product.model.dto.request.ProductPagingRequest;

public interface ProductReadService {

    Product getProductById(final String productId);

    CustomPage<Product> getProducts(final ProductPagingRequest productPagingRequest);

}
