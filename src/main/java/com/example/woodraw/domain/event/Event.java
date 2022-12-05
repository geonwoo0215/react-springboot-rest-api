package com.example.woodraw.domain.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.example.woodraw.controller.dto.event.EventResponseDto;

public class Event {

	private final Long eventId;

	private final Long productId;

	public Event(Long eventId, Long productId) {
		this.eventId = eventId;
		this.productId = productId;
	}

	public Long getEventId() {
		return eventId;
	}

	public Long getProductId() {
		return productId;
	}

	public EventResponseDto toEventResponseDto() {
		return new EventResponseDto(this.getEventId(), this.productId);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Event event = (Event)o;
		return Objects.equals(getEventId(), event.getEventId()) && Objects.equals(getProductId(),
			event.getProductId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getEventId(), getProductId());
	}
}
