package com.example.woodraw.service.member;

import java.util.List;

import com.example.woodraw.domain.member.Member;
import com.example.woodraw.repository.member.MemberRepository;

public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public void insert(Member member) {
		memberRepository.insert(member);
	}

	public Member findById(Long productId) {
		return memberRepository.findById(productId).orElseThrow(IllegalArgumentException::new);
	}

	public List<Member> findAll() {
		return memberRepository.findAll();
	}

	public void updateByObject(Member member) {
		memberRepository.updateByObject(member);
	}

	public void deleteById(Long memberId) {
		memberRepository.deleteById(memberId);
	}

	public void deleteAll() {
		memberRepository.deleteAll();
	}
}
