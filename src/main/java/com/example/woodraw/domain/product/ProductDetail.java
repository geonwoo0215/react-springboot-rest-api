package com.example.woodraw.domain.product;

public class ProductDetail {

	private final Long detailId;

	private final Long productId;

	private final Size size;

	private final Integer quantity;

	public ProductDetail(Long detailId, Long productId, Size size, Integer quantity) {
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
