package com.security.rolepermissionexample.product.model.mapper;

import com.security.rolepermissionexample.common.model.mapper.BaseMapper;
import com.security.rolepermissionexample.product.model.Product;
import com.security.rolepermissionexample.product.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface named {@link ProductToProductEntityMapper} for converting {@link Product} to {@link ProductEntity}.
 */
@Mapper
public interface ProductToProductEntityMapper extends BaseMapper<Product, ProductEntity> {

    /**
     * Maps Product to ProductEntity.
     *
     * @param source The Product object to map.
     * @return ProductEntity object containing mapped data.
     */
    @Override
    ProductEntity map(Product source);

    /**
     * Initializes and returns an instance of ProductToProductEntityMapper.
     *
     * @return Initialized ProductToProductEntityMapper instance.
     */
    static ProductToProductEntityMapper initialize() {
        return Mappers.getMapper(ProductToProductEntityMapper.class);
    }

}
