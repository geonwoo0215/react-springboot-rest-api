package com.example.woodraw.service.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.woodraw.domain.product.Product;
import com.example.woodraw.repository.product.ProductRepository;

@Service
public class productService {

	public productService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	private final ProductRepository productRepository;

	public void insert(Product product) {
		productRepository.insert(product);
	}

	public Product findById(Long productId) {
		return productRepository.findById(productId).orElseThrow(IllegalArgumentException::new);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public void updateByObject(Product product) {
		productRepository.updateByObject(product);
	}

	public void deleteById(Long productId) {
		productRepository.deleteById(productId);
	}

	public void deleteAll() {
		productRepository.deleteAll();
	}

}
