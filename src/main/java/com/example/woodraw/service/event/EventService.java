package com.example.woodraw.service.event;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.woodraw.domain.event.Event;
import com.example.woodraw.repository.event.EventRepository;

@Service
public class EventService {

	private final EventRepository eventRepository;

	public EventService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	public void insert(Event event) {
		eventRepository.insert(event);
	}

	public Event findById(Long eventId) {
		return eventRepository.findById(eventId).orElseThrow(IllegalArgumentException::new);
	}

	public List<Event> findAll() {
		return eventRepository.findAll();
	}

	public void updateByObject(Event event) {
		eventRepository.updateByObject(event);
	}

	public void deleteById(Long eventId) {
		eventRepository.deleteById(eventId);
	}

	public void deleteAll() {
		eventRepository.deleteAll();
	}
}
