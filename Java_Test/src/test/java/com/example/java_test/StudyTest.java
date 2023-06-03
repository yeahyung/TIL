package com.example.java_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class StudyTest {

	@Test
	void create() {
		Study study = new Study();
		assertNotNull(study);

		System.out.println("create");
	}

	@Test
	@Disabled
	void create1() {
		System.out.println("create1");
	}

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