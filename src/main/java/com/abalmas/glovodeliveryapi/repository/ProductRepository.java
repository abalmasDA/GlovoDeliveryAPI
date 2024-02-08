package com.abalmas.glovodeliveryapi.repository;

import com.abalmas.glovodeliveryapi.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Product repository.
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {


}
