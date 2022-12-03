package com.example.woodraw.service.event;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.woodraw.controller.dto.event.EventRequestDto;
import com.example.woodraw.controller.dto.event.EventResponseDto;
import com.example.woodraw.domain.event.Event;
import com.example.woodraw.repository.event.EventRepository;

@Service
public class EventService {

	private final EventRepository eventRepository;

	public EventService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	public void insert(EventRequestDto eventRequestDto) {
		eventRepository.insert(eventRequestDto.toEvent());
	}

	public EventResponseDto findById(Long eventId) {
		return eventRepository.findById(eventId).orElseThrow(IllegalArgumentException::new).toEventResponseDto();
	}

	public List<EventResponseDto> findAll() {
		return eventRepository.findAll().stream().map(Event::toEventResponseDto).collect(Collectors.toList());
	}

	public void updateByObject(EventRequestDto eventRequestDto) {
		eventRepository.updateByObject(eventRequestDto.toEvent());
	}

	public void deleteById(Long eventId) {
		eventRepository.deleteById(eventId);
	}

	public void deleteAll() {
		eventRepository.deleteAll();
	}
}
