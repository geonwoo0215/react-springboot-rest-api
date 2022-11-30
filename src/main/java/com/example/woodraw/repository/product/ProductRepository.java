package com.example.woodraw.repository.product;

import java.util.List;
import java.util.Optional;

import com.example.woodraw.domain.product.Product;

public interface ProductRepository {

	void insert(Product product);

	Optional<Product> findById(Long id);

	List<Product> findAll();

	void updateByObject(Product product);

	void deleteById(Long id);

	void deleteAll();

}
