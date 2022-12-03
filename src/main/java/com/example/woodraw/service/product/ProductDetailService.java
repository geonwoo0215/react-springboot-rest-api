package com.example.woodraw.service.product;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.woodraw.controller.dto.product.ProductResponseDto;
import com.example.woodraw.controller.dto.productDetail.ProductDetailRequestDto;
import com.example.woodraw.controller.dto.productDetail.ProductDetailResponseDto;
import com.example.woodraw.domain.product.ProductDetail;
import com.example.woodraw.repository.productdetail.ProductDetailRepository;

@Service
public class ProductDetailService {

	private final ProductDetailRepository productDetailRepository;

	public ProductDetailService(
		ProductDetailRepository productDetailRepository) {
		this.productDetailRepository = productDetailRepository;
	}

	public void insert(ProductDetailRequestDto productDetailRequestDto) {
		productDetailRepository.insert(productDetailRequestDto.toProduct());
	}

	public ProductDetailResponseDto findById(Long productDetailId) {
		return productDetailRepository.findById(productDetailId).orElseThrow(IllegalArgumentException::new).toProductDetailResponseDto();
	}

	public List<ProductDetailResponseDto> findAll() {
		return productDetailRepository.findAll().stream().map(ProductDetail::toProductDetailResponseDto).collect(
			Collectors.toList());
	}

	public void updateByObject(ProductDetailRequestDto productDetailRequestDto) {
		productDetailRepository.updateByObject(productDetailRequestDto.toProduct());
	}

	public void deleteById(Long productDetailId) {
		productDetailRepository.deleteById(productDetailId);
	}

	public void deleteAll() {
		productDetailRepository.deleteAll();
	}
}
