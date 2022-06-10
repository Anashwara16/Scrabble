


import java.util.Comparator;
import java.util.Map;


/**
 * A class that implements Comparator interface of Map entries to sort an ArrayList of map entries. 
 * This is utilized by Collections.sort() method. 
 * Returns value greater than 0 if item 2's value is greater than item 1's value. 
 * If both items have the same score, then they are compared based on their alphabets. 
 *
 */
public class ScoreComparator implements Comparator<Map.Entry<String, Integer>> {
   
   public int compare(Map.Entry<String, Integer> item1, Map.Entry<String, Integer> item2) {
      
      if ((item2.getValue() - item1.getValue()) != 0){
         
         return item2.getValue() - item1.getValue();
      }
      
      return item1.getKey().compareTo(item2.getKey());
    
   }
}
