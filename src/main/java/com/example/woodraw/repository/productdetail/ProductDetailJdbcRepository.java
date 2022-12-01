package com.example.woodraw.repository.productdetail;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.woodraw.domain.product.ProductDetail;
import com.example.woodraw.domain.product.Size;

public class ProductDetailJdbcRepository implements ProductDetailRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public ProductDetailJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Map<String, Object> toParamMap(ProductDetail productDetail) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("detailId", productDetail.getDetailId());
		paramMap.put("productId", productDetail.getProductId());
		paramMap.put("size", productDetail.getSize().getLength());
		paramMap.put("quantity", productDetail.getQuantity());
		return paramMap;
	}

	public RowMapper<ProductDetail> productRowMapper = (result, i) -> {
		var detailId = result.getLong("detail_id");
		var productId = result.getLong("product_id");
		var size = Size.getSizeByLength(result.getString("size"));
		var quantity = result.getInt("quantity");
		return new ProductDetail(detailId, productId, size, quantity);
	};

	String insert = "insert into detail(detail_id,product_id,size,quantity) values(:detailId, :productId, :size, :quantity)";
	String findById = "select * from detail where detail_id = :detailId";
	String findAll = "select * from detail";
	String updateByObject = "update detail set size = :size, quantity = :quantity where detail_id = :detailId";
	String deleteById = "delete from detail where detail_id = :detailId";
	String deleteAll = "delete from detail";

	@Override
	public void insert(ProductDetail productDetail) {
		Map<String, Object> paramMap = toParamMap(productDetail);
		jdbcTemplate.update(insert, paramMap);
	}

	@Override
	public Optional<ProductDetail> findById(Long detailId) {
		try {
			return Optional.ofNullable(
				jdbcTemplate.queryForObject(findById, Collections.singletonMap("detailId", detailId),
					productRowMapper));
		} catch (DataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public List<ProductDetail> findAll() {
		return jdbcTemplate.query(findAll, productRowMapper);
	}

	@Override
	public void updateByObject(ProductDetail productDetail) {
		try {
			jdbcTemplate.update(updateByObject, toParamMap(productDetail));

		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteById(Long detailId) {
		jdbcTemplate.update(deleteById, Collections.singletonMap("detailId", detailId));
	}

	@Override
	public void deleteAll() {
		jdbcTemplate.update(deleteAll, Collections.emptyMap());
	}
}
