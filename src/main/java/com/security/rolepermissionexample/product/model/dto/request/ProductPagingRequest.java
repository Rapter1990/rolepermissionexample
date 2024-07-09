package com.security.rolepermissionexample.product.model.dto.request;

import com.security.rolepermissionexample.common.model.dto.request.CustomPagingRequest;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Represents a paging request object for retrieving products as {@link ProductPagingRequest}.
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ProductPagingRequest extends CustomPagingRequest {


}
