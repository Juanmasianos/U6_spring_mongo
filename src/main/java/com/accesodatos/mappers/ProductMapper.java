package com.accesodatos.mappers;

import org.springframework.stereotype.Component;

import com.accesodatos.dto.product.ProductResponseDto;
import com.accesodatos.entity.Product;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProductMapper {
	
	public ProductResponseDto toResponseDto(Product product) {
		
		ProductResponseDto dto = new ProductResponseDto();
		
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setDescription(product.getDescription());
		dto.setPrice(product.getPrice());
		dto.setStock(product.getStock());

		return dto;
	}
	
	public ProductResponseDto toShortResponseDto(Product product) {
		
		ProductResponseDto dto = new ProductResponseDto();
		
		dto.setName(product.getName());
		dto.setDescription(product.getDescription());
		dto.setPrice(product.getPrice());
		dto.setStock(product.getStock());

		return dto;
	}
}
