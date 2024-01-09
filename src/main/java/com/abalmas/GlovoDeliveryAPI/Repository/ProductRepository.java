package com.abalmas.GlovoDeliveryAPI.Repository;

import com.abalmas.GlovoDeliveryAPI.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {


}
