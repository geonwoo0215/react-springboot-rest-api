package com.example.woodraw.controller.dto.form;

import java.time.LocalDateTime;

import com.example.woodraw.domain.product.Size;

public class FormResponseDto {

	private Long formId;

	private Long memberId;

	private Long eventId;

	private LocalDateTime submission;

	private Size size;

	public FormResponseDto(Long formId, Long memberId, Long eventId, LocalDateTime submission,
		Size size) {
		this.formId = formId;
		this.memberId = memberId;
		this.eventId = eventId;
		this.submission = submission;
		this.size = size;
	}

	public Long getFormId() {
		return formId;
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
}
