package com.example.woodraw.controller.participant;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.woodraw.controller.dto.resultDto.ResultResponseDto;
import com.example.woodraw.service.participant.ParticipantService;

@Controller
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @PostMapping("/api/v1/event/{eventId}/result")
    public String pickWinner(@PathVariable Long eventId) {
        participantService.insert();
        participantService.pickWinner(eventId);
        return "redirect:/api/v1/event/{eventId}";
    }

    @GetMapping("/api/v1/event/{eventId}/result")
    public String findAll(@PathVariable Long eventId,Model model) {
        List<ResultResponseDto> resultList = participantService.finaAll(eventId);
        model.addAttribute("resultList", resultList);
        return "result/resultInfo";
    }
}
