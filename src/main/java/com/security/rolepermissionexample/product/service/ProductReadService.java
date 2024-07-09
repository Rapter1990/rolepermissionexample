package com.security.rolepermissionexample.product.service;

import com.security.rolepermissionexample.common.model.CustomPage;
import com.security.rolepermissionexample.product.model.Product;
import com.security.rolepermissionexample.product.model.dto.request.ProductPagingRequest;

/**
 * Service interface named {@link ProductReadService} for reading products.
 */
public interface ProductReadService {

    /**
     * Retrieves a product by its unique ID.
     *
     * @param productId The ID of the product to retrieve.
     * @return The Product object corresponding to the given ID.
     */
    Product getProductById(final String productId);

    /**
     * Retrieves a page of products based on the paging request criteria.
     *
     * @param productPagingRequest The paging request criteria.
     * @return A CustomPage containing the list of products that match the paging criteria.
     */
    CustomPage<Product> getProducts(final ProductPagingRequest productPagingRequest);

}
