package com.example.woodraw.domain.result;

import com.example.woodraw.domain.member.Member;
import com.example.woodraw.domain.product.Size;

import java.util.List;

public class Result {

    private final Size size;

    private final List<Member> memberList;

    public Result(Size size, List<Member> memberList) {
        this.size = size;
        this.memberList = memberList;
    }

    public Size getSize() {
        return size;
    }

    public List<Member> getMemberList() {
        return memberList;
    }
}
