package com.example.woodraw.domain.clothing;

import static com.example.woodraw.domain.Size.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.woodraw.domain.Product;

class ProductTest {

	@Test
	@DisplayName("0이상의 가격이 입력되면 검증에 성공한다.")
	void priceValidationSuccessTest() {

		assertThatNoException().isThrownBy(() -> new Product(1L, "나이키", 100,SIZE_250));

	}

	@Test
	@DisplayName("0 미만의 가격이 입력되면 검증에 실패한다.")
	void priceValidationFailTest() {

		assertThatThrownBy(() -> new Product(1L, "나이키", -20,SIZE_250));

	}

}