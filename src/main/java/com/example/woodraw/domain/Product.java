package com.example.woodraw.domain;

public class Product {

	public final Long id;

	public final int price;

	public final String name;

	public final Size size;

	public Product(Long id, int price, String name, Size size) {
		priceValidation(price);
		this.id = id;
		this.price = price;
		this.name = name;
		this.size = size;
	}

	public void priceValidation(int price) {
		if (price < 0) {
			throw new IllegalArgumentException();
		}
	}

	public Long getId() {
		return id;
	}

	public int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public Size getSize() {
		return size;
	}
}
