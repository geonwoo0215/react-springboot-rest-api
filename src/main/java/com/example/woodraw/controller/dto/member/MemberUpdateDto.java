package com.example.woodraw.controller.dto.member;

import com.example.woodraw.domain.member.Member;

public class MemberUpdateDto {

	private Long memberId;

	private String memberName;

	private String email;

	public MemberUpdateDto(Long memberId, String memberName, String email) {
		this.memberId = memberId;
		this.memberName = memberName;
		this.email = email;
	}

	public Member toMember() {
		return new Member(this.memberId, this.memberName, this.email);
	}

	public Long getMemberId() {
		return memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getEmail() {
		return email;
	}

}
