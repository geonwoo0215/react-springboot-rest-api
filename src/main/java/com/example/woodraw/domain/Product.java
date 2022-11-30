package com.example.woodraw.domain;

public class Product {

	public final Long productId;

	public final String productName;

	public final int price;

	public final Size size;

	public Product(Long productId, String productName, int price,  Size size) {
		priceValidation(price);
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.size = size;
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

	public int getPrice() {
		return price;
	}

	public Size getSize() {
		return size;
	}
}
