package com.accesodatos.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    @NotBlank(message = "La descripción es obligatoria")
    private String description;
    @NotNull(message = "El precio es obligatorio")
    @Min(0)
    private Double price;
    @NotNull(message = "El stock es obligatorio")
    @Min(0)
    private Integer stock;
}