package com.example.woodraw.repository.productdetail;

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
import com.example.woodraw.domain.product.ProductDetail;
import com.example.woodraw.domain.product.Size;
import com.example.woodraw.repository.product.ProductJdbcRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductDetailJdbcRepositoryTest {

	DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
			.addScripts("schema.sql")
			.build();
	}

	NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource());
	}

	ProductJdbcRepository productJdbcRepository;
	ProductDetailJdbcRepository productDetailJdbcRepository;

	@BeforeAll
	void setUp() {
		productJdbcRepository = new ProductJdbcRepository(namedParameterJdbcTemplate());
		productDetailJdbcRepository = new ProductDetailJdbcRepository(namedParameterJdbcTemplate());
	}

	@AfterEach
	void clear() {
		productDetailJdbcRepository.deleteAll();
	}

	@Test
	@DisplayName("제품 상세를 저장하고 id로 조회하여 성공적으로 반환한다.")
	void findByIdTest() {

		Size size = Size.SIZE_250;
		Integer quantity = 50;

		//given
		Product product = new Product(null, "나이키", 1500);
		Long productId = productJdbcRepository.insert(product);
		ProductDetail productDetail = new ProductDetail(null, productId, size, quantity);
		Long productDetailId = productDetailJdbcRepository.insert(productDetail);

		//when
		Optional<ProductDetail> savedProductDetail = productDetailJdbcRepository.findById(productDetailId);

		//then
		Assertions.assertThat(savedProductDetail).isPresent();
		Assertions.assertThat(savedProductDetail.get().getSize()).isEqualTo(size);
		Assertions.assertThat(savedProductDetail.get().getQuantity()).isEqualTo(quantity);

	}

	@Test
	@DisplayName("제품 상세를 저장하고 모든 제품상세를 리스트에 담아 반환한다.")
	void findAllTest() {

		//given
		ProductDetail productDetail1 = new ProductDetail(null, 1L, Size.SIZE_250, 50);
		ProductDetail productDetail2 = new ProductDetail(null, 1L, Size.SIZE_260, 50);
		productDetailJdbcRepository.insert(productDetail1);
		productDetailJdbcRepository.insert(productDetail2);

		//when
		List<ProductDetail> productDetailListList = productDetailJdbcRepository.findAll();

		//then
		Assertions.assertThat(productDetailListList).hasSize(2);
	}

	@Test
	@DisplayName("파라미터로 productDetail 객체를 받아 성공적으로 업데이트 한다.")
	void updateByObjectSuccessTest() {

		Size size = Size.SIZE_260;
		Integer quantity = 60;

		//given
		ProductDetail productDetail = new ProductDetail(null, 1L, Size.SIZE_250, 50);
		Long productDetailId = productDetailJdbcRepository.insert(productDetail);
		ProductDetail updateProductDetail = new ProductDetail(productDetailId, 1L, size, quantity);

		//when
		productDetailJdbcRepository.updateByObject(updateProductDetail);
		Optional<ProductDetail> updatedProductDetail = productDetailJdbcRepository.findById(productDetailId);

		//then
		Assertions.assertThat(updatedProductDetail).isPresent();
		Assertions.assertThat(updatedProductDetail.get().getSize()).isEqualTo(size);
		Assertions.assertThat(updatedProductDetail.get().getQuantity()).isEqualTo(quantity);

	}

	@Test
	@DisplayName("파라미터로 id를 받아 제품상세를 삭제한다.")
	void deleteByIdSuccessTest() {

		//given
		ProductDetail productDetail = new ProductDetail(null, 1L, Size.SIZE_250, 50);
		Long productDetailId = productDetailJdbcRepository.insert(productDetail);

		//when
		productDetailJdbcRepository.deleteById(productDetailId);
		Optional<ProductDetail> savedProductDetail = productDetailJdbcRepository.findById(productDetailId);

		//then
		Assertions.assertThat(savedProductDetail).isEmpty();
	}


}