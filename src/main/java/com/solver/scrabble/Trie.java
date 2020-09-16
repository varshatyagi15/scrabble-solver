package com.solver.scrabble;

/**
 * Class to implement standard Trie data structure, with insert method.
 */
public class Trie {
    /**
     * Property to store the root node of a Trie.
     */
    private TrieNode root;

    /**
     * @return Root node of a Trie.
     */
    protected TrieNode getRoot() {
        return root;
    }

    /**
     * Constructor to instantiate root node of Trie.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * @param word Word which need to be inserted This method inserts input word
     *             into the Trie.
     */
    public void insert(final String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsChild(currentChar)) {
                node.put(currentChar, new TrieNode());
            }
            node = node.get(currentChar);
        }
        node.setWordEnd();
    }
}
