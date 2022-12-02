package com.example.woodraw.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.woodraw.domain.product.Product;
import com.example.woodraw.repository.product.ProductJdbcRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	@Mock
	ProductJdbcRepository productJdbcRepository;

	@InjectMocks
	productService productService;

	@Test
	@DisplayName("제품을 저장한다. - 성공")
	void insertTest() {

		//given
		Product product = new Product(1L, "나이키", 1500);
		Mockito.doNothing().when(productJdbcRepository).insert(product);

		//when
		productService.insert(product);

		//then
		Mockito.verify(productJdbcRepository).insert(product);
	}

	@Test
	@DisplayName("제품을 조회한다. - 성공")
	void findByIdTest() {

		//given
		Product product = new Product(1L, "나이키", 1500);
		Mockito.when(productJdbcRepository.findById(product.getProductId())).thenReturn(Optional.of(product));

		//when
		productService.findById(product.getProductId());

		//then
		Mockito.verify(productJdbcRepository).findById(product.getProductId());
	}

	@Test
	@DisplayName("전체 제품을 조회한다. - 성공")
	void findAllTest() {

		//given
		Product product = new Product(1L, "나이키", 1500);
		List<Product> productList = new ArrayList<>();
		productList.add(product);
		Mockito.when(productJdbcRepository.findAll()).thenReturn(productList);

		//when
		productService.findAll();

		//then
		Mockito.verify(productJdbcRepository).findAll();

	}

	@Test
	@DisplayName("제품을 수정한다. - 성공")
	void updateByObjectTest() {

		//given
		Product product = new Product(1L, "나이키", 1500);
		Mockito.doNothing().when(productJdbcRepository).updateByObject(product);

		//when
		productService.updateByObject(product);

		//then
		Mockito.verify(productJdbcRepository).updateByObject(product);

	}

	@Test
	@DisplayName("제품을 삭제한다. - 성공")
	void deleteByIdTest() {

		//given
		Product product = new Product(1L, "나이키", 1500);
		Mockito.doNothing().when(productJdbcRepository).deleteById(product.getProductId());

		//when
		productService.deleteById(product.getProductId());

		//then
		Mockito.verify(productJdbcRepository).deleteById(product.getProductId());
	}

	@Test
	@DisplayName("전체 제품을 삭제한다. - 성공")
	void deleteAll() {

		//given
		Mockito.doNothing().when(productJdbcRepository).deleteAll();

		//when
		productService.deleteAll();

		Mockito.verify(productJdbcRepository).deleteAll();
	}

}