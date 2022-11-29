package com.example.woodraw.domain.clothing;

public abstract class Clothing {

	public final Long id;

	public final int price;

	public final String name;

	public Clothing(Long id, int price, String name) {
		priceValidation(price);
		this.id = id;
		this.price = price;
		this.name = name;
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
}
