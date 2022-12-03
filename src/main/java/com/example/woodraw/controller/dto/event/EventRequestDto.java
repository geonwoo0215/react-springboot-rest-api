package com.example.woodraw.controller.dto.event;

import java.time.LocalDateTime;

import com.example.woodraw.domain.event.Event;

public class EventRequestDto {

	private Long productId;

	private LocalDateTime deadLine;

	public EventRequestDto(Long productId, LocalDateTime deadLine) {
		this.productId = productId;
		this.deadLine = deadLine;
	}

	public Long getProductId() {
		return productId;
	}

	public LocalDateTime getDeadLine() {
		return deadLine;
	}

	public Event toEvent() {
		return new Event(null, this.productId, this.deadLine);
	}
}
