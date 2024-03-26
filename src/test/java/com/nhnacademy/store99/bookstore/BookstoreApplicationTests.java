package com.nhnacademy.store99.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class BookstoreApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void test() {
		String databaseName = jdbcTemplate.queryForObject("SELECT DATABASE()", String.class);
		System.out.println(databaseName);
	}
}
