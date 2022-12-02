package com.example.woodraw.domain.form;

import java.time.LocalDateTime;

import com.example.woodraw.domain.product.Size;

public class Form {

	private final Long formId;

	private final Long memberId;

	private final Long eventId;

	private final LocalDateTime submission;

	private final Size size;

	public Form(Long formId, Long memberId, Long eventId, LocalDateTime submission,
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
