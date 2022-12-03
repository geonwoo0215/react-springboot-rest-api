package com.example.woodraw.controller.dto.event;

import java.time.LocalDateTime;

public class EventResponseDto {

	private Long eventId;

	private Long productId;

	private LocalDateTime localDateTime;

	public EventResponseDto(Long eventId, Long productId, LocalDateTime localDateTime) {
		this.eventId = eventId;
		this.productId = productId;
		this.localDateTime = localDateTime;
	}

	public Long getEventId() {
		return eventId;
	}

	public Long getProductId() {
		return productId;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
}
