package com.example.woodraw.domain.form;

import java.time.LocalDateTime;
import java.util.Objects;

import com.example.woodraw.controller.dto.form.FormRequestDto;
import com.example.woodraw.controller.dto.form.FormResponseDto;
import com.example.woodraw.domain.product.Size;

public class Form {

	private final Long formId;

	private final Long eventId;

	private final Size size;

	private final String email;

	public Form(Long formId, Long eventId,Size size, String email) {
		this.formId = formId;
		this.eventId = eventId;
		this.size = size;
		this.email = email;
	}

	public Long getFormId() {
		return formId;
	}

	public Long getEventId() {
		return eventId;
	}

	public Size getSize() {
		return size;
	}

	public String getEmail() {
		return email;
	}

	public FormResponseDto toFormResponseDto() {
		return new FormResponseDto(this.formId, this.eventId, this.size, this.email);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Form form = (Form)o;
		return Objects.equals(getFormId(), form.getFormId()) && Objects.equals(getEventId(),
			form.getEventId()) && getSize() == form.getSize() && Objects.equals(getEmail(), form.getEmail());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getFormId(), getEventId(), getSize(), getEmail());
	}
}
