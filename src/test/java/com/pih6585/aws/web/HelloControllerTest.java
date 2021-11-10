package com.pih6585.aws.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.pih6585.aws.config.auth.SecurityConfig;

@WebMvcTest(controllers = HelloController.class,
	excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
	}
)
public class HelloControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(roles = "USER")
	public void hello가_리턴된다() throws Exception {
		String hello = "hello";

		mockMvc.perform(get("/hello"))
			.andExpect(status().isOk())
			.andExpect(content().string(hello));
	}

	@Test
	@WithMockUser(roles = "USER")
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