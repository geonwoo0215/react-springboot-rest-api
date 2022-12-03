package com.example.woodraw.repository.event;

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
import org.springframework.test.context.jdbc.Sql;

import com.example.woodraw.domain.event.Event;
import com.example.woodraw.domain.product.Product;
import com.example.woodraw.repository.product.ProductJdbcRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EventJdbcRepositoryTest {
	DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
			.addScripts("schema.sql")
			.build();
	}

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	EventJdbcRepository eventJdbcRepository;
	ProductJdbcRepository productJdbcRepository;

	@BeforeAll
	void setup() {
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource());
		eventJdbcRepository = new EventJdbcRepository(namedParameterJdbcTemplate);
		productJdbcRepository = new ProductJdbcRepository(namedParameterJdbcTemplate);
	}

	@AfterEach
	void clear() {
		eventJdbcRepository.deleteAll();
		productJdbcRepository.deleteAll();
	}

	@Test
	@DisplayName("이벤트를 저장하고 id로 조회하여 성공적으로 반환한다.")
	void findByIdTest() {

		LocalDateTime localDateTime = LocalDateTime.now().withNano(0);

		//given
		Product product = new Product(null, "나이키", 1500);
		Long productId = productJdbcRepository.insert(product);
		Event event = new Event(null, productId, localDateTime);
		Long eventId = eventJdbcRepository.insert(event);

		//when
		Optional<Event> savedEvent = eventJdbcRepository.findById(eventId);

		//then
		Assertions.assertThat(savedEvent).isPresent();
		Assertions.assertThat(savedEvent.get().getDeadLine()).isEqualTo(localDateTime);

	}

	@Test
	@DisplayName("이벤트를 저장하고 모든 제품을 리스트에 담아 반환한다.")
	void findAllTest() {

		//given
		Product product1 = new Product(null, "나이키", 1500);
		Product product2 = new Product(null, "아디다스", 2000);
		Long productId1 = productJdbcRepository.insert(product1);
		Long productId2 = productJdbcRepository.insert(product2);

		Event event1 = new Event(null, productId1, LocalDateTime.now().withNano(0));
		Event event2 = new Event(null, productId2, LocalDateTime.now().withNano(0));
		eventJdbcRepository.insert(event1);
		eventJdbcRepository.insert(event2);

		//when
		List<Event> eventList = eventJdbcRepository.findAll();

		//then
		Assertions.assertThat(eventList).hasSize(2);
	}

	@Test
	@DisplayName("파라미터로 event 객체를 받아 성공적으로 업데이트 한다.")
	void updateByObjectSuccessTest() {

		LocalDateTime localDateTime = LocalDateTime.now().withNano(0);

		//given
		Product product = new Product(null, "나이키", 1500);
		Long productId = productJdbcRepository.insert(product);

		Event event = new Event(null, productId, localDateTime);
		Long eventId = eventJdbcRepository.insert(event);
		Event updateEvent = new Event(eventId, productId, localDateTime.plusHours(2));

		//when
		eventJdbcRepository.updateByObject(updateEvent);
		Optional<Event> updatedEvent = eventJdbcRepository.findById(eventId);

		//then
		Assertions.assertThat(updatedEvent).isPresent();
		Assertions.assertThat(updatedEvent.get().getDeadLine()).isEqualTo(localDateTime.plusHours(2));

	}

	@Test
	@DisplayName("파라미터로 id를 받아 이벤트를 삭제한다.")
	void deleteByIdSuccessTest() {

		//given
		Product product = new Product(null, "나이키", 1500);
		Long productId = productJdbcRepository.insert(product);

		Event event = new Event(null, productId, LocalDateTime.now().withNano(0));
		Long eventId = eventJdbcRepository.insert(event);

		//when
		eventJdbcRepository.deleteById(eventId);
		Optional<Event> savedEvent = eventJdbcRepository.findById(eventId);

		//then
		Assertions.assertThat(savedEvent).isEmpty();
	}
}