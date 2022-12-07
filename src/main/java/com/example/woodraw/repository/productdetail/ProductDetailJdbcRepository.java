package com.example.woodraw.repository.productdetail;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.woodraw.domain.product.ProductDetail;
import com.example.woodraw.domain.product.Size;

@Repository
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

	public Map<String, Object> toParamMap(Long productId, Size size) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productId", productId);
		paramMap.put("size", size.getLength());
		return paramMap;
	}

	public RowMapper<ProductDetail> productRowMapper = (result, i) -> {
		var detailId = result.getLong("detail_id");
		var productId = result.getLong("product_id");
		var size = Size.getSizeByLength(result.getString("size"));
		var quantity = result.getInt("quantity");
		return new ProductDetail(detailId, productId, size, quantity);
	};

	public RowMapper<Size> sizeRowMapper = (result, i) -> {
		var size = Size.getSizeByLength(result.getString("size"));
		return size;
	};


	public RowMapper<Integer> productQuantityRowMapper = (result, i) -> result.getInt("quantity");

	String insert = "insert into detail(product_id,size,quantity) values(:productId, :size, :quantity)";
	String findById = "select * from detail where detail_id = :detailId";
	String findAll = "select * from detail";
	String updateByObject = "update detail set size = :size, quantity = :quantity where detail_id = :detailId";
	String deleteById = "delete from detail where detail_id = :detailId";
	String deleteAll = "delete from detail";

	@Override
	public Long insert(ProductDetail productDetail) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource(toParamMap(productDetail));
		jdbcTemplate.update(insert, sqlParameterSource,keyHolder);
		return Objects.requireNonNull(keyHolder.getKey()).longValue();
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

	@Override
	public Integer findQuantityProductId(Long productId, Size size) {
		return jdbcTemplate.queryForObject(
			"SELECT quantity from detail where detail.product_id = :productId AND detail.size = :size",
			toParamMap(productId,size), productQuantityRowMapper);
	}

	@Override
	public List<Size> possibleSize(Long productId) {
		return jdbcTemplate.query("SELECT size from detail where detail.product_id = :productId",
			Collections.singletonMap("productId", productId), sizeRowMapper);
	}
}
