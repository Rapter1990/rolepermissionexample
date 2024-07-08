package com.security.rolepermissionexample.product.model.mapper;

import com.security.rolepermissionexample.common.model.mapper.BaseMapper;
import com.security.rolepermissionexample.product.model.Product;
import com.security.rolepermissionexample.product.model.dto.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductToProductResponseMapper extends BaseMapper<Product, ProductResponse> {

    @Override
    ProductResponse map(Product source);

    static ProductToProductResponseMapper initialize() {
        return Mappers.getMapper(ProductToProductResponseMapper.class);
    }

}
