package com.security.rolepermissionexample.product.model.mapper;

import com.security.rolepermissionexample.common.model.mapper.BaseMapper;
import com.security.rolepermissionexample.product.model.Product;
import com.security.rolepermissionexample.product.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductEntityToProductMapper extends BaseMapper<ProductEntity, Product> {

    @Override
    Product map(ProductEntity source);

    static ProductEntityToProductMapper initialize() {
        return Mappers.getMapper(ProductEntityToProductMapper.class);
    }

}
