package com.example.woodraw.service.participant;

import com.example.woodraw.domain.member.Member;
import com.example.woodraw.domain.product.Size;
import com.example.woodraw.domain.result.Result;
import com.example.woodraw.domain.winner.Participant;
import com.example.woodraw.repository.member.MemberJdbcRepository;
import com.example.woodraw.repository.winner.ParticipantJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {

    private final ParticipantJdbcRepository participantRepository;
    private final MemberJdbcRepository memberJdbcRepository;

    public ParticipantService(ParticipantJdbcRepository winnerJdbcRepository, MemberJdbcRepository memberJdbcRepository) {
        this.participantRepository = winnerJdbcRepository;
        this.memberJdbcRepository = memberJdbcRepository;
    }

    public void insert() {
        List<Participant> winnerList = participantRepository.allocate();
        winnerList.forEach(participantRepository::insert);
    }

    public List<Result> pick(Long eventId) {
        List<Size> sizeList = participantRepository.possibleSize(eventId);
        List<Result> resultList = new ArrayList<>();
        for (Size size : sizeList) {
            List<Long> list = participantRepository.pick(eventId, size);
            List<Member> memberList = new ArrayList<>();
            for (Long memberId : list) {
                Optional<Member> member = memberJdbcRepository.findById(memberId);
                memberList.add(member.orElseThrow());
            }
            resultList.add(new Result(size, memberList));
        }
        return resultList;
    }
}
