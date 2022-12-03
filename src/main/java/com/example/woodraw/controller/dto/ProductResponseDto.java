package com.example.woodraw.controller.dto;

public class ProductResponseDto {

	private Long productId;

	private String productName;

	private Integer price;

	public ProductResponseDto(Long productId, String productName, Integer price) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
	}

	public Long getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public Integer getPrice() {
		return price;
	}
}
