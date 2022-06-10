


import java.io.FileNotFoundException;
import java.util.*;


/**
 * A console-based program that finds all possible words that can be made from a rack of Scrabble tiles. 
 * This is the main program of the Scrabble game. It creates an anagram dictionary from the dictionary file. 
 * It prompts for a dictionary and a rack word from the user. 
 * If no dictionary file is provided as a command-line argument, then "sowpods.txt" is taken as the default input file & used as the dictionary. 
 * This main program handles the necessary exceptions if the file is not found or if the dictionary is illegal. 
 * It further calls corresponding functions to process the rack word and display the output accordingly.
 * 
 * Run the program as follows: 
 * java WordFinder [dictionaryFile]
 *
 */
public class WordFinder {
    
   public static void main(String[] args){
       
      String dictionaryFile = "sowpods.txt";   // Default dictionary
      
        if (args.length > 0){
           
           dictionaryFile = args[0];
        }
      
      try {
         
         AnagramDictionary dictionary = new AnagramDictionary(dictionaryFile);
         
         Scanner scanner = new Scanner(System.in);
         
         String input;
         
         System.out.println("Type . to quit.");
         
         while (true){
         
            System.out.print("Rack? ");
            
            input = scanner.next();
            
            if (input.equals(".")){
            
               return;
            }
           
            rackOperations(dictionary, input);         
         }
      
      }
      
      catch (FileNotFoundException e){
         
         System.out.println("ERROR: Dictionary file \"" + dictionaryFile + "\" does not exist.");
           
         System.out.println("Exiting program.");
      }
        
      catch (IllegalDictionaryException e){
           
         System.out.println(e.getMessage());
      
         System.out.println("Exiting program.");
      }
   }

   
   /**
   * This method performs a set of operations on the rack: find subsets, anagrams for each subset, sort, and print. 
   * Creates a rack with the input string. 
   * It first finds all anagrams from the dictionary for each subset of the word. 
   * It finally sorts the anagrams by scores and prints them.
   * @param String word - string entered by the user.
   * @param AnagramDictionary dictionary - dictionary created from the dictionary file.
   */
   private static void rackOperations(AnagramDictionary dictionary, String word){
      
      Rack rack = new Rack(word);
      
      ArrayList<String> anagrams = null;
      ArrayList<String> listOfAllAnagrams = new ArrayList<>();
          
      // Find all subsets of a rack & then find their corresponding anagrams. 
      for (String subset : rack.findAllSubsets()){
      
         if (!subset.equals("")) {
         
            // Find anagrams from a subset of a rack. 
            anagrams = dictionary.getAnagramsOf(subset);
            
            if (anagrams != null){
            
               listOfAllAnagrams.addAll(anagrams);             
            }
        }
      }
      
      ArrayList<Map.Entry<String,Integer>> scores = getEachScore(listOfAllAnagrams);
      
      printScores(word, scores);
    }

   
   /**
   * This method gets scores for each anagram and sorts them accordingly.
   * @param ArrayList listOfAllAnagrams - an ArrayList of anagrams generated from the alphabets on the rack.
   * @return ArrayList scoresList - a sorted ArrayList of map entries with anagrams & their corresponding scores as key-value pairs.
   */
   private static ArrayList<Map.Entry<String,Integer>> getEachScore(ArrayList<String> listOfAllAnagrams){
       
      ScoreTable generateScores = new ScoreTable();
      ArrayList<Map.Entry<String,Integer>> scoresList = new ArrayList<>();
   
      Map<String, Integer> scoreMap = generateScores.getScores(listOfAllAnagrams);
      
      for(Map.Entry<String,Integer> entry: scoreMap.entrySet()){
         scoresList.add(entry);
      }
      
      sortScores(scoresList);
      return scoresList;
    }
   
   
   /**
   * This method sorts words in a decreasing order by score. Words with same scores are ordered alphabetically. 
   * Sorting is performed using a class that implements the comparator interface.
   * @param ArrayList scoresList - an ArrayList of map entries with anagrams & their corresponding scores as key-value pairs. 
   * @return ArrayList scoresList - a sorted ArrayList of map entries with anagrams & their corresponding scores as key-value pairs.
   */
   private static ArrayList<Map.Entry<String,Integer>> sortScores(ArrayList<Map.Entry<String,Integer>> scoresList){
       
      Collections.sort(scoresList, new ScoreComparator());
      
      return scoresList;
    }
   
   
   /**
   * This method displays all valid words, with their corresponding Scrabble scores for each word; in a decreasing order.
   * For words with the same Scrabble score, they will be alphabetically ordered. 
   * @param ArrayList scoresList - an ArrayList of map entries with anagrams & their corresponding scores as key-value pairs.
   * @param String word - alphabets in the rack. 
   */
   private static void printScores(String word, ArrayList<Map.Entry<String,Integer>> scoresList){

      System.out.print("We can make " + scoresList.size() + " words from \""+ word +"\"");
      System.out.println();
      
      // If a valid word is present, we print the word and its score. Print in order of occurrence. 
      if (scoresList.size() > 0){                  
         
         System.out.println("All of the words with their scores (sorted by score):");
           
         for(Map.Entry<String, Integer> current: scoresList){
           
            System.out.println(current.getValue() + ": " + current.getKey());
         }
           
      }
   }
}