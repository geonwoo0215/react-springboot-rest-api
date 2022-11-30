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

import com.example.woodraw.domain.Product;
import com.example.woodraw.domain.Size;

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
	void insertSuccessTest() {

		//given
		Product product = new Product(1L, "나이키", 1500, Size.SIZE_250);
		productJdbcRepository.insert(product);

		//when
		Optional<Product> savedProduct = productJdbcRepository.findById(product.getProductId());

		//then
		Assertions.assertThat(savedProduct).isPresent();
		Assertions.assertThat(savedProduct.get()).isEqualTo(product);

	}

	@Test
	@DisplayName("제품을 저장하고 잘못된 id 값으로 조회하면 반환을 실패한다.")
	void insertFailTest() {

		//given
		Product product = new Product(1L, "나이키", 1500, Size.SIZE_250);
		productJdbcRepository.insert(product);

		//when
		Optional<Product> savedProduct = productJdbcRepository.findById(2L);

		//then
		Assertions.assertThat(savedProduct).isEmpty();
	}

	@Test
	@DisplayName("제품을 여러개 저장하고 모든 제품을 리스트에 저장하여 반환한다.")
	void findAllTest() {

		//given
		Product product1 = new Product(1L, "나이키", 1500, Size.SIZE_250);
		Product product2 = new Product(2L, "뉴발란스", 3000, Size.SIZE_250);
		productJdbcRepository.insert(product1);
		productJdbcRepository.insert(product2);

		//when
		List<Product> productList = productJdbcRepository.findAll();

		//then
		Assertions.assertThat(productList).hasSize(2);
		Assertions.assertThat(productList).contains(product1).contains(product2);

	}

	@Test
	@DisplayName("파라미터로 product 객체를 받아 성공적으로 업데이트 한다.")
	void updateByObjectSuccessTest() {

		//given
		Product product = new Product(1L, "나이키", 1500, Size.SIZE_250);
		Product updateProduct = new Product(1L, "아디다스", 1000, Size.SIZE_250);
		productJdbcRepository.insert(product);

		//when
		productJdbcRepository.updateByObject(updateProduct);
		Optional<Product> updatedProduct = productJdbcRepository.findById(product.getProductId());

		//then
		Assertions.assertThat(updatedProduct).isPresent();
		Assertions.assertThat(updatedProduct).isEqualTo(updatedProduct);

	}

	@Test
	@DisplayName("파라미터로 id를 받아 제품을 삭제한다.")
	void deleteByIdSuccessTest() {

		//given
		Product product = new Product(1L, "나이키", 1500, Size.SIZE_250);
		productJdbcRepository.insert(product);

		//when
		productJdbcRepository.deleteById(product.getProductId());
		Optional<Product> savedProduct = productJdbcRepository.findById(product.productId);

		//then
		Assertions.assertThat(savedProduct).isEmpty();
	}

}