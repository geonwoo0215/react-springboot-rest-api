package com.example.woodraw.repository.product;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.woodraw.domain.product.Product;
import com.example.woodraw.domain.product.Size;

@Repository
public class ProductJdbcRepository implements ProductRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public ProductJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Map<String, Object> toParamMap(Product product) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId", product.getProductId());
		paramMap.put("productName", product.getProductName());
		paramMap.put("price", product.getPrice());
		paramMap.put("size", product.getSize().getLength());
		return paramMap;
	}

	public RowMapper<Product> productRowMapper = (result, i) -> {

		var id = result.getLong("product_id");
		var name = result.getString("product_name");
		var price = result.getInt("price");
		var size = Size.getSizeByLength(result.getString("size"));
		return new Product(id, name, price, size);
	};

	String insert = "insert into product(product_id,product_name,price,size) values(:productId, :productName, :price,:size)";
	String findById = "select * from product where product_id = :productId";
	String findAll = "select * from product";
	String updateByObject = "update product set price = :price, product_name = :productName where product_id = :productId";
	String deleteById = "delete from product where product_id = :productId";
	String deleteAll = "delete from product";

	@Override
	public void insert(Product product) {
		Map<String, Object> paramMap = toParamMap(product);
		jdbcTemplate.update(insert, paramMap);
	}

	@Override
	public Optional<Product> findById(Long productId) {
		try {
			return Optional.ofNullable(
				jdbcTemplate.queryForObject(findById, Collections.singletonMap("productId", productId),
					productRowMapper));
		} catch (DataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public List<Product> findAll() {
		return jdbcTemplate.query(findAll, productRowMapper);
	}

	@Override
	public void updateByObject(Product product) {
		try {
			jdbcTemplate.update(updateByObject, toParamMap(product));

		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteById(Long productId) {
		jdbcTemplate.update(deleteById, Collections.singletonMap("productId", productId));
	}

	@Override
	public void deleteAll() {
		jdbcTemplate.update(deleteAll, Collections.emptyMap());
	}

}
