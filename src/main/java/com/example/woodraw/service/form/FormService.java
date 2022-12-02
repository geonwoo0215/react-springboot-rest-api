package com.example.woodraw.service.form;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.woodraw.domain.form.Form;
import com.example.woodraw.repository.form.FormJdbcRepository;

@Service
public class FormService {

	private final FormJdbcRepository formJdbcRepository;

	public FormService(FormJdbcRepository formJdbcRepository) {
		this.formJdbcRepository = formJdbcRepository;
	}

	public void insert(Form form) {
		formJdbcRepository.insert(form);
	}

	public Form findById(Long formId) {
		return formJdbcRepository.findById(formId).orElseThrow(IllegalArgumentException::new);
	}

	public List<Form> findAll() {
		return formJdbcRepository.findAll();
	}

	public void updateByObject(Form form) {
		formJdbcRepository.updateByObject(form);
	}

	public void deleteById(Long formId) {
		formJdbcRepository.deleteById(formId);
	}

	public void deleteAll() {
		formJdbcRepository.deleteAll();
	}

}
