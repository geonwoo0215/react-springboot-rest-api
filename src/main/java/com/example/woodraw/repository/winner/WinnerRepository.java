package com.example.woodraw.repository.winner;

import java.util.List;

import com.example.woodraw.domain.winner.Winner;

public interface WinnerRepository {

	List<Winner> allocate();

	void insert(Winner winner);

	List<Winner> pick();

}
