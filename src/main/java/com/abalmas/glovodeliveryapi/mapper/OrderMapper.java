package com.abalmas.glovodeliveryapi.mapper;

import com.abalmas.glovodeliveryapi.dto.OrderDto;
import com.abalmas.glovodeliveryapi.entity.OrderEntity;
import org.mapstruct.Mapper;

/**
 * The interface Order mapper.
 */
@Mapper(componentModel = "spring")
public interface OrderMapper extends EntityMapper<OrderDto, OrderEntity> {


}
