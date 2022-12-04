package com.example.woodraw.controller.member;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.woodraw.controller.dto.member.MemberRequestDto;
import com.example.woodraw.controller.dto.member.MemberResponseDto;
import com.example.woodraw.controller.dto.member.MemberUpdateDto;
import com.example.woodraw.service.member.MemberService;

@Controller
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/api/v1/members")
	public String findAll(Model model) {
		List<MemberResponseDto> memberList = memberService.findAll();
		model.addAttribute("memberList", memberList);
		return "member/members";
	}

	@GetMapping("/api/v1/member/{memberId}")
	public String findById(@PathVariable Long memberId,Model model) {
		MemberResponseDto memberResponseDto = memberService.findById(memberId);
		model.addAttribute("member", memberResponseDto);
		return "member/memberInfo";
	}

	@GetMapping("/api/v1/member")
	public String create() {
		return "member/memberForm";
	}

	@PostMapping("/api/v1/member")
	public String insert(@ModelAttribute MemberRequestDto memberRequestDto) {
		memberService.insert(memberRequestDto);
		return "redirect:/api/v1/members";
	}

	@GetMapping("/api/v1/member/{memberId}/edit")
	public String edit(@PathVariable Long memberId, Model model) {
		MemberResponseDto memberResponseDto = memberService.findById(memberId);
		model.addAttribute("member", memberResponseDto);
		return "member/memberEditForm";
	}

	@PatchMapping("/api/v1/member/{memberId}/edit")
	public String updateByObject(@PathVariable Long memberId,@RequestBody MemberUpdateDto memberUpdateDto) {
		memberService.updateByObject(memberUpdateDto);
		return "redirect:/api/v1/members";
	}

	@DeleteMapping("/api/v1/member/{memberId}")
	public String delete(@PathVariable Long memberId) {
		memberService.deleteById(memberId);
		return "redirect:/api/v1/members";
	}
}
