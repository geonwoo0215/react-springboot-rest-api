package com.example.woodraw.domain.form;

import java.time.LocalDateTime;
import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Form form = (Form)o;
		return Objects.equals(getFormId(), form.getFormId()) && Objects.equals(getMemberId(),
			form.getMemberId()) && Objects.equals(getEventId(), form.getEventId()) && Objects.equals(
			getSubmission(), form.getSubmission()) && getSize() == form.getSize();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getFormId(), getMemberId(), getEventId(), getSubmission(), getSize());
	}
}
