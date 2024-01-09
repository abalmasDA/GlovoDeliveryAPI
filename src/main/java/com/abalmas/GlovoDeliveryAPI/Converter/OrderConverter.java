package com.abalmas.GlovoDeliveryAPI.Converter;

import com.abalmas.GlovoDeliveryAPI.DTO.OrderDTO;
import com.abalmas.GlovoDeliveryAPI.Entity.OrderEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class OrderConverter {

    private final ModelMapper modelMapper;

    public OrderDTO toOrderDTO(OrderEntity orderEntity) {
        return modelMapper.map(orderEntity, OrderDTO.class);
    }

    public OrderEntity toOrderEntity(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, OrderEntity.class);
    }
}