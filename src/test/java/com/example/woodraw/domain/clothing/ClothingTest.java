package com.example.woodraw.domain.clothing;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClothingTest {

	@Test
	@DisplayName("0이상의 가격이 입력되면 검증에 성공한다.")
	void priceValidationSuccessTest() {

		assertThatNoException().isThrownBy(() -> new Shoe(1L, 100, "나이키"));

	}

	@Test
	@DisplayName("0 미만의 가격이 입력되면 검증에 실패한다.")
	void priceValidationFailTest() {

		assertThatThrownBy(() -> new Shoe(1L, -20, "나이키"));

	}

}