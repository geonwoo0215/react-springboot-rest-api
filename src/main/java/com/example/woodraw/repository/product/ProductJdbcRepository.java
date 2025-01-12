package com.example.woodraw.repository.product;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.woodraw.domain.product.Product;

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
		return paramMap;
	}


	public RowMapper<Product> productRowMapper = (result, i) -> {

		var id = result.getLong("product_id");
		var name = result.getString("product_name");
		var price = result.getInt("price");
		return new Product(id, name, price);
	};



	String insert = "insert into product(product_name,price) values(:productName, :price)";
	String findById = "select * from product where product_id = :productId";
	String findAll = "select * from product";
	String updateByObject = "update product set price = :price, product_name = :productName where product_id = :productId";
	String deleteById = "delete from product where product_id = :productId";
	String deleteAll = "delete from product";

	@Override
	public Long insert(Product product) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource(toParamMap(product));
		jdbcTemplate.update(insert, sqlParameterSource,keyHolder);
		return Objects.requireNonNull(keyHolder.getKey()).longValue();
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
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource(toParamMap(product));
		try {
			jdbcTemplate.update(updateByObject, sqlParameterSource);

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
