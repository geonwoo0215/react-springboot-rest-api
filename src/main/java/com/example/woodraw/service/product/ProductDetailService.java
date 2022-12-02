package com.example.woodraw.service.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.woodraw.domain.product.ProductDetail;
import com.example.woodraw.repository.productdetail.ProductDetailRepository;

@Service
public class ProductDetailService {

	private final ProductDetailRepository productDetailRepository;

	public ProductDetailService(
		ProductDetailRepository productDetailRepository) {
		this.productDetailRepository = productDetailRepository;
	}

	public void insert(ProductDetail productDetailId) {
		productDetailRepository.insert(productDetailId);
	}

	public ProductDetail findById(Long productDetailId) {
		return productDetailRepository.findById(productDetailId).orElseThrow(IllegalArgumentException::new);
	}

	public List<ProductDetail> findAll() {
		return productDetailRepository.findAll();
	}

	public void updateByObject(ProductDetail productDetailId) {
		productDetailRepository.updateByObject(productDetailId);
	}

	public void deleteById(Long productDetailId) {
		productDetailRepository.deleteById(productDetailId);
	}

	public void deleteAll() {
		productDetailRepository.deleteAll();
	}
}
