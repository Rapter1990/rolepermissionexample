package com.security.rolepermissionexample.product.service;

import com.security.rolepermissionexample.product.model.Product;
import com.security.rolepermissionexample.product.model.dto.request.ProductCreateRequest;

/**
 * Service interface named {@link ProductCreateService} for creating products.
 */
public interface ProductCreateService {

    /**
     * Creates a new product based on the provided product creation request.
     *
     * @param productCreateRequest The request containing data to create the product.
     * @return The created Product object.
     */
    Product createProduct(final ProductCreateRequest productCreateRequest);

}
