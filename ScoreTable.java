


import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


/**
 * Calculates scores for strings generated, according to the following score-chart:
 *
 * (1 point)-A, E, I, O, U, L, N, S, T, R
 * (2 points)-D, G
 * (3 points)-B, C, M, P
 * (4 points)-F, H, V, W, Y
 * (5 points)-K
 * (8 points)- J, X
 * (10 points)-Q, Z
 *
 * Representation Invariant : The score table array is always of a fixed size (26) and contains only non-negative values.
 */
public class ScoreTable {
   
   private static final int NUMBER_OF_ALPHABETS = 26;

   // The following constants represents scores for each alphabet, based on its frequency. 
   private static final int ONE_POINT = 1;          // A, E, I, O, U, L, N, S, T, R
   private static final int TWO_POINTS = 2;        // D, G
   private static final int THREE_POINTS = 3;        // B, C, M, P
   private static final int FOUR_POINTS = 4;      // F, H, V, W, Y
   private static final int FIVE_POINTS = 5;      // K
   private static final int EIGHT_POINTS = 8;     // J, X
   private static final int TEN_POINTS = 10; // Q, Z  
   
   private int[] scoreBoard = new int[NUMBER_OF_ALPHABETS];

   public ScoreTable() {
       
      char[] arrayOfAlphabets = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    
      for (char c : arrayOfAlphabets) {
         
         if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'l' || c == 'n' || c == 's' || c == 't' || c == 'r') {
               
            scoreBoard[getCharNum(c)] = ONE_POINT;
         }
        
         if (c == 'd' || c == 'g') {
       
            scoreBoard[getCharNum(c)] = TWO_POINTS;
         }
        
         if (c == 'b' || c == 'c' || c == 'm' || c == 'p') {
       
            scoreBoard[getCharNum(c)] = THREE_POINTS;        
         }
        
         if (c == 'f' || c == 'h' || c == 'v' || c == 'w' || c == 'y') {
       
            scoreBoard[getCharNum(c)] = FOUR_POINTS;        
         }
        
         if (c == 'k') {
       
            scoreBoard[getCharNum(c)] = FIVE_POINTS;        
         }
        
         if (c == 'j' || c == 'x') {
       
            scoreBoard[getCharNum(c)] = EIGHT_POINTS;        
         }
        
         if (c == 'q' || c == 'z') {
                   
            scoreBoard[getCharNum(c)] = TEN_POINTS;     
         }
       }
    }

   
   /**
   * Index an array with a char that is a letter by treating it as an int and subtracting 'a' from it. 
   * E.g., If your letter is 'd', ('d' - 'a') = 3 and if it's 'e', ('e' - 'a') = 4.
   @param character c 
   @return c-'a' difference between c and 'a'
   */   
   private int getCharNum(char c){
      return (c-'a');
   }
  
   
   /**
   * This method calculates scores of the words present in an arrayList of strings.
   * @param ArrayList anagramsSet - An arrayList of strings for which scores are to be calculated.
   * @return HashMap scoreMap - hashmap with words and scores as key-value pairs.
   * PRE: Each string in anagramsSet is a valid string (only contains letters/alphabets).
   */
   public Map<String, Integer> getScores(ArrayList<String> anagramsSet){
   
      Map<String, Integer> scoreMap = new HashMap<>();
      int totalScore = 0;
      
      for(String word : anagramsSet){
         
         totalScore = 0;
         
         for(char c : word.toCharArray()){
            
            c = Character.toLowerCase(c);
            
            int charNumber = getCharNum(c);
            
            totalScore += scoreBoard[charNumber];
         }
            scoreMap.put(word, totalScore);
      }
      
      return scoreMap;
   }
}