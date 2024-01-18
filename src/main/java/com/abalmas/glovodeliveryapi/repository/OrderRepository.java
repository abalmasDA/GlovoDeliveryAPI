package com.abalmas.glovodeliveryapi.repository;

import com.abalmas.glovodeliveryapi.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Order repository.
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

}



