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
public class DictionaryTest {
	
	private String inputWord;
	private List<String> expectedSortedScrabbleWords;
	private Dictionary dictionary; // required to setup scrabble Trie with data

	@Before
	public void setUp() throws Exception {
		dictionary = new Dictionary(ScrabbleSolverConstants.URL_FOR_LIST_OF_WORDS);
	}
	
	public DictionaryTest(String inputWord, List<String> expectedSortedScrabbleWords) {
		this.inputWord = inputWord;
		this.expectedSortedScrabbleWords = expectedSortedScrabbleWords;
	}
	
	@Parameterized.Parameters(name = "test Cases")
	public static Collection<Object[]> testCases() {
		return Arrays.asList(new Object[][] { 
			{ "hat", Arrays.asList("hat", "ah", "ha", "th", "at", "a") },
			{ "Hat", Arrays.asList("hat", "ah", "ha", "th", "at", "a") },
			{ "HAT", Arrays.asList("hat", "ah", "ha", "th", "at", "a") },
			{ "aht", Arrays.asList("hat", "ah", "ha", "th", "at", "a") }, 
			{ "zzz", Arrays.asList() },
			{ "a", Arrays.asList("a") }, 
			{ "", Arrays.asList() } ,
			{ "InvalidInput1423", null }
		});
	}

	@Test
	public void testGetWordCombinationst() {

		List<String> sortedScrabbleWords = dictionary.getSortedScrabbleWords(this.inputWord);
		Assert.assertEquals(this.expectedSortedScrabbleWords, sortedScrabbleWords);
	}

}
