package com.example.woodraw.repository.winner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.example.woodraw.domain.member.Member;
import com.example.woodraw.domain.product.Size;
import com.example.woodraw.domain.winner.Winner;

public class WinnerJdbcRepository implements WinnerRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public WinnerJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public RowMapper<Winner> winnerRowMapper = (result, i) -> {
		var memberId = result.getLong("member_id");
		var formId = result.getLong("form_id");
		return new Winner(memberId, formId);
	};

	public Map<String, Object> toParamMap(Winner winner) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("formId", winner.getFormId());
		paramMap.put("memberId", winner.getMemberId());
		return paramMap;
	}

	@Override
	public List<Winner> allocate() {
		return jdbcTemplate.query(
			"SELECT member.member_id,form.form_id from member inner join form on member.email = form.email",
			winnerRowMapper);
	}

	@Override
	public void insert(Winner winner) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource(toParamMap(winner));
		jdbcTemplate.update(
			"INSERT INTO winner(member_id,form_id) Values(:memberId, :formId)",
			sqlParameterSource,keyHolder);
	}

	@Override
	public List<Member> pick(Long eventId, Size size) {
		jdbcTemplate.query(
			"SELECT member.member_name from winner.event_id = :eventId"
		)
	}
}
