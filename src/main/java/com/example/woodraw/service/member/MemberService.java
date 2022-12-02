package com.example.woodraw.service.member;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.woodraw.domain.member.Member;
import com.example.woodraw.repository.member.MemberRepository;

@Service
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public void insert(Member member) {
		memberRepository.insert(member);
	}

	public Member findById(Long memberId) {
		return memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
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
