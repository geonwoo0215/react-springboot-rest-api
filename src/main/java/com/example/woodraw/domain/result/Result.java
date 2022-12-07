package com.example.woodraw.domain.result;

import com.example.woodraw.controller.dto.resultDto.ResultResponseDto;
import com.example.woodraw.domain.member.Member;
import com.example.woodraw.domain.product.Size;

import java.util.List;

public class Result {

    private final Size size;

    private final Member member;

    public Result(Size size, Member member) {
        this.size = size;
        this.member = member;
    }

    public Size getSize() {
        return size;
    }

    public Member getMember() {
        return member;
    }

    public ResultResponseDto toResultResponseDto() {
        return new ResultResponseDto(this.size, this.member);
    }
}
