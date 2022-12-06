package com.example.woodraw.repository.winner;

import com.example.woodraw.domain.product.Size;
import com.example.woodraw.domain.winner.Participant;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ParticipantJdbcRepository implements ParticipantRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public ParticipantJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public RowMapper<Participant> winnerRowMapper = (result, i) -> {
		var memberId = result.getLong("member_id");
		var formId = result.getLong("form_id");
		return new Participant(memberId, formId);
	};

	public Map<String, Object> toParamMap(Long eventId, Size size, Integer quantity) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("eventId", eventId);
		paramMap.put("size", size);
		paramMap.put("quantity", quantity);
		return paramMap;
	}

	public RowMapper<Long> memberIdRowMapper = (result, i) -> {
		var memberId = result.getLong("member_id");
		return memberId;
	};

	public RowMapper<Long> productIdRowMapper = (result, i) -> {
		var productId = result.getLong("product_id");
		return productId;
	};

	public RowMapper<Integer> productQuantityRowMapper = (result, i) -> {
		var quantity = result.getInt("quantity");
		return quantity;
	};

	public RowMapper<Size> sizeRowMapper = (result, i) -> {
		var size = Size.getSizeByLength(result.getString("size"));
		return size;
	};


	public Map<String, Object> toParamMap(Participant winner) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("formId", winner.getFormId());
		paramMap.put("memberId", winner.getMemberId());
		return paramMap;
	}


	@Override
	public List<Participant> allocate() {
		return jdbcTemplate.query(
			"SELECT member.member_id,form.form_id from member inner join form on member.email = form.email",
			winnerRowMapper);
	}

	@Override
	public void insert(Participant winner) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource(toParamMap(winner));
		jdbcTemplate.update(
			"INSERT INTO winner(member_id,form_id) Values(:memberId, :formId)",
			sqlParameterSource,keyHolder);
	}

	@Override
	public List<Long> pick(Long eventId, Size size) {

		Long productId = jdbcTemplate.queryForObject("SELECT event.product_id form event where event.event_id = :eventId",
				Collections.singletonMap("eventId", eventId), productIdRowMapper);


		Integer quantity = jdbcTemplate.queryForObject("SELECT quantity from productDetail where prdouctDetail.product_id = :productId",
				Collections.singletonMap("productId", productId), productQuantityRowMapper);

		return jdbcTemplate.query(
				"SELECT winner.member_id from winner ORDER BY RAND() LIMIT :quantity inner join form on winner.form_id = form.form_id where winner.event_id = :eventId AND winner.size = :size "
				, toParamMap(eventId, size, quantity), memberIdRowMapper);
	}

	@Override
	public List<Size> possibleSize(Long eventId) {

		Long productId = jdbcTemplate.queryForObject("SELECT event.product_id form event where event.event_id = :eventId",
				Collections.singletonMap("eventId", eventId), productIdRowMapper);

		return jdbcTemplate.query("SELECT size from productDetial where producctDetail.product_id = :productId",
				Collections.singletonMap("productId", productId), sizeRowMapper);
	}




}
