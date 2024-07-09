package com.security.rolepermissionexample.product.model.mapper;

import com.security.rolepermissionexample.common.model.mapper.BaseMapper;
import com.security.rolepermissionexample.product.model.dto.request.ProductUpdateRequest;
import com.security.rolepermissionexample.product.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface named {@link ProductUpdateRequestToProductEntityMapper} for updating {@link ProductEntity} using {@link ProductUpdateRequest}.
 */
@Mapper
public interface ProductUpdateRequestToProductEntityMapper extends BaseMapper<ProductUpdateRequest, ProductEntity> {

    /**
     * Maps fields from ProductUpdateRequest to update ProductEntity.
     *
     * @param productEntityToBeUpdate The ProductEntity object to be updated.
     * @param productUpdateRequest    The ProductUpdateRequest object containing updated fields.
     */
    @Named("mapForUpdating")
    default void mapForUpdating(ProductEntity productEntityToBeUpdate, ProductUpdateRequest productUpdateRequest) {
        productEntityToBeUpdate.setName(productUpdateRequest.getName());
        productEntityToBeUpdate.setAmount(productUpdateRequest.getAmount());
        productEntityToBeUpdate.setUnitPrice(productUpdateRequest.getUnitPrice());
    }

    /**
     * Initializes and returns an instance of ProductUpdateRequestToProductEntityMapper.
     *
     * @return Initialized ProductUpdateRequestToProductEntityMapper instance.
     */
    static ProductUpdateRequestToProductEntityMapper initialize() {
        return Mappers.getMapper(ProductUpdateRequestToProductEntityMapper.class);
    }

}
