package com.example.woodraw.controller.dto.member;

import com.example.woodraw.domain.member.Member;

public class MemberRequestDto {

	private String memberName;

	private String email;

	public MemberRequestDto() {
	}

	public MemberRequestDto(String memberName, String email) {
		this.memberName = memberName;
		this.email = email;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getEmail() {
		return email;
	}

	public Member toMember() {
		return new Member(null, this.memberName, this.email);
	}
}
