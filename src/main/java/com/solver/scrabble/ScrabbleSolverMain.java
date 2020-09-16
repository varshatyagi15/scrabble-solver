package com.solver.scrabble;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

/**
 * This application starts a web service that returns Scrabble suggestions for a
 * given set of letters. The highest-scoring words are listed first. An HTTP GET
 * request to http://local.bluenile.com:8080/words/{letter} is returned.
 */
@SpringBootApplication
public class ScrabbleSolverMain {

    /**
     * Dictionary object which is instantiated when application is started. This
     * object is instantiated once during startup and can be used as many times
     * for Scrabble suggestions for different input words to compute.
     */
    private static Dictionary dictionary;

    /**
     * @return Dictionary with all the words stored in Trie format.
     */
    public static Dictionary getDictionary() {
        return dictionary;
    }

    /**
     * @param args Arguments to main methods
     */
    public static void main(String[] args) {
        SpringApplication.run(ScrabbleSolverMain.class, args);
    }

    /**
     * @param event context refreshed event
     * @implNote This method instantiates dictionary object which can be used by
     *           multiple get request for Scrabble suggestions
     */
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        dictionary = new Dictionary(
                ScrabbleSolverConstants.URL_FOR_LIST_OF_WORDS);
    }
}
