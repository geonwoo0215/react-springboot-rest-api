package com.example.woodraw.controller.participant;

import com.example.woodraw.controller.dto.event.EventResponseDto;
import com.example.woodraw.domain.result.Result;
import com.example.woodraw.service.participant.ParticipantService;
import com.example.woodraw.service.participant.WinnerService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping("/api/v1/event/{eventId}/winner")
    public String pickWinner(@PathVariable Long eventId, Model model) {
       participantService.pick(eventId);
       return "winner/winners";
    }

    @GetMapping("/api/v1/winner/{winnerId}")
    public String findById(@PathVariable Long eventId,Model model) {
        EventResponseDto eventResponseDto = eventService.findById(eventId);
        model.addAttribute("event", eventResponseDto);
        return "event/eventInfo";
    }
}
