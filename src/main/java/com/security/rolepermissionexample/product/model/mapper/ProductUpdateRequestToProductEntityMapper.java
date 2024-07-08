package com.security.rolepermissionexample.product.model.mapper;

import com.security.rolepermissionexample.common.model.mapper.BaseMapper;
import com.security.rolepermissionexample.product.model.dto.request.ProductUpdateRequest;
import com.security.rolepermissionexample.product.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductUpdateRequestToProductEntityMapper extends BaseMapper<ProductUpdateRequest, ProductEntity> {


    @Named("mapForUpdating")
    default void mapForUpdating(ProductEntity productEntityToBeUpdate, ProductUpdateRequest productUpdateRequest) {
        productEntityToBeUpdate.setName(productUpdateRequest.getName());
        productEntityToBeUpdate.setAmount(productUpdateRequest.getAmount());
        productEntityToBeUpdate.setUnitPrice(productUpdateRequest.getUnitPrice());
    }

    static ProductUpdateRequestToProductEntityMapper initialize() {
        return Mappers.getMapper(ProductUpdateRequestToProductEntityMapper.class);
    }

}
