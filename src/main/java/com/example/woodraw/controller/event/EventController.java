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
public class EventController {

	private final EventService eventService;

	public EventController(EventService eventService) {
		this.eventService = eventService;
	}

	@GetMapping("/api/v1/events")
	public String findAll(Model model) {
		List<EventResponseDto> eventResponseDtoList = eventService.findAll();
		model.addAttribute("eventList", eventResponseDtoList);
		return "event/events";

	}

	@GetMapping("/api/v1/event/{eventId}")
	public String findById(@PathVariable Long eventId,Model model) {
		EventResponseDto eventResponseDto = eventService.findById(eventId);
		model.addAttribute("event", eventResponseDto);
		return "event/eventInfo";
	}

	@PostMapping("/api/v1/product/{productId}/event")
	public String insert(@PathVariable Long productId, @ModelAttribute EventRequestDto formRequestDto) {
		formRequestDto.setProductId(productId);
		eventService.insert(formRequestDto);
		return "redirect:/api/v1/events";
	}

	@DeleteMapping("/api/v1/event/{eventId}")
	public String delete(@PathVariable Long eventId) {
		eventService.deleteById(eventId);
		return "redirect:/api/v1/events";
	}
}
