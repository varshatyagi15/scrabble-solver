package com.solver.scrabble;

/**
 * This class provides method for calculating scrabble
 * score for input word.
 *
 */
public final class ScrabbleScorer {

    /**
     * Private constructor for the utility class, which only
     * exposed static method(s).
     */
    private ScrabbleScorer() {
    }

    /**
     * Scrabble score for each letter (A, B, C ,...., Z).
     */
    private static final int[] LETTER_SCORES = {1, 3, 3, 2, 1, // a = 1, b = 3,
                                                                // c = 3, d = 2,
                                                                // e = 1,
            4, 2, 4, 1, 8, // f = 4, g = 2, h = 4, i = 1, j = 8,
            5, 1, 3, 1, 1, // k = 5, l = 1, m = 3, n = 1, o = 1,
            3, 10, 1, 1, 1, // p = 3, q = 10, r = 1, s = 1, t = 1,
            1, 4, 4, 8, 4, 10 // u = 1, v = 4, w = 4, x = 8, y = 4, z = 10
    };

    /**
     * @param word Word for which score need to be calculated.
     * @return scrabble score of the word by summing up the each letter score.
     */
    public static Integer getWordScore(final String word) {
        int score = 0;
        for (char letter : word.toLowerCase().toCharArray()) {
            score += getLetterScore(letter);
        }
        return score;
    }

    /**
     * @param letter Letter for which score need to be calculated.
     * @return Score for input letter.
     */
    private static int getLetterScore(final char letter) {
        return LETTER_SCORES[letter - 'a'];
    }
}
