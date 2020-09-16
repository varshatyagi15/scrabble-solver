package com.solver.scrabble;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This Class extends standard Trie and implements a method to return all the
 * combination words of input word present in Trie.
 *
 */
public class ScrabbleTrie extends Trie {

    /**
     * List that would contain all the combination words of the input word.
     */
    private List<String> wordCombinations;

    /**
     * @param node               Current Node
     * @param availableAlphabets Remaining alphabets present to add to word
     * @param word               Word formed until this node, while traversing
     *                           from root node of Trie
     * @implNote This method search all the word combinations present in Trie
     *           and add them to class property - wordCombinations.
     */
    private void searchWordCombination(final TrieNode node,
            HashMap<Character, Integer> availableAlphabets, String word) {
        if (node == null || availableAlphabets.isEmpty()) {
            return;
        }

        for (Character childChar : node.getChildren().keySet()) {
            if (availableAlphabets.containsKey(childChar)) {
                Integer noOfOccurences = availableAlphabets.get(childChar);
                if (noOfOccurences > 1) {
                    availableAlphabets.put(childChar,
                            availableAlphabets.get(childChar) - 1);
                } else {
                    availableAlphabets.remove(childChar);
                }

                TrieNode childNode = node.get(childChar);
                word += childChar;
                if (childNode.isWordEnd()) {
                    wordCombinations.add(word);
                }

                // Call method recursively for the child node and
                // updated availableAlphabets and word
                searchWordCombination(childNode, availableAlphabets, word);

                // Put back the children character in the availableAlphabets
                if (availableAlphabets.containsKey(childChar)) {
                    availableAlphabets.put(childChar,
                            availableAlphabets.get(childChar) + 1);
                } else {
                    availableAlphabets.put(childChar, 1);
                }
                // Remove added character from the word
                word = word.substring(0, word.length() - 1);
            }
        }
    }

    /**
     * @param word Word for which combination are required
     * @return 1. null for invalid input (non-alphabets) 2. All the words
     *         possible in the given dictionary (trie) using letters present in
     *         input word
     */
    public List<String> getWordCombinations(final String word) {
        if (!word.matches("^[a-zA-Z]*$")) {
            return null;
        }

        HashMap<Character, Integer> availableAlphabets = new HashMap<>();

        for (int i = 0; i < word.length(); i++) {
            char ch = word.toLowerCase().charAt(i);
            if (availableAlphabets.containsKey(ch)) {
                availableAlphabets.put(ch, availableAlphabets.get(ch) + 1);
            } else {
                availableAlphabets.put(ch, 1);
            }
        }
        wordCombinations = new ArrayList<>();
        searchWordCombination(this.getRoot(), availableAlphabets, "");

        return wordCombinations;
    }
}
