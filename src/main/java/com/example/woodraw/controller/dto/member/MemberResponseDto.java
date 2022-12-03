package com.example.woodraw.controller.dto.member;

public class MemberResponseDto {

	private Long memberId;

	private String memberName;

	private String email;

	public MemberResponseDto(Long memberId, String memberName, String email) {
		this.memberId = memberId;
		this.memberName = memberName;
		this.email = email;
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
