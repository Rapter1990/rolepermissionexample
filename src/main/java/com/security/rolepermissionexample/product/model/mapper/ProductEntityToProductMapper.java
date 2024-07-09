package com.security.rolepermissionexample.product.model.mapper;

import com.security.rolepermissionexample.common.model.mapper.BaseMapper;
import com.security.rolepermissionexample.product.model.Product;
import com.security.rolepermissionexample.product.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface named {@link ProductEntityToProductMapper} for converting {@link ProductEntity} to {@link Product}.
 */
@Mapper
public interface ProductEntityToProductMapper extends BaseMapper<ProductEntity, Product> {

    /**
     * Maps ProductEntity to Product.
     *
     * @param source The ProductEntity object to map.
     * @return Product object containing mapped data.
     */
    @Override
    Product map(ProductEntity source);

    /**
     * Initializes and returns an instance of ProductEntityToProductMapper.
     *
     * @return Initialized ProductEntityToProductMapper instance.
     */
    static ProductEntityToProductMapper initialize() {
        return Mappers.getMapper(ProductEntityToProductMapper.class);
    }

}
