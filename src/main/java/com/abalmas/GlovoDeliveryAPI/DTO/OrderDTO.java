package com.abalmas.GlovoDeliveryAPI.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private long id;
    private String customerName;
    private long totalPrice;
    private LocalDate additionDate;
    private List<ProductDTO> products;

}
