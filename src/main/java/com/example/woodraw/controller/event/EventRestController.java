package com.example.woodraw.controller.event;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.woodraw.controller.dto.event.EventRequestDto;
import com.example.woodraw.controller.dto.event.EventResponseDto;
import com.example.woodraw.service.event.EventService;


@Controller
public class EventRestController {

	private final EventService eventService;

	public EventRestController(EventService eventService) {
		this.eventService = eventService;
	}

	@GetMapping("/api/v1/events")
	public String findAll(Model model) {
		List<EventResponseDto> eventResponseDtoList = eventService.findAll();
		model.addAttribute("eventResponseDtoList", eventResponseDtoList);
		return "event/events";

	}

	@GetMapping("/api/v1/event/{eventId}")
	public ResponseEntity<EventResponseDto> findById(@PathVariable Long eventId) {
		EventResponseDto memberResponseDtoList = eventService.findById(eventId);
		return ResponseEntity.ok(memberResponseDtoList);
	}

	@GetMapping("/api/v1/event")
	public String create() {
		return "event/eventForm";
	}

	@PostMapping("/api/v1/event")
	public String insert(@ModelAttribute EventRequestDto formRequestDto) {
		eventService.insert(formRequestDto);
		return "redirect:/api/vi/events";
	}

	@PatchMapping("/api/v1/event")
	public ResponseEntity<Void> updateByObject(@RequestBody EventRequestDto formRequestDto) {
		eventService.updateByObject(formRequestDto);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/api/v1/event/{eventId}")
	public ResponseEntity<Void> delete(@PathVariable Long eventId) {
		eventService.deleteById(eventId);
		return ResponseEntity.ok().build();
	}
}
