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

import com.example.woodraw.domain.product.ProductDetail;
import com.example.woodraw.domain.product.Size;

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

	ProductDetailJdbcRepository productDetailJdbcRepository;

	@BeforeAll
	void setUp() {
		productDetailJdbcRepository = new ProductDetailJdbcRepository(namedParameterJdbcTemplate());
	}

	@AfterEach
	void clear() {
		productDetailJdbcRepository.deleteAll();
	}

	@Test
	@DisplayName("멤버를 저장하고 id로 조회하여 성공적으로 반환한다.")
	void insertSuccessTest() {

		//given
		ProductDetail productDetail = new ProductDetail(1L, 1L, Size.SIZE_250, 50);

		//when
		productDetailJdbcRepository.insert(productDetail);
		Optional<ProductDetail> savedProductDetail = productDetailJdbcRepository.findById(productDetail.getDetailId());

		//then
		Assertions.assertThat(savedProductDetail).isPresent();
		Assertions.assertThat(savedProductDetail.get()).isEqualTo(productDetail);

	}

	@Test
	@DisplayName("멤버를 저장하고 모든 제품을 리스트에 담아 반환한다.")
	void findAllTest() {

		//given
		ProductDetail productDetail1 = new ProductDetail(1L, 1L, Size.SIZE_250, 50);
		ProductDetail productDetail2 = new ProductDetail(2L, 1L, Size.SIZE_260, 50);
		productDetailJdbcRepository.insert(productDetail1);
		productDetailJdbcRepository.insert(productDetail2);

		//when
		List<ProductDetail> productDetailListList = productDetailJdbcRepository.findAll();

		//then
		Assertions.assertThat(productDetailListList).hasSize(2);
		Assertions.assertThat(productDetailListList).contains(productDetail1).contains(productDetail2);
	}

	@Test
	@DisplayName("파라미터로 product 객체를 받아 성공적으로 업데이트 한다.")
	void updateByObjectSuccessTest() {

		//given
		ProductDetail productDetail = new ProductDetail(1L, 1L, Size.SIZE_250, 50);
		ProductDetail updateProductDetail = new ProductDetail(1L, 1L, Size.SIZE_250, 60);
		productDetailJdbcRepository.insert(productDetail);

		//when
		productDetailJdbcRepository.updateByObject(updateProductDetail);
		Optional<ProductDetail> updatedProductDetail = productDetailJdbcRepository.findById(productDetail.getDetailId());

		//then
		Assertions.assertThat(updatedProductDetail).isPresent();
		Assertions.assertThat(updatedProductDetail.get()).isEqualTo(updateProductDetail);

	}

	@Test
	@DisplayName("파라미터로 id를 받아 제품을 삭제한다.")
	void deleteByIdSuccessTest() {

		//given
		ProductDetail productDetail = new ProductDetail(1L, 1L, Size.SIZE_250, 50);
		productDetailJdbcRepository.insert(productDetail);

		//when
		productDetailJdbcRepository.deleteById(productDetail.getDetailId());
		Optional<ProductDetail> savedProductDetail = productDetailJdbcRepository.findById(productDetail.getDetailId());

		//then
		Assertions.assertThat(savedProductDetail).isEmpty();
	}


}