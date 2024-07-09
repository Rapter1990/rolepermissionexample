package com.security.rolepermissionexample.product.model.mapper;

import com.security.rolepermissionexample.common.model.mapper.BaseMapper;
import com.security.rolepermissionexample.product.model.Product;
import com.security.rolepermissionexample.product.model.dto.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface named {@link ProductToProductResponseMapper} for converting {@link Product} to {@link ProductResponse}.
 */
@Mapper
public interface ProductToProductResponseMapper extends BaseMapper<Product, ProductResponse> {

    /**
     * Maps Product to ProductResponse.
     *
     * @param source The Product object to map.
     * @return ProductResponse object containing mapped data.
     */
    @Override
    ProductResponse map(Product source);

    /**
     * Initializes and returns an instance of ProductToProductResponseMapper.
     *
     * @return Initialized ProductToProductResponseMapper instance.
     */
    static ProductToProductResponseMapper initialize() {
        return Mappers.getMapper(ProductToProductResponseMapper.class);
    }

}
