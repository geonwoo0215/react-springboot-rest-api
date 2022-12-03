package com.example.woodraw.repository.form;

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

import com.example.woodraw.domain.form.Form;
import com.example.woodraw.domain.product.Size;

@Repository
public class FormJdbcRepository implements FormRepository {
	private final NamedParameterJdbcTemplate jdbcTemplate;

	public FormJdbcRepository(
		NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Map<String, Object> toParamMap(Form form) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("formId", form.getFormId());
		paramMap.put("memberId", form.getMemberId());
		paramMap.put("eventId", form.getEventId());
		paramMap.put("submission", form.getSubmission());
		paramMap.put("size", form.getSize().getLength());
		return paramMap;
	}

	public RowMapper<Form> memberRowMapper = (result, i) -> {
		var formId = result.getLong("form_id");
		var memberId = result.getLong("member_id");
		var eventId = result.getLong("event_id");
		var submission = result.getTimestamp("submission").toLocalDateTime();
		var size = Size.getSizeByLength(result.getString("size"));
		return new Form(formId, memberId, eventId, submission, size);
	};

	String insert = "insert into form(member_id,event_id,submission,size) values(:memberId, :eventId, :submission, :size)";
	String findById = "select * from form where form_id = :formId";
	String findAll = "select * from form";
	String updateByObject = "update form set size = :size where form_id = :formId";
	String deleteById = "delete from form where form_id = :formId";
	String deleteAll = "delete from form";

	@Override
	public Long insert(Form form) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource(toParamMap(form));
		jdbcTemplate.update(insert, sqlParameterSource,keyHolder);
		return Objects.requireNonNull(keyHolder.getKey()).longValue();
	}

	@Override
	public Optional<Form> findById(Long formId) {
		try {
			return Optional.ofNullable(
				jdbcTemplate.queryForObject(findById, Collections.singletonMap("formId", formId),
					memberRowMapper));
		} catch (DataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public List<Form> findAll() {
		return jdbcTemplate.query(findAll, memberRowMapper);
	}

	@Override
	public void updateByObject(Form form) {
		try {
			jdbcTemplate.update(updateByObject, toParamMap(form));

		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteById(Long formId) {
		jdbcTemplate.update(deleteById, Collections.singletonMap("formId", formId));
	}

	@Override
	public void deleteAll() {
		jdbcTemplate.update(deleteAll, Collections.emptyMap());
	}
}
