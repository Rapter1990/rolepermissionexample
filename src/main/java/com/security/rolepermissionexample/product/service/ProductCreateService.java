package com.security.rolepermissionexample.product.service;

import com.security.rolepermissionexample.product.model.Product;
import com.security.rolepermissionexample.product.model.dto.request.ProductCreateRequest;

public interface ProductCreateService {

    Product createProduct(final ProductCreateRequest productCreateRequest);

}
