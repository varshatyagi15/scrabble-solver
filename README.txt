# Scrabble Solver Service
Implements an HTTP REST service that returns Scrabble words for a given set of letters. 
And the dictionary(list of words) is fetched from - http://recruiting.bluenile.com/words.txt

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 
See deployment for notes on how to deploy the project on a live system.

## Built With
Below are the tools and IDE used:

* JDK 1.8 
* [Maven](3.6.3) - Dependency Management
* Spring Boot - Java based framework
* Spring Tool Suite 4(STS)

## Deployment and Testing
Go to the project folder and run below commands:

*To build:
  mvn clean package

*To run tests and coverage report:
  mvn clean verify

*To run checkstyle report:
  mvn checkstyle:checkstyle

*To run:
  mvn spring-boot:run
  
*URL format for using the GET API:
    http://localhost:8080/words/<letters>
	
	Example usage:
	```
    > curl http://localhost:8080/words/hat
    ["hat","ah","ha","th","at","a"]

    > curl http://localhost:8080/words/zzz
    []
	```
## Complexity Analysis
Scrabble solver uses Trie data-strucure to store dictionary (list of words). 
It creates the Trie just after the application starts (hence one time cost of setup)
And then this trie can be used as many times while making call to GET API for different input letters.
Both space and time complexity analysis is divided into two main operations:
 * setup(): Method used to fetch all words from data source (URL) and store them as Trie data-strucure.
 * getWords(): Method (GET API) used to fetch sorted scrabble suggestions for input word. 

### Space
* setup: Since, we are storing all the words in Trie data structure. 
      During setup, space complexity of a word is O(mt) => O(m), where m is length of word
      and "t"  is the space taken by each TrieNode which is constant.
      In the worst case, if newly inserted word doesn't share a prefix with the words already present in the trie, 
      we have to add m new nodes, which will take O(mt) space.
      That implies for n words in list (data source), the worst case space complexity will be O(n*m*t) => O(n*m) [As t is constant]
      But in practice since there are large number of words in dictionary, there will be common prefixes and 
      trie can have max of NUMBER_OF_ALPHABETS_SIZE(=26) children.
      That implies, Average case space complexity => O(NUMBER_OF_ALPHABETS_SIZE * m) nodes => O(26 * m)  => O(m) 
        [m can also be seen as depth of trie]
* getWords(): 
      	  * In searchWordCombination in ScribbleTrie class
 		* "availableAlphabets" is a HashMap used to store remianing alphabets available to add to word. It stores all the characters as key and their
	  	  number of occurence in input word as value. That implies, if no character is duplicate, space taken will be O(m), where m is length of input word.
	     	  But since there can be maximum of 26 characters (keys) the space complexity becomes constant => O(26) => O(1).
	  	* We do store input word during recursion  which will have space complexity of O(m), 
		  If we ignore the space (stack) used for supporting recursion, the space complexity here will be O(m).
	  * "wordCombinations" used in "getSortedScrabbleWords" will be storing all valid combinations of input word's letters. 
             In worst case if all the permuations of word exist in the dictionary then number of words formed,  L = xP1 + xP2 .... + X!/ m1! * m2! ... mk! 
             (Where one element repeated m1 times, .. kth element repeated mk times)
             ==> O(L*average length of words formed)
		 
	That implies final space complexity will be O(L*average_length_of_words_formed + m). 
	Note: this will be capped by size by dictionary = O(n*m), where n is number of words in dictionary
     
### Time
* setup:  Scanning all the words in dictionary and storing them in Trie => O(n*m), Where n is number of words in dictionary 
and m is average length of words
* getWords: During search for all the combinations for a word (length = m)
        Since, we check for all the children until depth (=m), time complexity will be:-  
          O(NUMBER_OF_ALPHABETS_SIZE * m) 
          => O(26 * m) 
		  => O(m)

Note: If we would have done setup while GET request the time complexity of getWords will be higher ==> O(n*m + m)