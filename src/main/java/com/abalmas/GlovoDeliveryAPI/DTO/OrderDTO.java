package com.abalmas.GlovoDeliveryAPI.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    @NotEmpty(message = "Customer name cannot be empty")
    private String customerName;

    @NotNull(message = "Total price cannot be null")
    @Positive(message = "Total price must be positive")
    private int totalPrice;

    @NotNull(message = "Addition date cannot be null")
    @Past
    private LocalDate additionDate;

    private List<ProductDTO> products;

}
