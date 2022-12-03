package com.example.woodraw.service.member;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.woodraw.controller.dto.member.MemberRequestDto;
import com.example.woodraw.controller.dto.member.MemberResponseDto;
import com.example.woodraw.domain.member.Member;
import com.example.woodraw.repository.member.MemberRepository;

@Service
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public void insert(MemberRequestDto memberRequestDto) {
		memberRepository.insert(memberRequestDto.toMember());
	}

	public MemberResponseDto findById(Long memberId) {
		return memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new).toMemberResponseDto();
	}

	public List<MemberResponseDto> findAll() {
		return memberRepository.findAll().stream().map(Member::toMemberResponseDto).collect(Collectors.toList());
	}

	public void updateByObject(MemberRequestDto memberRequestDto) {
		memberRepository.updateByObject(memberRequestDto.toMember());
	}

	public void deleteById(Long memberId) {
		memberRepository.deleteById(memberId);
	}

	public void deleteAll() {
		memberRepository.deleteAll();
	}
}
