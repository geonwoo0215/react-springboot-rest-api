package com.example.woodraw.controller.dto.form;

import java.time.LocalDateTime;

import com.example.woodraw.domain.form.Form;
import com.example.woodraw.domain.product.Size;

public class FormRequestDto {

	private Long memberId;

	private Long eventId;

	private LocalDateTime submission;

	private Size size;

	public FormRequestDto(Long memberId, Long eventId, LocalDateTime submission,
		Size size) {
		this.memberId = memberId;
		this.eventId = eventId;
		this.submission = submission;
		this.size = size;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public Long getEventId() {
		return eventId;
	}

	public LocalDateTime getSubmission() {
		return submission;
	}

	public Size getSize() {
		return size;
	}

	public Form toForm() {
		return new Form(null, this.memberId, this.eventId, this.submission, this.size);
	}
}
