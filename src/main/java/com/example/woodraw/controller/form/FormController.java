package com.example.woodraw.controller.form;

import java.util.List;

import com.example.woodraw.controller.dto.form.FormUpdateDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.woodraw.controller.dto.form.FormRequestDto;
import com.example.woodraw.controller.dto.form.FormResponseDto;
import com.example.woodraw.service.form.FormService;

@Controller
public class FormController {

	private final FormService formService;

	public FormController(FormService formService) {
		this.formService = formService;
	}

	@GetMapping("/api/v1/forms")
	public String findAll(Model model) {
		List<FormResponseDto> formList = formService.findAll();
		model.addAttribute("formList", formList);
		return "form/forms";
	}

	@GetMapping("/api/v1/form/{formId}")
	public String findById(@PathVariable Long formId, Model model) {
		FormResponseDto formResponseDto = formService.findById(formId);
		model.addAttribute("form", formResponseDto);
		return "form/formInfo";
	}

	@GetMapping("/api/v1/member/{memberId}/form")
	public String create(@PathVariable Long memberId, Model model) {
		model.addAttribute("memberId", memberId);
		return "form/formForm";
	}
	@PostMapping("/api/v1/member/{memberId}/form")
	public String insert(@PathVariable Long memberId, @ModelAttribute FormRequestDto formRequestDto) {
		formRequestDto.setMemberId(memberId);
		formService.insert(formRequestDto);
		return "redirect:/api/v1/forms";
	}

	@GetMapping("/api/v1/form/{formId}/edit")
	public String edit(@PathVariable Long formId, Model model) {
		FormResponseDto formResponseDto = formService.findById(formId);
		model.addAttribute("form", formResponseDto);
		return "form/formForm";
	}

	@PatchMapping("/api/v1/form/{formId}/edit")
	public String updateByObject(@PathVariable Long formId, @ModelAttribute FormUpdateDto formUpdateDto) {
		formUpdateDto.setFormId(formId);
		formService.updateByObject(formUpdateDto);
		return "redirect:/api/v1/forms";
	}

	@DeleteMapping("/api/v1/form/{formId}")
	public String delete(@PathVariable Long formId) {
		formService.deleteById(formId);
		return "redirect:/api/v1/forms";
	}
}
