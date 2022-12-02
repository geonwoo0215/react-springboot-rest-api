package com.example.woodraw.repository.form;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
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

import com.example.woodraw.domain.event.Event;
import com.example.woodraw.domain.form.Form;
import com.example.woodraw.domain.member.Member;
import com.example.woodraw.domain.product.Product;
import com.example.woodraw.domain.product.Size;
import com.example.woodraw.repository.event.EventJdbcRepository;
import com.example.woodraw.repository.member.MemberJdbcRepository;
import com.example.woodraw.repository.product.ProductJdbcRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FormJdbcRepositoryTest {
	DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
			.addScripts("schema.sql")
			.build();
	}

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	FormJdbcRepository formJdbcRepository;
	MemberJdbcRepository memberJdbcRepository;
	EventJdbcRepository eventJdbcRepository;
	ProductJdbcRepository productJdbcRepository;

	@BeforeAll
	void setup() {
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource());
		formJdbcRepository = new FormJdbcRepository(namedParameterJdbcTemplate);
		memberJdbcRepository = new MemberJdbcRepository(namedParameterJdbcTemplate);
		eventJdbcRepository = new EventJdbcRepository(namedParameterJdbcTemplate);
		productJdbcRepository = new ProductJdbcRepository(namedParameterJdbcTemplate);
	}

	@AfterEach
	void clear() {
		formJdbcRepository.deleteAll();
		memberJdbcRepository.deleteAll();
		eventJdbcRepository.deleteAll();
		productJdbcRepository.deleteAll();
	}

	@Test
	@DisplayName("응모 신청서 저장하고 id로 조회하여 성공적으로 반환한다.")
	void insertSuccessTest() {

		//given
		Product product = new Product(1L, "나이키", 1500);
		Event event = new Event(1L, product.getProductId(), LocalDateTime.now().withNano(0));
		Member member = new Member(1L, "이건우", "gw0215");
		Form form = new Form(1L, member.getMemberId(), event.getEventId(), LocalDateTime.now().withNano(0),
			Size.SIZE_250);

		//when
		memberJdbcRepository.insert(member);
		productJdbcRepository.insert(product);
		eventJdbcRepository.insert(event);
		formJdbcRepository.insert(form);

		Optional<Form> savedForm = formJdbcRepository.findById(form.getFormId());

		//then
		Assertions.assertThat(savedForm).isPresent();
		Assertions.assertThat(savedForm.get()).isEqualTo(form);

	}

	@Test
	@DisplayName("응모 신청서를 저장하고 모든 제품을 리스트에 담아 반환한다.")
	void findAllTest() {

		//given
		Product product = new Product(1L, "나이키", 1500);
		Member member1 = new Member(1L, "이건우", "gw0215");
		Event event = new Event(1L, product.getProductId(), LocalDateTime.now().withNano(0));
		Form form1 = new Form(1L, member1.getMemberId(), event.getEventId(), LocalDateTime.now().withNano(0),
			Size.SIZE_250);
		Member member2 = new Member(2L, "장주영", "wkdwndud");
		Form form2 = new Form(2L, member2.getMemberId(), event.getEventId(), LocalDateTime.now().withNano(0),
			Size.SIZE_250);

		memberJdbcRepository.insert(member1);
		memberJdbcRepository.insert(member2);
		productJdbcRepository.insert(product);
		eventJdbcRepository.insert(event);
		formJdbcRepository.insert(form1);
		formJdbcRepository.insert(form2);


		//when
		List<Form> eventList = formJdbcRepository.findAll();

		//then
		Assertions.assertThat(eventList).hasSize(2);
		Assertions.assertThat(eventList).contains(form1).contains(form2);
	}

	@Test
	@DisplayName("파라미터로 form 객체를 받아 성공적으로 업데이트 한다.")
	void updateByObjectSuccessTest() {

		//given
		Product product = new Product(1L, "나이키", 1500);
		Event event = new Event(1L, product.getProductId(), LocalDateTime.now().withNano(0));
		Member member = new Member(1L, "이건우", "gw0215");
		Form form = new Form(1L, member.getMemberId(), event.getEventId(), LocalDateTime.now().withNano(0),
			Size.SIZE_250);

		Form updateForm = new Form(1L, member.getMemberId(), event.getEventId(), LocalDateTime.now().withNano(0),
			Size.SIZE_260);

		memberJdbcRepository.insert(member);
		productJdbcRepository.insert(product);
		eventJdbcRepository.insert(event);
		formJdbcRepository.insert(form);

		//when
		formJdbcRepository.updateByObject(updateForm);
		Optional<Form> updatedForm = formJdbcRepository.findById(form.getFormId());

		//then
		Assertions.assertThat(updatedForm).isPresent();
		Assertions.assertThat(updatedForm.get()).isEqualTo(updateForm);

	}

	@Test
	@DisplayName("파라미터로 id를 받아 응모 신청서를 삭제한다.")
	void deleteByIdSuccessTest() {

		//given
		Product product = new Product(1L, "나이키", 1500);
		Event event = new Event(1L, product.getProductId(), LocalDateTime.now().withNano(0));
		Member member = new Member(1L, "이건우", "gw0215");
		Form form = new Form(1L, member.getMemberId(), event.getEventId(), LocalDateTime.now().withNano(0),
			Size.SIZE_250);

		memberJdbcRepository.insert(member);
		productJdbcRepository.insert(product);
		eventJdbcRepository.insert(event);
		formJdbcRepository.insert(form);

		//when
		formJdbcRepository.deleteById(form.getFormId());
		Optional<Form> savedForm = formJdbcRepository.findById(form.getFormId());

		//then
		Assertions.assertThat(savedForm).isEmpty();
	}
}