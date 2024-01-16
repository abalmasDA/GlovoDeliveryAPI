package com.abalmas.GlovoDeliveryAPI.Mapper;

public interface EntityMapper<D, E> {

    E toEntity(D dto);

    D toDTO(E entity);




}
