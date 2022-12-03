package com.example.woodraw.controller.dto.productDetail;

import com.example.woodraw.domain.product.Size;

public class ProductDetailResponseDto {

	private Long detailId;

	private Long productId;

	private Size size;

	private Integer quantity;

	public ProductDetailResponseDto(Long detailId, Long productId, Size size, Integer quantity) {
		this.detailId = detailId;
		this.productId = productId;
		this.size = size;
		this.quantity = quantity;
	}

	public Long getDetailId() {
		return detailId;
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


}



