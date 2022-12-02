package com.example.woodraw.repository.event;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
		paramMap.put("deadLine", event.getDeadLine());
		return paramMap;
	}

	public RowMapper<Event> memberRowMapper = (result, i) -> {
		var eventId = result.getLong("event_id");
		var productId = result.getLong("product_id");
		var deadLine = result.getTimestamp("deadline").toLocalDateTime();
		return new Event(eventId, productId, deadLine);
	};

	String insert = "insert into event(event_id,product_id,deadline) values(:eventId, :productId, :deadLine)";
	String findById = "select * from event where event_id = :eventId";
	String findAll = "select * from event";
	String updateByObject = "update event set deadline = :deadLine where event_id = :eventId";
	String deleteById = "delete from event where event_id = :eventId";
	String deleteAll = "delete from event";

	@Override
	public void insert(Event event) {
		Map<String, Object> paramMap = toParamMap(event);
		jdbcTemplate.update(insert, paramMap);
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
	public void updateByObject(Event event) {
		try {
			jdbcTemplate.update(updateByObject, toParamMap(event));

		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteById(Long eventId) {
		jdbcTemplate.update(deleteById, Collections.singletonMap("eventId", eventId));
	}

	@Override
	public void deleteAll() {
		jdbcTemplate.update(deleteAll, Collections.emptyMap());
	}
}
