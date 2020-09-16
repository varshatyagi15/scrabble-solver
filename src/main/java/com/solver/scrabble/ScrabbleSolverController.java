package com.solver.scrabble;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class which exposes GET API to get Scrabble Words for input
 * letters.
 *
 */
@RestController
public class ScrabbleSolverController {

    /**
     * @param letters Input word for which srabble suggestions are need.
     * @return Sorted scrabble suggestions for input word.
     */
    @RequestMapping(value = "/words/{letters}", produces = "application/json")
    public List<String> getWords(
            @PathVariable("letters") final String letters) {
        if (!letters.matches("^[a-zA-Z]*$")) {
            // Exception can also be thrown in case of incorrect input
            return Arrays.asList(
                    ScrabbleSolverConstants.ERROR_MESSAGE_INORRECT_INPUT);
        }
        return ScrabbleSolverMain.getDictionary()
                .getSortedScrabbleWords(letters.toLowerCase());
    }


     * @return Error message if GET API doesn't pass any input word.
     */
    @RequestMapping(value = "/words", produces = "application/json")
    public String getEmptyState() {
        return ScrabbleSolverConstants.ERROR_MESSAGE_EMPTY_INPUT;
    }

}
