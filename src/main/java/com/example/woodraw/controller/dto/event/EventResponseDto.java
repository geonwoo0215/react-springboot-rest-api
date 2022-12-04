package com.example.woodraw.controller.dto.event;

import java.time.LocalDateTime;

public class EventResponseDto {

	private Long eventId;

	private Long productId;

	private LocalDateTime deadLine;

	public EventResponseDto(Long eventId, Long productId, LocalDateTime deadLine) {
		this.eventId = eventId;
		this.productId = productId;
		this.deadLine = deadLine;
	}

	public Long getEventId() {
		return eventId;
	}

	public Long getProductId() {
		return productId;
	}

	public LocalDateTime getDeadLine() {
		return deadLine;
	}
}
