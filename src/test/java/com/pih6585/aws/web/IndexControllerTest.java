package com.pih6585.aws.web;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void 메인페이지_로딩() {
		String body = this.restTemplate.getForObject("/", String.class);

		assertThat(body).contains("스프링부트로 시작하는 웹 서비스");
	}
}