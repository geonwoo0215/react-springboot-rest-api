package com.example.woodraw.controller.dto.event;

import java.time.LocalDateTime;

public class EventResponseDto {

	private Long eventId;

	private Long productId;

	public EventResponseDto(Long eventId, Long productId) {
		this.eventId = eventId;
		this.productId = productId;
	}

	public Long getEventId() {
		return eventId;
	}

	public Long getProductId() {
		return productId;
	}

}
