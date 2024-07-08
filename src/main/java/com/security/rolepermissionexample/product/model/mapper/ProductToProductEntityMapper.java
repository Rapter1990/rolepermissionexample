package com.security.rolepermissionexample.product.model.mapper;

import com.security.rolepermissionexample.common.model.mapper.BaseMapper;
import com.security.rolepermissionexample.product.model.Product;
import com.security.rolepermissionexample.product.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductToProductEntityMapper extends BaseMapper<Product, ProductEntity> {

    @Override
    ProductEntity map(Product source);

    static ProductToProductEntityMapper initialize() {
        return Mappers.getMapper(ProductToProductEntityMapper.class);
    }

}
