package com.example.woodraw.service.participant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.woodraw.controller.dto.resultDto.ResultResponseDto;
import com.example.woodraw.domain.participant.Participant;
import com.example.woodraw.domain.product.Size;
import com.example.woodraw.domain.result.Result;
import com.example.woodraw.repository.event.EventJdbcRepository;
import com.example.woodraw.repository.form.FormJdbcRepository;
import com.example.woodraw.repository.member.MemberJdbcRepository;
import com.example.woodraw.repository.product.ProductJdbcRepository;
import com.example.woodraw.repository.productdetail.ProductDetailJdbcRepository;
import com.example.woodraw.repository.participant.ParticipantJdbcRepository;

@Service
public class ParticipantService {

    private final ParticipantJdbcRepository participantRepository;
    private final MemberJdbcRepository memberJdbcRepository;
    private final ProductDetailJdbcRepository productDetailJdbcRepository;
    private final EventJdbcRepository eventJdbcRepository;
    private final FormJdbcRepository formJdbcRepository;

    public ParticipantService(ParticipantJdbcRepository participantRepository,
        MemberJdbcRepository memberJdbcRepository,
        ProductDetailJdbcRepository productDetailJdbcRepository,
        EventJdbcRepository eventJdbcRepository, FormJdbcRepository formJdbcRepository) {
        this.participantRepository = participantRepository;
        this.memberJdbcRepository = memberJdbcRepository;
        this.productDetailJdbcRepository = productDetailJdbcRepository;
        this.eventJdbcRepository = eventJdbcRepository;
        this.formJdbcRepository = formJdbcRepository;
    }

    public void insert() {
        List<Participant> ParticipantList = participantRepository.allocate();
        ParticipantList.forEach(participantRepository::insert);
    }

    public void pickWinner(Long eventId) {
        Long productId = eventJdbcRepository.findProductIdByEventId(eventId);

        List<Size> sizeList = productDetailJdbcRepository.possibleSize(productId);

        for (Size size : sizeList) {
            Integer quantity = productDetailJdbcRepository.findQuantityProductId(productId, size);

            List<Long> memberIdList = participantRepository.pickWinner(eventId, size, quantity);

            for (Long memberId : memberIdList) {
                Long formId = participantRepository.findFormIdByMemberId(eventId, memberId);
                System.out.println(formId);
                participantRepository.updateWinner(formId,memberId);
            }

        }
    }

    public List<ResultResponseDto> finaAll(Long eventId) {
        List<ResultResponseDto> resultList = new ArrayList<>();

        List<Long> memberIdList = participantRepository.findWinner(eventId);

        for (Long memberId : memberIdList) {
            Long formId = participantRepository.findFormIdByMemberId(eventId, memberId);

            Size size = formJdbcRepository.findSizeById(formId);

            resultList.add(new Result(size, memberJdbcRepository.findById(memberId).orElseThrow()).toResultResponseDto());
        }

        return resultList;
    }
}
