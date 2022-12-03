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
	void findByIdTest() {

		Size size = Size.SIZE_250;
		LocalDateTime localDateTime = LocalDateTime.now().withNano(0);

		//given
		Product product = new Product(null, "나이키", 1500);
		Long productId = productJdbcRepository.insert(product);

		Member member = new Member(null, "이건우", "gw0215");
		Long memberId = memberJdbcRepository.insert(member);

		Event event = new Event(null, productId, localDateTime);
		Long eventId = eventJdbcRepository.insert(event);

		Form form = new Form(null, memberId, eventId, localDateTime,
			size);
		Long formId = formJdbcRepository.insert(form);

		//when
		Optional<Form> savedForm = formJdbcRepository.findById(formId);

		//then
		Assertions.assertThat(savedForm).isPresent();
		Assertions.assertThat(savedForm.get().getSize()).isEqualTo(size);
		Assertions.assertThat(savedForm.get().getSubmission()).isEqualTo(localDateTime);
	}

	@Test
	@DisplayName("응모 신청서를 저장하고 모든 제품을 리스트에 담아 반환한다.")
	void findAllTest() {

		//given
		Product product = new Product(null, "나이키", 1500);
		Long productId = productJdbcRepository.insert(product);

		Member member1 = new Member(null, "이건우", "gw0215");
		Member member2 = new Member(null, "이건형", "gw0215");
		Long memberId1 = memberJdbcRepository.insert(member1);
		Long memberId2 = memberJdbcRepository.insert(member2);

		Event event = new Event(null, productId, LocalDateTime.now().withNano(0));
		Long eventId = eventJdbcRepository.insert(event);

		Form form1 = new Form(null, memberId1, eventId, LocalDateTime.now().withNano(0),
			Size.SIZE_250);
		Form form2 = new Form(null, memberId2, eventId, LocalDateTime.now().withNano(0),
			Size.SIZE_250);
		formJdbcRepository.insert(form1);
		formJdbcRepository.insert(form2);

		//when
		List<Form> eventList = formJdbcRepository.findAll();

		//then
		Assertions.assertThat(eventList).hasSize(2);
	}

	@Test
	@DisplayName("파라미터로 form 객체를 받아 성공적으로 업데이트 한다.")
	void updateByObjectSuccessTest() {

		LocalDateTime localDateTime = LocalDateTime.now().withNano(0);
		Size size = Size.SIZE_260;

		//given
		Product product = new Product(null, "나이키", 1500);
		Long productId = productJdbcRepository.insert(product);

		Member member = new Member(null, "이건우", "gw0215");
		Long memberId = memberJdbcRepository.insert(member);

		Event event = new Event(null, productId, localDateTime);
		Long eventId = eventJdbcRepository.insert(event);

		Form form = new Form(null, memberId, eventId, localDateTime,
			Size.SIZE_250);
		Long formId = formJdbcRepository.insert(form);

		Form updateForm = new Form(formId, memberId, eventId, localDateTime,
			Size.SIZE_260);
		//when
		formJdbcRepository.updateByObject(updateForm);
		Optional<Form> updatedForm = formJdbcRepository.findById(formId);

		//then
		Assertions.assertThat(updatedForm).isPresent();
		Assertions.assertThat(updatedForm.get().getSubmission()).isEqualTo(localDateTime);
		Assertions.assertThat(updatedForm.get().getSize()).isEqualTo(size);

	}

	@Test
	@DisplayName("파라미터로 id를 받아 응모 신청서를 삭제한다.")
	void deleteByIdSuccessTest() {

		//given
		Product product = new Product(null, "나이키", 1500);
		Long productId = productJdbcRepository.insert(product);
		Member member = new Member(null, "이건우", "gw0215");
		Long memberId = memberJdbcRepository.insert(member);
		Event event = new Event(null, productId, LocalDateTime.now().withNano(0));
		Long eventId = eventJdbcRepository.insert(event);
		Form form = new Form(null, memberId, eventId, LocalDateTime.now().withNano(0),
			Size.SIZE_250);

		Long formId = formJdbcRepository.insert(form);

		//when
		formJdbcRepository.deleteById(formId);
		Optional<Form> savedForm = formJdbcRepository.findById(formId);

		//then
		Assertions.assertThat(savedForm).isEmpty();
	}
}