package com.example.woodraw.controller.dto.resultDto;

import com.example.woodraw.domain.member.Member;
import com.example.woodraw.domain.product.Size;

public class ResultResponseDto {

	private final Size size;

	private final Member member;

	public ResultResponseDto(Size size, Member member) {
		this.size = size;
		this.member = member;
	}

	public Size getSize() {
		return size;
	}

	public Member getMember() {
		return member;
	}

}
