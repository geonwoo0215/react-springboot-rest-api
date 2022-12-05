package com.example.woodraw.controller.dto.event;

import java.time.LocalDateTime;

import com.example.woodraw.domain.event.Event;

public class EventRequestDto {

	private Long productId;

	public EventRequestDto(Long productId) {
		this.productId = productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getProductId() {
		return productId;
	}

	public Event toEvent() {
		return new Event(null, this.productId);
	}
}
