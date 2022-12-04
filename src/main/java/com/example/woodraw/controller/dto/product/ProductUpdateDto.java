package com.example.woodraw.controller.dto.product;

import com.example.woodraw.domain.product.Product;

public class ProductUpdateDto {

	private Long productId;

	private String productName;

	private Integer price;

	public ProductUpdateDto(Long productId, String productName, Integer price) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
	}

	public Product toProduct() {
		return new Product(this.productId, this.productName, this.price);
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
