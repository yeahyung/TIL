package com.example.java_test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

class StudyTest {

	@Test
	@DisplayName("스터디 만들기")
	void create() {
		Study study = new Study(10);

		// 3번째 assertTrue가 실행되기 위해선 앞의 2개의 테스트가 반드시 통과해야함
		assertNotNull(study);
		assertEquals(StudyStatus.DRAFT, study.getStudyStatus(), "스터디를 처음 만들면 DRAFT 상태여야한다.");
		assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 합니다.");

		// assertAll을 통해 앞의 2개의 테스트가 실패해도 3번째 assertTrue가 실행되어 해당 테스트의 결과를 알 수 있음
		assertAll(
			() -> assertNotNull(study),
			() -> assertEquals(StudyStatus.DRAFT, study.getStudyStatus(), "스터디를 처음 만들면 DRAFT 상태여야한다."),
			() -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 합니다.")
		);

		IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
			() -> new Study(-10));
		String message = illegalArgumentException.getMessage();
		assertEquals("limit은 0보다 커야합니다", message);

		// 아래 assertTimeout은 테스트가 모두 종료되어야 종료됨, timeout으로 설정한 100ms가 지나고 테스트가 종료되지 않았다면 종료되지 않음
		assertTimeout(Duration.ofMillis(1000), () -> {
			new Study(10);
			Thread.sleep(200);
		});

		// 아래 assertTimeoutPreemptively 는 timeout으로 설정한 100ms가 지나면 바로 테스트 코드가 종료됨
		assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
			new Study(10);
			Thread.sleep(200);
		}); // 별도의 Thread로 실행되기 때문에 ThreadLocal을 사용하는 테스트 코드는 예상치 못한 에러가 발생할 수 있음
			// ex) Transaction rollback이 안될 수 있음
	}

	@Test
	@DisplayName("조건에 따라 테스트 실행하기")
	void create_new_study1() {
		String test_env = System.getenv("TEST_ENV");

		// assumeTrue가 통과해야 그 아래 테스트 코드가 실행되며 통과하지 않으면 Test는 무시됨(테스트 실패로 처리되지 않음)
		// 특정 환경(dev, beta 등)에서만 실행해야 하는 테스트 코드가 있다면 assumeTrue를 활용하라.
		assumeTrue("LOCAL".equals(test_env));

		// 혹은 아래와 같이 실행 가능
		assumingThat("LOCAL".equals(test_env), () -> {
			System.out.println("local");
		});

		assumingThat("DEV".equals(test_env), () -> {
			System.out.println("dev");
		});
	}

	@Test
	@DisplayName("환경 변수에 따라 테스트 실행하기")
	@EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")
	void create_new_study2() {

	}

	@Test
	@DisplayName("OS 환경에 따라 테스트 실행하기")
	@EnabledOnOs({OS.MAC, OS.LINUX})
	void create_new_study3() {

	}

	@Test
	@DisplayName("JAVA 환경에 따라 테스트 실행하기")
	@EnabledOnJre({JRE.JAVA_8, JRE.JAVA_11})
	void create_new_study4() {

	}


	@Test
	@Disabled
	void create1() {
		System.out.println("create1");
	}

	/*
	BeforeAll, AfterAll은 반환 타입이 void 여야 하며, static method 여야함
	 */
	@BeforeAll
	static void beforeAll() {
		System.out.println("Before All");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("After All");
	}

	@BeforeEach
	void beforeEach() {
		System.out.println("Before Each");
	}

	@AfterEach
	void afterEach() {
		System.out.println("After Each");
	}

}