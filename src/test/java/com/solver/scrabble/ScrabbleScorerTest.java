package com.solver.scrabble;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ScrabbleScorerTest {
	private String inputWord;
	private int expectedScore;

	public ScrabbleScorerTest(String inputWord, int expectedScore) {
		this.inputWord = inputWord;
		this.expectedScore = expectedScore;
	}

	@Parameterized.Parameters(name = "test Cases")
	public static Collection<Object[]> testCases() {
		return Arrays.asList(new Object[][] { 
			{ "hat", 6 }, 
			{ "Hat", 6 }, 
			{ "HAT", 6 }, 
			{ "aht", 6 }, 
			{ "code", 7 },
			{ "antidisestablishmenatarianism", 39 }, 
			{ "", 0 }, 
			{ "abcdefghijklmnopqrstuvwxyz", 87 } });

	}

	@Test
	public void testGetWordScore() {
		int actualScore = ScrabbleScorer.getWordScore(this.inputWord);
		Assert.assertEquals(this.expectedScore, actualScore);

	}

}
