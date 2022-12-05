package com.example.woodraw.repository.event;

import java.util.List;
import java.util.Optional;

import com.example.woodraw.domain.event.Event;

public interface EventRepository {

	Long insert(Event event);

	Optional<Event> findById(Long id);

	List<Event> findAll();

	void deleteById(Long id);

	void deleteAll();

}
