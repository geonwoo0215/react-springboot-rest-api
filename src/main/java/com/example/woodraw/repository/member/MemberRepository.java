package com.example.woodraw.repository.member;

import java.util.List;
import java.util.Optional;

import com.example.woodraw.domain.member.Member;

public interface MemberRepository {

	void insert(Member member);

	Optional<Member> findById(Long id);

	List<Member> findAll();

	void updateByObject(Member member);

	void deleteById(Long id);

	void deleteAll();
}
