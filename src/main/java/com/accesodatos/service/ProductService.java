package com.accesodatos.service;

import java.util.List;

import com.accesodatos.dto.product.ProductRequestDto;
import com.accesodatos.dto.product.ProductResponseDto;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto request);
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto getProductById(String id);
    ProductResponseDto updateProduct(String id, ProductRequestDto request);
    void deleteProduct(String id);
}