package com.security.rolepermissionexample.product.model.mapper;

import com.security.rolepermissionexample.common.model.mapper.BaseMapper;
import com.security.rolepermissionexample.product.model.dto.request.ProductCreateRequest;
import com.security.rolepermissionexample.product.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductCreateRequestToProductEntityMapper extends BaseMapper<ProductCreateRequest, ProductEntity> {


    @Named("mapForSaving")
    default ProductEntity mapForSaving(ProductCreateRequest productCreateRequest) {
        return ProductEntity.builder()
                .amount(productCreateRequest.getAmount())
                .name(productCreateRequest.getName())
                .unitPrice(productCreateRequest.getUnitPrice())
                .build();
    }

    static ProductCreateRequestToProductEntityMapper initialize() {
        return Mappers.getMapper(ProductCreateRequestToProductEntityMapper.class);
    }

}
