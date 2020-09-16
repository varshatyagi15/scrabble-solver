package com.solver.scrabble;

import java.util.HashMap;

/**
 * Class for Building block of standard Trie data structure.
 *
 */
public final class TrieNode {
    /**
     * Stores children nodes in a HashMap.
     */
    private HashMap<Character, TrieNode> children;

    /**
     * isEndOfWord is true if the node. represents end of a word.
     */
    private boolean isEndOfWord;

    /**
     * Constructor for TrieNode, it also initializes children hashmap.
     */
    public TrieNode() {
        children = new HashMap<Character, TrieNode>();
    }

    /**
     * @param ch - character to be found
     * @return whether TrieNode has a child for input character.
     */
    public boolean containsChild(final char ch) {
        return children.containsKey(ch);
    }

    /**
     * @param ch the character for which child TrieNode need to be returned
     * @return child TrieNode of current TrieNode for input character.
     */
    public TrieNode get(final char ch) {
        return children.get(ch);
    }

    /**
     * @param ch Character
     * @param node TrieNode to be added for the given character
     * This method adds child (TrieNode) to a current TrieNode
     * for input character.
     */
    public void put(final char ch, final TrieNode node) {
        children.put(ch, node);
    }

    /**
     * Sets isEndOfWord = true for current TrieNode,
     * i.e. current TrieNode is end of a Word.
     */
    public void setWordEnd() {
        isEndOfWord = true;
    }

    /**
     * @return true if current TrieNode is end of a Word.
     */
    public boolean isWordEnd() {
        return isEndOfWord;
    }

    /**
     * @return Children of Current TrieNode.
     */
    public HashMap<Character, TrieNode> getChildren() {
        return children;
    }
}
