package com.solver.scrabble;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Class stores list of words in Trie data structure and exposed method for
 * getting sorting scrabble suggestions.
 *
 */
public class Dictionary {

    /**
     * Object of Scrabble Trie (Custom trie extended from standard Trie data
     * structure.
     */
    private ScrabbleTrie scrabbleTrie;

    /**
     * @param urlForListOfWords URL to text file, which contains list of words.
     * @implNote It sets up dictionary, by reading words from input URL and
     *           storing them in Trie data-structure.
     */
    public Dictionary(final String urlForListOfWords) {
        scrabbleTrie = new ScrabbleTrie();
        setup(urlForListOfWords);
    }

    /**
     * @return Getter method for scrabble Trie.
     */
    public ScrabbleTrie getScrabbleTrie() {
        return scrabbleTrie;
    }

    /**
     * @param word Word for which scrabble words are needed
     * @return 1. null for invalid input (non-alphabets) 2. All the words
     *         possible in the given dictionary (trie) using letters present in
     *         input word sorted in descending order by their scrabble score
     */
    public List<String> getSortedScrabbleWords(final String word) {
        if (!word.matches("^[a-zA-Z]*$")) {
            return null;
        }

        List<String> wordCombinations = scrabbleTrie.getWordCombinations(word);
        wordCombinations.sort((String word1, String word2) -> ScrabbleScorer
                .getWordScore(word2)
                .compareTo(ScrabbleScorer.getWordScore(word1)));
        return wordCombinations;
    }

    /**
     * @param urlForListOfWords URL to text file, which contains list of words.
     * @implNote Fetches all the words from the URL and then add these words to
     *           the Scrabble Trie. Note: It converts all words to lower case
     *           and also removes apostrophe(')/spaces from the word before
     *           storing them in Trie.
     */
    private void setup(final String urlForListOfWords) {
        List<String> dictionaryWords = getListOfWords(urlForListOfWords);
        for (String word : dictionaryWords) {
            String lowerCaseOnlyAlphabetsWord = word.replaceAll("\\s+", "")
                    .replaceAll("'", "").toLowerCase();
            scrabbleTrie.insert(lowerCaseOnlyAlphabetsWord);
        }
    }

    /**
     * @param urlForListOfWords URL to text file, which contains list of words.
     * @return List of words present in the file for the input URL.
     */
    private static List<String> getListOfWords(final String urlForListOfWords) {
        try {
            List<String> list = new ArrayList<>();
            URL url = new URL(urlForListOfWords);
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                list.add(str);
            }
            scanner.close();
            return list;
        } catch (IOException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }
}
