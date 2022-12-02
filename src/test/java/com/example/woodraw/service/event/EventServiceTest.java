package com.example.woodraw.service.event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.woodraw.domain.event.Event;
import com.example.woodraw.repository.event.EventJdbcRepository;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

	@Mock
	EventJdbcRepository eventJdbcRepository;

	@InjectMocks
	EventService eventService;

	@Test
	@DisplayName("멤버를 저장한다. - 성공")
	void insertTest() {

		//given
		Event event = new Event(1L, 1L, LocalDateTime.now().withNano(0));
		Mockito.doNothing().when(eventJdbcRepository).insert(event);

		//when
		eventService.insert(event);

		//then
		Mockito.verify(eventJdbcRepository).insert(event);
	}

	@Test
	@DisplayName("멤버를 조회한다. - 성공")
	void findByIdTest() {

		//given
		Event event = new Event(1L, 1L, LocalDateTime.now().withNano(0));
		Mockito.when(eventJdbcRepository.findById(event.getEventId())).thenReturn(Optional.of(event));

		//when
		eventService.findById(event.getEventId());

		//then
		Mockito.verify(eventJdbcRepository).findById(event.getEventId());
	}

	@Test
	@DisplayName("전체 멤버를 조회한다. - 성공")
	void findAllTest() {

		//given
		Event event = new Event(1L, 1L, LocalDateTime.now().withNano(0));
		List<Event> eventList = new ArrayList<>();
		eventList.add(event);
		Mockito.when(eventJdbcRepository.findAll()).thenReturn(eventList);

		//when
		eventService.findAll();

		//then
		Mockito.verify(eventJdbcRepository).findAll();

	}

	@Test
	@DisplayName("멤버를 수정한다. - 성공")
	void updateByObjectTest() {

		//given
		Event event = new Event(1L, 1L, LocalDateTime.now().withNano(0));
		Mockito.doNothing().when(eventJdbcRepository).updateByObject(event);

		//when
		eventService.updateByObject(event);

		//then
		Mockito.verify(eventJdbcRepository).updateByObject(event);

	}

	@Test
	@DisplayName("멤버를 삭제한다. - 성공")
	void deleteByIdTest() {

		//given
		Event event = new Event(1L, 1L, LocalDateTime.now().withNano(0));
		Mockito.doNothing().when(eventJdbcRepository).deleteById(event.getEventId());

		//when
		eventService.deleteById(event.getEventId());

		//then
		Mockito.verify(eventJdbcRepository).deleteById(event.getEventId());
	}

	@Test
	@DisplayName("전체 멤버를 삭제한다. - 성공")
	void deleteAll() {

		//given
		Mockito.doNothing().when(eventJdbcRepository).deleteAll();

		//when
		eventService.deleteAll();

		//then
		Mockito.verify(eventJdbcRepository).deleteAll();
	}
}