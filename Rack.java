
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
   A Rack of Scrabble tiles
 */


public class Rack {

   private String wordInRack;

   /**
    * Creates a rack with the given word. 
    * @param string word.
    */
   public Rack(String word){
      wordInRack = word;
   }

   
   /**
    * Creates all the subsets of the word in the rack. It first creates a unique word (with no duplicates)
    * and a corresponding multiplicity array to be passed as arguments to the recursive function allSubsets.
    * @return ArrayList of strings that are subsets of the rack word.
    */
   public ArrayList<String> findAllSubsets(){
   
      Map<Character, Integer> characterCounter = new HashMap<>(); // Hashmap to store character and its counts
      
      for (int i = 0; i < wordInRack.length(); i++){
         
         Character letter = wordInRack.charAt(i);
      
         if (!characterCounter.containsKey(letter)){
         
            characterCounter.put(letter, 1);
         }
         
         else {
            
            Integer letterCount = characterCounter.get(letter);
            
            characterCounter.put(letter, letterCount + 1);
         }
      }
     
      StringBuilder unique = new StringBuilder();               
      int[] multi = new int[characterCounter.size()];            
      int entry = 0;
      
      for (Map.Entry<Character, Integer> current : characterCounter.entrySet()) {
      
         unique.append(current.getKey());
         
         multi[entry++] = current.getValue();
                  
      }
      
      ArrayList<String> totalSubsets = allSubsets(unique.toString(), multi, 0);

      return totalSubsets;

   }

   
   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
      each subset is represented as a String that can have repeated characters in it.
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         
         allCombos.add("");
         
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      
      for (int n = 0; n <= mult[k]; n++) {   
      
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }
   
}
