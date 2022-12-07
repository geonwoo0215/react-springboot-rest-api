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
		paramMap.put("email", form.getEmail());
		paramMap.put("eventId", form.getEventId());
		paramMap.put("size", form.getSize().getLength());
		return paramMap;
	}

	public RowMapper<Form> memberRowMapper = (result, i) -> {
		var formId = result.getLong("form_id");
		var email = result.getString("email");
		var eventId = result.getLong("event_id");
		var size = Size.getSizeByLength(result.getString("size"));
		return new Form(formId, eventId, size, email);
	};

	public RowMapper<Size> sizeRowMapper = (result, i) -> {
		return Size.getSizeByLength(result.getString("size"));
	};

	String insert = "insert into form(event_id,size,email) values( :eventId, :size,:email)";
	String findById = "select * from form where form_id = :formId";
	String findAll = "select * from form";
	String updateByObject = "update form set size = :size where form_id = :formId";
	String deleteById = "delete from form where form_id = :formId";
	String deleteAll = "delete from form";

	@Override
	public Long insert(Form form) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource(toParamMap(form));
		jdbcTemplate.update(insert, sqlParameterSource, keyHolder);
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

	public Size findSizeById(Long formId) {
		return jdbcTemplate.queryForObject("SELECT form.size from form where form.form_id = :formId",
			Collections.singletonMap("formId", formId), sizeRowMapper);
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
