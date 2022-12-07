package com.example.woodraw.repository.participant;

import com.example.woodraw.domain.product.Size;
import com.example.woodraw.domain.participant.Participant;

import java.util.List;

public interface ParticipantRepository {

	List<Participant> allocate();

	List<Long> findWinner(Long eventId);

	void insert(Participant winner);

	List<Long> pickWinner(Long eventId, Size size, Integer quantity);

	void updateWinner(Long formId, Long memberId);

	Long findFormIdByMemberId(Long memberId, Long eventId);
}
