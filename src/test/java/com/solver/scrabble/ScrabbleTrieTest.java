package com.solver.scrabble;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ScrabbleTrieTest {

	private String inputWord;
	private List<String> expectedWordCombinations;
	private Dictionary dictionary; // required to setup scrabble Trie with data

	@Before
	public void setUp() throws Exception {
		dictionary = new Dictionary(ScrabbleSolverConstants.URL_FOR_LIST_OF_WORDS);
	}

	public ScrabbleTrieTest(String inputWord, List<String> expectedWordCombinations) {
		this.inputWord = inputWord;
		this.expectedWordCombinations = expectedWordCombinations;
	}

	@Parameterized.Parameters(name = "test Cases")
	public static Collection<Object[]> testCases() {
		return Arrays.asList(new Object[][] { 
			{ "hat", Arrays.asList("a", "ah", "at", "ha", "hat", "th") },
			{ "Hat", Arrays.asList("a", "ah", "at", "ha", "hat", "th") },
			{ "HAT", Arrays.asList("a", "ah", "at", "ha", "hat", "th") },
			{ "aht", Arrays.asList("a", "ah", "at", "ha", "hat", "th") }, 
			{ "zzz", Arrays.asList() },
			{ "a", Arrays.asList("a") }, 
			{ "", Arrays.asList() } ,
			{ "InvalidInput1423", null }
		});
	}

	@Test
	public void testGetWordCombinations() {
		ScrabbleTrie scrabbleTrie = dictionary.getScrabbleTrie();
		List<String> wordCombinations = scrabbleTrie.getWordCombinations(this.inputWord);
		Assert.assertEquals(this.expectedWordCombinations, wordCombinations);
	}

}
