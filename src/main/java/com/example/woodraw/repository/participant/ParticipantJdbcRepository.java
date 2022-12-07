package com.example.woodraw.repository.participant;

import com.example.woodraw.domain.product.Size;
import com.example.woodraw.domain.participant.Participant;

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

	public RowMapper<Participant> participantRowMapper = (result, i) -> {
		var memberId = result.getLong("member_id");
		var formId = result.getLong("form_id");
		return new Participant(memberId, formId);
	};

	public Map<String, Object> toParamMap(Long eventId, String size, Integer quantity) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("eventId", eventId);
		paramMap.put("size", size);
		paramMap.put("quantity", quantity);
		return paramMap;
	}

	public Map<String, Object> toParamMap(Long eventId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("eventId", eventId);
		return paramMap;
	}

	public Map<String, Object> toParamMap(Long eventId,Long memberId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("memberId", memberId);
		paramMap.put("eventId", eventId);
		return paramMap;
	}

	public Map<String, Object> toParamMapByForm(Long formId,Long memberId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("memberId", memberId);
		paramMap.put("formId", formId);
		return paramMap;
	}

	public RowMapper<Long> memberIdRowMapper = (result, i) -> result.getLong("member_id");

	public RowMapper<Long> formIdRowMapper = (result, i) -> result.getLong("form_id");


	public Map<String, Object> toParamMap(Participant participant) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("formId", participant.getFormId());
		paramMap.put("memberId", participant.getMemberId());
		return paramMap;
	}




	@Override
	public List<Participant> allocate() {
		return jdbcTemplate.query(
			"SELECT member.member_id,form.form_id from member inner join form on member.email = form.email",
			participantRowMapper);
	}

	@Override
	public List<Long> findWinner(Long eventId) {
		return jdbcTemplate.query(
			"SELECT participant.member_id from participant "
				+ "inner join form on participant.form_id = form.form_id "
				+ "where participant.win = true AND form.event_id = :eventId",
			toParamMap(eventId), memberIdRowMapper);
	}

	@Override
	public void insert(Participant participant) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource(toParamMap(participant));
		jdbcTemplate.update(
			"INSERT INTO participant(member_id,form_id) Values(:memberId, :formId)",
			sqlParameterSource, keyHolder);
	}

	@Override
	public List<Long> pickWinner(Long eventId, Size size, Integer quantity) {
		return jdbcTemplate.query(
			"SELECT participant.member_id from participant  "
				+ "inner join form on participant.form_id = form.form_id "
				+ "where form.event_id = :eventId AND form.size = :size "
				+ "ORDER BY RAND() LIMIT :quantity"
			, toParamMap(eventId, size.getLength(), quantity), memberIdRowMapper);
	}


	@Override
	public void updateWinner(Long formId, Long memberId) {
		jdbcTemplate.update("update participant set win = true where participant.member_id = :memberId AND participant.form_id = :formId",
			toParamMapByForm(formId,memberId));

	}

	@Override
	public Long findFormIdByMemberId(Long eventId, Long memberId) {
		return jdbcTemplate.queryForObject("SELECT participant.form_id from participant "
				+ "inner join form on participant.form_id = form.form_id "
				+ "where  form.event_id = :eventId AND participant.member_id = :memberId",
			toParamMap(eventId, memberId), formIdRowMapper);
	}

}
