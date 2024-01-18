package com.abalmas.glovodeliveryapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Product entity.
 */
@Data
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "price", nullable = false)
  private int price;

  @ManyToOne
  @JoinColumn(name = "id_order", referencedColumnName = "id", nullable = false)
  private OrderEntity order;

}
