package com.example.woodraw.repository.productdetail;

import java.util.List;
import java.util.Optional;

import com.example.woodraw.domain.product.ProductDetail;
import com.example.woodraw.domain.product.Size;

public interface ProductDetailRepository {

	Long insert(ProductDetail productDetail);

	Optional<ProductDetail> findById(Long id);

	List<ProductDetail> findAll();

	void updateByObject(ProductDetail productDetail);

	void deleteById(Long id);

	void deleteAll();

	Integer findQuantityProductId(Long productId, Size size);

	List<Size> possibleSize(Long productId);
}
