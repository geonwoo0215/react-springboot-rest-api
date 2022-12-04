package com.example.woodraw.repository.product;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.example.woodraw.domain.product.Product;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductJdbcRepositoryTest {

	DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
			.addScript("schema.sql")
			.build();
	}

	NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource());
	}

	ProductJdbcRepository productJdbcRepository;

	@BeforeAll
	void setUp() {
		productJdbcRepository = new ProductJdbcRepository(namedParameterJdbcTemplate());
	}

	@AfterEach
	void clear() {
		productJdbcRepository.deleteAll();
	}

	@Test
	@DisplayName("제품을 저장하고 id로 조회하여 성공적으로 반환한다.")
	void findById() {

		String productName = "나이키";
		Integer price = 1500;

		//given
		Product product = new Product(1L, productName, price);
		Long productId = productJdbcRepository.insert(product);

		//when
		Optional<Product> savedProduct = productJdbcRepository.findById(productId);

		//then
		Assertions.assertThat(savedProduct).isPresent();
		Assertions.assertThat(savedProduct.get().getProductName()).isEqualTo(productName);
		Assertions.assertThat(savedProduct.get().getPrice()).isEqualTo(price);

	}

	@Test
	@DisplayName("제품을 여러개 저장하고 모든 제품을 리스트에 저장하여 반환한다.")
	void findAllTest() {

		//given
		Product product1 = new Product(null, "나이키", 1500);
		Product product2 = new Product(null, "뉴발란스", 3000);
		productJdbcRepository.insert(product1);
		productJdbcRepository.insert(product2);

		//when
		List<Product> productList = productJdbcRepository.findAll();

		//then
		Assertions.assertThat(productList).hasSize(2);

	}

	@Test
	@DisplayName("파라미터로 product 객체를 받아 성공적으로 업데이트 한다.")
	void updateByObjectSuccessTest() {

		String productName = "아디다스";
		Integer price = 1000;

		//given
		Product product = new Product(null, "나이키", 1500);
		Long productId = productJdbcRepository.insert(product);
		Product updateProduct = new Product(productId, "아디다스", 1000);

		//when
		productJdbcRepository.updateByObject(updateProduct);
		Optional<Product> updatedProduct = productJdbcRepository.findById(productId);

		//then
		Assertions.assertThat(updatedProduct).isPresent();
		Assertions.assertThat(updatedProduct.get().getProductName()).isEqualTo(productName);
		Assertions.assertThat(updatedProduct.get().getPrice()).isEqualTo(price);


	}

	@Test
	@DisplayName("파라미터로 id를 받아 제품을 삭제한다.")
	void deleteByIdSuccessTest() {

		//given
		Product product = new Product(null, "나이키", 1500);
		Long productId = productJdbcRepository.insert(product);

		//when
		productJdbcRepository.deleteById(productId);
		Optional<Product> savedProduct = productJdbcRepository.findById(productId);

		//then
		Assertions.assertThat(savedProduct).isEmpty();
	}

}