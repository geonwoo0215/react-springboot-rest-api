package com.example.woodraw.domain.member;

import java.util.Objects;

import com.example.woodraw.controller.dto.member.MemberResponseDto;

public class Member {

	private final Long memberId;

	private final String memberName;

	private final String email;

	public Member(Long memberId, String memberName, String email) {
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

	public MemberResponseDto toMemberResponseDto() {
		return new MemberResponseDto(this.memberId, this.memberName, this.email);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Member member = (Member)o;
		return Objects.equals(getMemberId(), member.getMemberId()) && Objects.equals(getMemberName(),
			member.getMemberName()) && Objects.equals(getEmail(), member.getEmail());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getMemberId(), getMemberName(), getEmail());
	}
}
