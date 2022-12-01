package com.example.woodraw.domain.event;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event {

	private final Long eventId;

	private final Long productId;

	private final LocalDateTime deadLine;

	public Event(Long eventId, Long productId, LocalDateTime deadLine) {
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Event event = (Event)o;
		return Objects.equals(getEventId(), event.getEventId()) && Objects.equals(getProductId(),
			event.getProductId()) && Objects.equals(getDeadLine(), event.getDeadLine());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getEventId(), getProductId(), getDeadLine());
	}
}
