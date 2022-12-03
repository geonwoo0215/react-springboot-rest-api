package com.example.woodraw.service.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.woodraw.controller.dto.ProductRequestDto;
import com.example.woodraw.controller.dto.ProductResponseDto;
import com.example.woodraw.domain.product.Product;
import com.example.woodraw.repository.product.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public void insert(ProductRequestDto productRequestDto) {
		productRepository.insert(productRequestDto.toProduct());
	}

	public ProductResponseDto findById(Long productId) {
		return productRepository.findById(productId).orElseThrow(IllegalArgumentException::new).toProductResponseDto();
	}

	public List<ProductResponseDto> findAll() {
		return productRepository.findAll().stream().map(Product::toProductResponseDto).collect(Collectors.toList());
	}

	public void updateByObject(ProductRequestDto productRequestDto) {
		productRepository.updateByObject(productRequestDto.toProduct());
	}

	public void deleteById(Long productId) {
		productRepository.deleteById(productId);
	}

	public void deleteAll() {
		productRepository.deleteAll();
	}

}
