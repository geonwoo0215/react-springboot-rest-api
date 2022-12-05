package com.example.woodraw.service.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.woodraw.controller.dto.member.MemberRequestDto;
import com.example.woodraw.controller.dto.member.MemberUpdateDto;
import com.example.woodraw.domain.member.Member;
import com.example.woodraw.repository.member.MemberJdbcRepository;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	@Mock
	MemberJdbcRepository memberJdbcRepository;

	@InjectMocks
	MemberService memberService;

	@Test
	@DisplayName("멤버를 저장한다. - 성공")
	void insertTest() {

		//given
		MemberRequestDto memberRequestDto = new MemberRequestDto("이건우", "gw0215");
		Mockito.doNothing().when(memberJdbcRepository).insert(memberRequestDto.toMember());

		//when
		memberService.insert(memberRequestDto);

		//then
		Mockito.verify(memberJdbcRepository).insert(memberRequestDto.toMember());
	}

	@Test
	@DisplayName("멤버를 조회한다. - 성공")
	void findByIdTest() {

		//given
		Member member = new Member(1L, "이건우", "gw0215");
		Mockito.when(memberJdbcRepository.findById(member.getMemberId())).thenReturn(Optional.of(member));

		//when
		memberService.findById(member.getMemberId());

		//then
		Mockito.verify(memberJdbcRepository).findById(member.getMemberId());
	}

	@Test
	@DisplayName("전체 멤버를 조회한다. - 성공")
	void findAllTest() {

		//given
		Member member = new Member(1L, "이건우", "gw0215");
		List<Member> memberList = new ArrayList<>();
		memberList.add(member);
		Mockito.when(memberJdbcRepository.findAll()).thenReturn(memberList);

		//when
		memberService.findAll();

		//then
		Mockito.verify(memberJdbcRepository).findAll();

	}

	@Test
	@DisplayName("멤버를 수정한다. - 성공")
	void updateByObjectTest() {

		//given
		MemberUpdateDto memberUpdateDto = new MemberUpdateDto(1L, "이건우", "gw0215");
		Mockito.doNothing().when(memberJdbcRepository).updateByObject(memberUpdateDto.toMember());

		//when
		memberService.updateByObject(memberUpdateDto);

		//then
		Mockito.verify(memberJdbcRepository).updateByObject(memberUpdateDto.toMember());

	}

	@Test
	@DisplayName("멤버를 삭제한다. - 성공")
	void deleteByIdTest() {

		//given
		Member member = new Member(1L, "이건우", "gw0215");
		Mockito.doNothing().when(memberJdbcRepository).deleteById(member.getMemberId());

		//when
		memberService.deleteById(member.getMemberId());

		//then
		Mockito.verify(memberJdbcRepository).deleteById(member.getMemberId());
	}

	@Test
	@DisplayName("전체 멤버를 삭제한다. - 성공")
	void deleteAll() {

		//given
		Mockito.doNothing().when(memberJdbcRepository).deleteAll();

		//when
		memberService.deleteAll();

		Mockito.verify(memberJdbcRepository).deleteAll();
	}
}