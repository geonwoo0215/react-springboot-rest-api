package com.example.woodraw.service.form;

import java.time.LocalDateTime;
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

import com.example.woodraw.controller.dto.form.FormRequestDto;
import com.example.woodraw.controller.dto.form.FormUpdateDto;
import com.example.woodraw.domain.form.Form;
import com.example.woodraw.domain.product.Size;
import com.example.woodraw.repository.form.FormJdbcRepository;

@ExtendWith(MockitoExtension.class)
class FormServiceTest {

	@Mock
	FormJdbcRepository formJdbcRepository;

	@InjectMocks
	FormService formService;

	@Test
	@DisplayName("응모 신청서를 저장한다. - 성공")
	void insertTest() {

		//given
		FormRequestDto formRequestDto = new FormRequestDto(1L, Size.SIZE_250,"gw0215");
		Mockito.doNothing().when(formJdbcRepository).insert(formRequestDto.toForm());

		//when
		formService.insert(formRequestDto);

		//then
		Mockito.verify(formJdbcRepository).insert(formRequestDto.toForm());
	}

	@Test
	@DisplayName("응모 신청서를 조회한다. - 성공")
	void findByIdTest() {

		//given
		Form form = new Form(1L, 1L,Size.SIZE_250,"gw0215");
		Mockito.when(formJdbcRepository.findById(form.getEventId())).thenReturn(Optional.of(form));

		//when
		formService.findById(form.getEventId());

		//then
		Mockito.verify(formJdbcRepository).findById(form.getEventId());
	}

	@Test
	@DisplayName("전체 응모 신청서를 조회한다. - 성공")
	void findAllTest() {

		//given
		Form form = new Form(1L, 1L, Size.SIZE_250,"gw0215");
		List<Form> formList = new ArrayList<>();
		formList.add(form);
		Mockito.when(formJdbcRepository.findAll()).thenReturn(formList);

		//when
		formService.findAll();

		//then
		Mockito.verify(formJdbcRepository).findAll();

	}

	@Test
	@DisplayName("응모 신청서를 수정한다. - 성공")
	void updateByObjectTest() {

		//given
		FormUpdateDto formUpdateDto = new FormUpdateDto(1L,1L, Size.SIZE_250,"gw0215");
		Mockito.doNothing().when(formJdbcRepository).updateByObject(formUpdateDto.toForm());

		//when
		formService.updateByObject(formUpdateDto);

		//then
		Mockito.verify(formJdbcRepository).updateByObject(formUpdateDto.toForm());

	}

	@Test
	@DisplayName("응모 신청서를 삭제한다. - 성공")
	void deleteByIdTest() {

		//given
		Form form = new Form(1L, 1L, Size.SIZE_250,"gw0215");
		Mockito.doNothing().when(formJdbcRepository).deleteById(form.getFormId());

		//when
		formService.deleteById(form.getFormId());

		//then
		Mockito.verify(formJdbcRepository).deleteById(form.getFormId());
	}

	@Test
	@DisplayName("전체 응모 신청서를 삭제한다. - 성공")
	void deleteAll() {

		//given
		Mockito.doNothing().when(formJdbcRepository).deleteAll();

		//when
		formService.deleteAll();

		//then
		Mockito.verify(formJdbcRepository).deleteAll();
	}
}