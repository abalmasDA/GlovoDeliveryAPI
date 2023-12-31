package com.abalmas.GlovoDeliveryAPI.Entity;

import com.abalmas.GlovoDeliveryAPI.DTO.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "order")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "total_price")
    private long totalPrice;

    @Column(name = "addition_date")
    private LocalDate additionDate;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductEntity> products;


}
