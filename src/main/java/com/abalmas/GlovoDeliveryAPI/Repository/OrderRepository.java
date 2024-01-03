package com.abalmas.GlovoDeliveryAPI.Repository;

import com.abalmas.GlovoDeliveryAPI.Entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

}



