package com.example.woodraw.controller.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.woodraw.controller.dto.product.ProductRequestDto;
import com.example.woodraw.domain.product.Product;
import com.example.woodraw.repository.product.ProductJdbcRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	ProductJdbcRepository productJdbcRepository;

	@Test
	@DisplayName("전체 제품을 가져온다 - 성공")
	void findAllTest() throws Exception {

		Product product = new Product(null, "나이키", 1500);

		productJdbcRepository.insert(product);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.*").exists());
	}

	@Test
	@DisplayName("제품을 저장한다. - 성공")
	void insertTest() throws Exception {
		ProductRequestDto productRequestDto = new ProductRequestDto("나이키", 1500);

		String json = objectMapper.writeValueAsString(productRequestDto);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(MockMvcResultHandlers.print());

	}

}