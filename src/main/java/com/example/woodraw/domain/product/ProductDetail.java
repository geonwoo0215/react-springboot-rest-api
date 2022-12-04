package com.example.woodraw.domain.product;

import java.util.Objects;

import com.example.woodraw.controller.dto.productDetail.ProductDetailResponseDto;

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

	public ProductDetailResponseDto toProductDetailResponseDto() {
		return new ProductDetailResponseDto(this.detailId, this.productId, this.size, this.quantity);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ProductDetail that = (ProductDetail)o;
		return Objects.equals(getDetailId(), that.getDetailId()) && Objects.equals(getProductId(),
			that.getProductId()) && getSize() == that.getSize() && Objects.equals(getQuantity(),
			that.getQuantity());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getDetailId(), getProductId(), getSize(), getQuantity());
	}
}
