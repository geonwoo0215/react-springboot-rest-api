package com.example.woodraw.domain.winner;

public class Participant {

	private final Long memberId;

	private final Long formId;

	public Participant(Long memberId, Long formId) {
		this.memberId = memberId;
		this.formId = formId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public Long getFormId() {
		return formId;
	}
}
