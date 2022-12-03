package com.example.woodraw.controller.dto.product;

import com.example.woodraw.domain.product.Product;

public class ProductRequestDto {

	private String productName;

	private Integer price;

	public ProductRequestDto() {
	}

	public ProductRequestDto(String productName, Integer price) {
		this.productName = productName;
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public Integer getPrice() {
		return price;
	}

	public Product toProduct() {
		return new Product(null, this.productName, this.price);
	}
}
