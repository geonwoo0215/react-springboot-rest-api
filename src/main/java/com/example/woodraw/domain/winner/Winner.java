package com.example.woodraw.domain.winner;

public class Winner {

	private final Long memberId;

	private final Long formId;

	public Winner(Long memberId, Long formId) {
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
