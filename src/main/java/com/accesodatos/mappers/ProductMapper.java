package com.accesodatos.mappers;

import org.springframework.stereotype.Component;

import com.accesodatos.dto.product.ProductResponseDto;
import com.accesodatos.entity.Product;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProductMapper {
	
	public ProductResponseDto toResponseDto(Product Product) {
		
		ProductResponseDto dto = new ProductResponseDto();
		
		dto.setName(Product.getName());
		dto.setDescription(Product.getDescription());
		dto.setPrice(Product.getPrice());
		dto.setStock(Product.getStock());

		return dto;
	}
	
	public ProductResponseDto toShortResponseDto(Product Product) {
		
		ProductResponseDto dto = new ProductResponseDto();
		
		dto.setName(Product.getName());
		dto.setDescription(Product.getDescription());
		dto.setPrice(Product.getPrice());
		dto.setStock(Product.getStock());

		return dto;
	}
}
