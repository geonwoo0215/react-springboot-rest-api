package com.example.woodraw.domain.product;

public class ProductDetail {

	private final Long detailId;

	private final Product product;

	private final Size size;

	private final Integer quantity;

	public ProductDetail(Long detailId, Product product, Size size, Integer quantity) {
		this.detailId = detailId;
		this.product = product;
		this.size = size;
		this.quantity = quantity;
	}

	public Long getDetailId() {
		return detailId;
	}

	public Product getProduct() {
		return product;
	}

	public Size getSize() {
		return size;
	}

	public Integer getQuantity() {
		return quantity;
	}
}
