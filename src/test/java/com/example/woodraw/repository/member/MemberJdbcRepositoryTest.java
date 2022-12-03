package com.example.woodraw.repository.member;

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

import com.example.woodraw.domain.member.Member;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MemberJdbcRepositoryTest {

	DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScripts("schema.sql").build();
	}

	NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource());
	}

	MemberJdbcRepository memberJdbcRepository;

	@BeforeAll
	void setup() {
		memberJdbcRepository = new MemberJdbcRepository(namedParameterJdbcTemplate());
	}

	@AfterEach
	void clear() {
		memberJdbcRepository.deleteAll();
	}

	@Test
	@DisplayName("멤버를 저장하고 id로 조회하여 성공적으로 반환한다.")
	void findByIdTest() {

		String memberName = "이건우";
		String email = "gw980215";

		//given
		Member member = new Member(null, memberName, email);
		Long savedId = memberJdbcRepository.insert(member);

		//when
		Optional<Member> savedMember = memberJdbcRepository.findById(savedId);

		//then
		Assertions.assertThat(savedMember).isPresent();
		Assertions.assertThat(savedMember.get().getMemberName()).isEqualTo(memberName);
		Assertions.assertThat(savedMember.get().getEmail()).isEqualTo(email);

	}

	@Test
	@DisplayName("멤버를 저장하고 모든 제품을 리스트에 담아 반환한다.")
	void findAllTest() {

		//given
		Member member1 = new Member(null, "이건우", "gw980215");
		Member member2 = new Member(null, "블랙독", "blackDog");
		memberJdbcRepository.insert(member1);
		memberJdbcRepository.insert(member2);

		//when
		List<Member> memberList = memberJdbcRepository.findAll();

		//then
		Assertions.assertThat(memberList).hasSize(2);
	}

	@Test
	@DisplayName("파라미터로 product 객체를 받아 성공적으로 업데이트 한다.")
	void updateByObjectSuccessTest() {

		String memberName = "이건형";
		String email = "gh950401";

		//given
		Member member = new Member(null, "이건우", "gw980215");
		Long savedId = memberJdbcRepository.insert(member);
		Member updateMember = new Member(savedId, "이건형", "gh950401");

		//when
		memberJdbcRepository.updateByObject(updateMember);
		Optional<Member> updatedMember = memberJdbcRepository.findById(savedId);

		//then
		Assertions.assertThat(updatedMember).isPresent();
		Assertions.assertThat(updatedMember.get().getMemberName()).isEqualTo(memberName);
		Assertions.assertThat(updatedMember.get().getEmail()).isEqualTo(email);

	}

	@Test
	@DisplayName("파라미터로 id를 받아 제품을 삭제한다.")
	void deleteByIdSuccessTest() {

		//given
		Member member = new Member(null, "이건우", "gw980215");
		Long memberId = memberJdbcRepository.insert(member);

		//when
		memberJdbcRepository.deleteById(memberId);
		Optional<Member> savedProduct = memberJdbcRepository.findById(memberId);

		//then
		Assertions.assertThat(savedProduct).isEmpty();
	}

}