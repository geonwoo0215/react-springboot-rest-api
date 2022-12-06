package com.example.woodraw.repository.winner;

import com.example.woodraw.domain.product.Size;
import com.example.woodraw.domain.winner.Participant;

import java.util.List;

public interface ParticipantRepository {

	List<Participant> allocate();

	void insert(Participant winner);

	List<Long> pick(Long eventId, Size size);

	List<Size> possibleSize(Long productId);

}
