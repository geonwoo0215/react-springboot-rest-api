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

import com.example.woodraw.domain.product.ProductDetail;
import com.example.woodraw.domain.product.Size;
import com.example.woodraw.repository.productdetail.ProductDetailJdbcRepository;

@ExtendWith(MockitoExtension.class)
class ProductDetailServiceTest {

	@Mock
	ProductDetailJdbcRepository productDetailJdbcRepository;

	@InjectMocks
	ProductDetailService productDetailService;

	@Test
	@DisplayName("제품 상세를 저장한다. - 성공")
	void insertTest() {

		//given
		ProductDetail productDetail = new ProductDetail(1L, 1L, Size.SIZE_250, 50);
		Mockito.doNothing().when(productDetailJdbcRepository).insert(productDetail);

		//when
		productDetailService.insert(productDetail);

		//then
		Mockito.verify(productDetailJdbcRepository).insert(productDetail);
	}

	@Test
	@DisplayName("제품 상세를 조회한다. - 성공")
	void findByIdTest() {

		//given
		ProductDetail productDetail = new ProductDetail(1L, 1L, Size.SIZE_250, 50);
		Mockito.when(productDetailJdbcRepository.findById(productDetail.getDetailId())).thenReturn(Optional.of(productDetail));

		//when
		productDetailService.findById(productDetail.getDetailId());

		//then
		Mockito.verify(productDetailJdbcRepository).findById(productDetail.getDetailId());
	}

	@Test
	@DisplayName("전체 제품 상세를 조회한다. - 성공")
	void findAllTest() {

		//given
		ProductDetail productDetail = new ProductDetail(1L, 1L, Size.SIZE_250, 50);
		List<ProductDetail> productDetailList = new ArrayList<>();
		productDetailList.add(productDetail);
		Mockito.when(productDetailJdbcRepository.findAll()).thenReturn(productDetailList);

		//when
		productDetailService.findAll();

		//then
		Mockito.verify(productDetailJdbcRepository).findAll();

	}

	@Test
	@DisplayName("제품 상세를 수정한다. - 성공")
	void updateByObjectTest() {

		//given
		ProductDetail productDetail = new ProductDetail(1L, 1L, Size.SIZE_250, 50);
		Mockito.doNothing().when(productDetailJdbcRepository).updateByObject(productDetail);

		//when
		productDetailService.updateByObject(productDetail);

		//then
		Mockito.verify(productDetailJdbcRepository).updateByObject(productDetail);

	}

	@Test
	@DisplayName("제품 상세를 삭제한다. - 성공")
	void deleteByIdTest() {

		//given
		ProductDetail productDetail = new ProductDetail(1L, 1L, Size.SIZE_250, 50);
		Mockito.doNothing().when(productDetailJdbcRepository).deleteById(productDetail.getDetailId());

		//when
		productDetailService.deleteById(productDetail.getDetailId());

		//then
		Mockito.verify(productDetailJdbcRepository).deleteById(productDetail.getDetailId());
	}

	@Test
	@DisplayName("전체 제품 상세를 삭제한다. - 성공")
	void deleteAll() {

		//given
		Mockito.doNothing().when(productDetailJdbcRepository).deleteAll();

		//when
		productDetailService.deleteAll();

		Mockito.verify(productDetailJdbcRepository).deleteAll();
	}
}