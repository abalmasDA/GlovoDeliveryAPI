package com.abalmas.glovodeliveryapi.mapper;

/**
 * The interface Entity mapper.
 *
 * @param <D> the type parameter
 * @param <E> the type parameter
 */
public interface EntityMapper<D, E> {

  E toEntity(D dto);

  D toDto(E entity);


}
