package com.abalmas.GlovoDeliveryAPI.Converter;

import com.abalmas.GlovoDeliveryAPI.DTO.OrderDTO;
import com.abalmas.GlovoDeliveryAPI.DTO.ProductDTO;
import com.abalmas.GlovoDeliveryAPI.Entity.OrderEntity;
import com.abalmas.GlovoDeliveryAPI.Entity.ProductEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProductConverter {
    private final ModelMapper modelMapper;

    public ProductDTO toProductDTO(ProductEntity productEntity) {
        return modelMapper.map(productEntity, ProductDTO.class);
    }

    public ProductEntity toProductEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, ProductEntity.class);
    }

}
