package com.example.woodraw.domain.product;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Product product = (Product)o;
		return getPrice() == product.getPrice() && Objects.equals(getProductId(), product.getProductId())
			&& Objects.equals(getProductName(), product.getProductName()) && getSize() == product.getSize();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getProductId(), getProductName(), getPrice(), getSize());
	}
}
