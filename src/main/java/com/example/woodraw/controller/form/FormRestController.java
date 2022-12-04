package com.example.woodraw.controller.form;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.woodraw.controller.dto.form.FormRequestDto;
import com.example.woodraw.controller.dto.form.FormResponseDto;
import com.example.woodraw.service.form.FormService;
import com.example.woodraw.service.member.MemberService;

@RestController
public class FormRestController {

	private final FormService formService;

	public FormRestController(FormService formService) {
		this.formService = formService;
	}

	@GetMapping("/api/v1/forms")
	public ResponseEntity<List<FormResponseDto>> findAll() {
		List<FormResponseDto> formResponseDtoList = formService.findAll();
		return ResponseEntity.ok(formResponseDtoList);
	}

	@GetMapping("/api/v1/form/{formId}")
	public ResponseEntity<FormResponseDto> findById(@PathVariable Long formId) {
		FormResponseDto memberResponseDtoList = formService.findById(formId);
		return ResponseEntity.ok(memberResponseDtoList);
	}

	@PostMapping("/api/v1/form")
	public ResponseEntity<Void> insert(@RequestBody FormRequestDto formRequestDto) {
		formService.insert(formRequestDto);
		return ResponseEntity.ok().build();
	}

	@PatchMapping("/api/v1/form")
	public ResponseEntity<Void> updateByObject(@RequestBody FormRequestDto formRequestDto) {
		formService.updateByObject(formRequestDto);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/api/v1/form/{formId}")
	public ResponseEntity<Void> delete(@PathVariable Long formId) {
		formService.deleteById(formId);
		return ResponseEntity.ok().build();
	}
}
