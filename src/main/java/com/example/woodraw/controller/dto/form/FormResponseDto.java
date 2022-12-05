package com.example.woodraw.controller.dto.form;

import java.time.LocalDateTime;

import com.example.woodraw.domain.product.Size;

public class FormResponseDto {

	private Long formId;

	private Long eventId;

	private Size size;

	private String email;

	public FormResponseDto(Long formId, Long eventId,Size size, String email) {
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
}
