package com.abalmas.glovodeliveryapi.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The type Order entity.
 */
@Data
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class OrderEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "customer_name", nullable = false)
  private String customerName;

  @Column(name = "total_price", nullable = false)
  private int totalPrice;

  @Column(name = "addition_date", nullable = false)
  private LocalDate additionDate;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<ProductEntity> products;


}
