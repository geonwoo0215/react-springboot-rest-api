package com.example.woodraw.service.form;

import java.util.List;
import java.util.stream.Collectors;

import com.example.woodraw.controller.dto.form.FormUpdateDto;
import org.springframework.stereotype.Service;

import com.example.woodraw.controller.dto.form.FormRequestDto;
import com.example.woodraw.controller.dto.form.FormResponseDto;
import com.example.woodraw.domain.form.Form;
import com.example.woodraw.repository.form.FormJdbcRepository;

@Service
public class FormService {

	private final FormJdbcRepository formJdbcRepository;

	public FormService(FormJdbcRepository formJdbcRepository) {
		this.formJdbcRepository = formJdbcRepository;
	}

	public void insert(FormRequestDto formRequestDto) {
		formJdbcRepository.insert(formRequestDto.toForm());
	}

	public FormResponseDto findById(Long formId) {
		return formJdbcRepository.findById(formId).orElseThrow(IllegalArgumentException::new).toFormResponseDto();
	}

	public List<FormResponseDto> findAll() {
		return formJdbcRepository.findAll().stream().map(Form::toFormResponseDto).collect(Collectors.toList());
	}

	public void updateByObject(FormUpdateDto formUpdateDto) {
		formJdbcRepository.updateByObject(formUpdateDto.toForm());
	}

	public void deleteById(Long formId) {
		formJdbcRepository.deleteById(formId);
	}

	public void deleteAll() {
		formJdbcRepository.deleteAll();
	}

}
