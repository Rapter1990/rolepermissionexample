package com.security.rolepermissionexample.product.service;

import com.security.rolepermissionexample.product.model.Product;
import com.security.rolepermissionexample.product.model.dto.request.ProductUpdateRequest;

public interface ProductUpdateService {

    Product updateProductById(final String productId, final ProductUpdateRequest productUpdateRequest);

}
