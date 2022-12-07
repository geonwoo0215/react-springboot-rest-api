package com.example.woodraw.repository.event;

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

import com.example.woodraw.domain.event.Event;

@Repository
public class EventJdbcRepository implements EventRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public EventJdbcRepository(
		NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Map<String, Object> toParamMap(Event event) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("eventId", event.getEventId());
		paramMap.put("productId", event.getProductId());
		return paramMap;
	}

	public RowMapper<Long> productIdRowMapper = (result, i) -> result.getLong("product_id");

	public RowMapper<Event> memberRowMapper = (result, i) -> {
		var eventId = result.getLong("event_id");
		var productId = result.getLong("product_id");
		return new Event(eventId, productId);
	};

	String insert = "insert into event(event_id,product_id) values(:eventId, :productId)";
	String findById = "select * from event where event_id = :eventId";
	String findAll = "select * from event";
	String deleteById = "delete from event where event_id = :eventId";
	String deleteAll = "delete from event";

	@Override
	public Long insert(Event event) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource(toParamMap(event));
		jdbcTemplate.update(insert, sqlParameterSource,keyHolder);
		return Objects.requireNonNull(keyHolder.getKey()).longValue();
	}

	@Override
	public Optional<Event> findById(Long eventId) {
		try {
			return Optional.ofNullable(
				jdbcTemplate.queryForObject(findById, Collections.singletonMap("eventId", eventId),
					memberRowMapper));
		} catch (DataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public List<Event> findAll() {
		return jdbcTemplate.query(findAll, memberRowMapper);
	}

	@Override
	public void deleteById(Long eventId) {
		jdbcTemplate.update(deleteById, Collections.singletonMap("eventId", eventId));
	}

	@Override
	public void deleteAll() {
		jdbcTemplate.update(deleteAll, Collections.emptyMap());
	}

	@Override
	public Long findProductIdByEventId(Long eventId) {
		return jdbcTemplate.queryForObject(
			"SELECT event.product_id from event where event.event_id = :eventId",
			Collections.singletonMap("eventId", eventId), productIdRowMapper);
	}

}
