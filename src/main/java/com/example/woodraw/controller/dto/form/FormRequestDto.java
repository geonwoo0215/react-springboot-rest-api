package com.example.woodraw.controller.dto.form;

import java.time.LocalDateTime;

import com.example.woodraw.domain.form.Form;
import com.example.woodraw.domain.product.Size;

public class FormRequestDto {

	private Long eventId;

	private Size size;

	private String email;

	public FormRequestDto(Long eventId, Size size, String email) {
		this.eventId = eventId;
		this.size = size;
		this.email = email;
	}


	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getEventId() {
		return eventId;
	}

	public Size getSize() {
		return size;
	}

	public Form toForm() {
		return new Form(null, this.eventId, this.size, this.email);
	}
}
