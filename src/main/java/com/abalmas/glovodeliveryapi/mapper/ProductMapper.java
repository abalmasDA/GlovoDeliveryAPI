package com.abalmas.glovodeliveryapi.mapper;

import com.abalmas.glovodeliveryapi.dto.ProductDto;
import com.abalmas.glovodeliveryapi.entity.ProductEntity;
import org.mapstruct.Mapper;

/**
 * The interface Product mapper.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDto, ProductEntity> {


}
