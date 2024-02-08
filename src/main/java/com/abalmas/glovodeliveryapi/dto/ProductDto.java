package com.abalmas.glovodeliveryapi.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Product dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

  @NotEmpty(message = "Title cannot be empty")
  private String title;

  @NotNull(message = "Price cannot be null")
  @Positive(message = "Price must be positive")
  private int price;

}
