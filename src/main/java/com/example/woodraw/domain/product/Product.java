package com.example.woodraw.domain.product;

import java.util.Objects;

import com.example.woodraw.controller.dto.product.ProductResponseDto;

public class Product {

	private final Long productId;

	private final String productName;

	private final Integer price;


	public Product(Long productId, String productName, int price) {
		priceValidation(price);
		this.productId = productId;
		this.productName = productName;
		this.price = price;
	}

	public void priceValidation(int price) {
		if (price < 0) {
			throw new IllegalArgumentException();
		}
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

	public ProductResponseDto toProductResponseDto() {
		return new ProductResponseDto(this.productId, this.productName, this.price);
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Product product = (Product)o;
		return Objects.equals(getProductId(), product.getProductId()) && Objects.equals(
			getProductName(), product.getProductName()) && Objects.equals(getPrice(), product.getPrice());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getProductId(), getProductName(), getPrice());
	}
}
