package com.example.woodraw.domain.member;

import java.util.Objects;

public class Member {

	private final Long userId;

	private final String userName;

	private final String email;

	public Member(Long userId, String userName, String email) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
	}

	public Long getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Member member = (Member)o;
		return Objects.equals(getUserId(), member.getUserId()) && Objects.equals(getUserName(),
			member.getUserName()) && Objects.equals(getEmail(), member.getEmail());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUserId(), getUserName(), getEmail());
	}
}
