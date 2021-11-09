package com.pih6585.aws.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class HelloControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void hello가_리턴된다() throws Exception{
		String hello = "hello";

		mockMvc.perform(get("/hello"))
			.andExpect(status().isOk())
			.andExpect(content().string(hello));
	}

	@Test
	public void helloDto가_리턴된다() throws Exception {
		String name = "hello";
		int amount = 1000;

		mockMvc.perform(
			get("/hello/dto")
				.param("name", name)
				.param("amount", String.valueOf(amount)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name", is(name)))
			.andExpect(jsonPath("$.amount", is(amount)));
	}

}