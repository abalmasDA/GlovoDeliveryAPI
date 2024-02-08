package com.abalmas.glovodeliveryapi.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Order dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

  @NotEmpty(message = "Customer name cannot be empty")
  private String customerName;

  @NotNull(message = "Total price cannot be null")
  @Positive(message = "Total price must be positive")
  private int totalPrice;

  @NotNull(message = "Addition date cannot be null")
  @Past
  private LocalDate additionDate;

  private List<ProductDto> products;

}
