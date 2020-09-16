package com.solver.scrabble;

public final class ScrabbleSolverConstants {

    /**
     * Private constructor for utility class.
     */
    private ScrabbleSolverConstants() {
    }

    /**
     * Error message for incorrect input to get API (word).
     */
    public static final String ERROR_MESSAGE_INORRECT_INPUT =
            "INCORRECT INPUT!! Only alphabets allowed";

    /**
     * Error message for empty input to get API (word).
     */
    public static final String ERROR_MESSAGE_EMPTY_INPUT =
            "INCORRECT INPUT!! Please pass input in format:-"
            + " http://localhost:8080/words/words/{letters}";

    /**
     * URL from list of words to be fetched.
     */
    public static final String URL_FOR_LIST_OF_WORDS =
            "https://tinyurl.com/y4ejk339";
}
