package com.example.woodraw.controller.dto.productDetail;

import com.example.woodraw.domain.product.ProductDetail;
import com.example.woodraw.domain.product.Size;

public class ProductDetailRequestDto {

	private Long productId;

	private Size size;

	private Integer quantity;

	public ProductDetailRequestDto(Long productId, Size size, Integer quantity) {
		this.productId = productId;
		this.size = size;
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public Size getSize() {
		return size;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public ProductDetail toProduct() {
		return new ProductDetail(null, this.productId, size, quantity);
	}
}
