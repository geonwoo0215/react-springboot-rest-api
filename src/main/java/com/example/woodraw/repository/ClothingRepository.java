package com.example.woodraw.repository;

import java.util.List;

import com.example.woodraw.domain.clothing.Clothing;

public interface ClothingRepository {

	void insert(Clothing clothing);

	Clothing findById(Long id);

	List<Clothing> findAll();

	void updatePriceById(Long id, int price);

	void updateNameById(Long id, String name);

	void deleteById(Long id);

	void deleteAll();

}
