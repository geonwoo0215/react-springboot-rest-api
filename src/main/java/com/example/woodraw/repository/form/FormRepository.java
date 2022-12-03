package com.example.woodraw.repository.form;

import java.util.List;
import java.util.Optional;

import com.example.woodraw.domain.form.Form;

public interface FormRepository {
	Long insert(Form form);

	Optional<Form> findById(Long id);

	List<Form> findAll();

	void updateByObject(Form form);

	void deleteById(Long id);

	void deleteAll();

}
