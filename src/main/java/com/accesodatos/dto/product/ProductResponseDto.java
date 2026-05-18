package com.accesodatos.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

        private String name;
        private String description;
        private Double price;
        private Integer stock;
}
