package com.solver.scrabble;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collection;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(Parameterized.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ScrabbleSolverControllerIntegrationTest {

	private String input;
	private String expectedResult;

	@ClassRule
	public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
	@Rule
	public final SpringMethodRule springMethodRule = new SpringMethodRule();
	@Autowired
	private MockMvc mockMvc;

	public ScrabbleSolverControllerIntegrationTest(String input, String expectedResult) {
		this.input = input;
		this.expectedResult = expectedResult.replace("\\", "");
	}

	@Parameterized.Parameters(name = "test Cases")
	public static Collection<Object[]> testCases() {
		return Arrays.asList(new Object[][] { { "hat", "[\"hat\",\"ah\",\"ha\",\"th\",\"at\",\"a\"]" },
				{ "aht", "[\"hat\",\"ah\",\"ha\",\"th\",\"at\",\"a\"]" },
				{ "HAT", "[\"hat\",\"ah\",\"ha\",\"th\",\"at\",\"a\"]" },
				{ "Hat", "[\"hat\",\"ah\",\"ha\",\"th\",\"at\",\"a\"]" }, { "zzz", "[]" }, { "ZZZ", "[]" },
				{ "a", "[\"a\"]" }, });

	}

	@Test
	public void testValidInputs() throws Exception {
		this.mockMvc.perform(get("/words/" + this.input)).andExpect(status().isOk())
				.andExpect(content().string(containsString(this.expectedResult)));
	}

	@Test()
	public void testIncorrectInput() throws Exception {
		this.mockMvc.perform(get("/words/hat1")).andExpect(status().isOk())
				.andExpect(content().string(containsString(ScrabbleSolverConstants.ERROR_MESSAGE_INORRECT_INPUT)));
	}

	@Test
	public void testEmptyInput() throws Exception {
		this.mockMvc.perform(get("/words")).andExpect(status().isOk())
				.andExpect(content().string(containsString(ScrabbleSolverConstants.ERROR_MESSAGE_EMPTY_INPUT)));
	}
}
