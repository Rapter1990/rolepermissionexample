package com.security.rolepermissionexample.product.model.mapper;

import com.security.rolepermissionexample.product.model.Product;
import com.security.rolepermissionexample.product.model.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ListProductEntityToListProductMapper {

    ProductEntityToProductMapper productEntityToProductMapper = Mappers.getMapper(ProductEntityToProductMapper.class);

    default List<Product> toProductList(List<ProductEntity> productEntities) {

        if (productEntities == null) {
            return null;
        }

        return productEntities.stream()
                .map(productEntityToProductMapper::map)
                .collect(Collectors.toList());

    }


    static ListProductEntityToListProductMapper initialize() {
        return Mappers.getMapper(ListProductEntityToListProductMapper.class);
    }

}
