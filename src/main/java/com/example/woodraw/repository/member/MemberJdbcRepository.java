package com.example.woodraw.repository.member;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.woodraw.domain.member.Member;

@Repository
public class MemberJdbcRepository implements MemberRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public MemberJdbcRepository(
		NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Map<String, Object> toParamMap(Member member) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("memberId", member.getMemberId());
		paramMap.put("memberName", member.getMemberName());
		paramMap.put("email", member.getEmail());
		return paramMap;
	}

	public RowMapper<Member> memberRowMapper = (result, i) -> {
		var id = result.getLong("member_id");
		var name = result.getString("member_name");
		var email = result.getString("email");
		return new Member(id, name, email);
	};

	String insert = "insert into member(member_id,member_name,email) values(:memberId, :memberName, :email)";
	String findById = "select * from member where member_id = :memberId";
	String findAll = "select * from member";
	String updateByObject = "update member set member_name = :memberName, email = :email where member_id = :memberId";
	String deleteById = "delete from member where member_id = :memberId";
	String deleteAll = "delete from member";

	@Override
	public void insert(Member product) {
		Map<String, Object> paramMap = toParamMap(product);
		jdbcTemplate.update(insert, paramMap);
	}

	@Override
	public Optional<Member> findById(Long memberId) {
		try {
			return Optional.ofNullable(
				jdbcTemplate.queryForObject(findById, Collections.singletonMap("memberId", memberId),
					memberRowMapper));
		} catch (DataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public List<Member> findAll() {
		return jdbcTemplate.query(findAll, memberRowMapper);
	}

	@Override
	public void updateByObject(Member member) {
		try {
			jdbcTemplate.update(updateByObject, toParamMap(member));

		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteById(Long memberId) {
		jdbcTemplate.update(deleteById, Collections.singletonMap("memberId", memberId));
	}

	@Override
	public void deleteAll() {
		jdbcTemplate.update(deleteAll, Collections.emptyMap());
	}
}
