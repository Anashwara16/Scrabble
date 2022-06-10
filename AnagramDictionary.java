

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {

   private Map<String, ArrayList<String>> dictionaryOfAnagramSets;
   private Set<String> dictionarySet;

   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
      @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException,
                                                    IllegalDictionaryException {
      File file = new File(fileName);
      Scanner scanner = new Scanner(file); 
                                                       
      dictionaryOfAnagramSets = new HashMap<>();                                                  
      dictionarySet = new HashSet<>();
                                                       
      String word; 
      String sortedWord;
                                                       
      while (scanner.hasNext()){
         
         word = scanner.next();
         sortedWord = sortString(word);
         
         if (dictionarySet.contains(word)){

            throw new IllegalDictionaryException("ERROR: Illegal dictionary: dictionary file has a duplicate word: " + word);
         }
         
         dictionarySet.add(word);
                  
         if (!dictionaryOfAnagramSets.containsKey(sortedWord)) {
            dictionaryOfAnagramSets.put(sortedWord, new ArrayList<>(Collections.singletonList(word)));
         }
         
         dictionaryOfAnagramSets.get(sortedWord).add(word);

      }

   }

   
   /**
    * Sorts the given word using the Arrays.sort() method; which alphabetically sorts an array of characters.
    * @param String word - which needs to be sorted. 
    * @return the sorted string. 
    */
   private String sortString(String string) {
      
      char[] arrayOfCharacters = string.toCharArray();
      
      Arrays.sort(arrayOfCharacters);
      
      String sortedString = new String(arrayOfCharacters); 
      
      return sortedString;

   }


   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String string) {
      
      String sortedString = sortString(string); 
      
      return dictionaryOfAnagramSets.get(sortedString);
   }

}
