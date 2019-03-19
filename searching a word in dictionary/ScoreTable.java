package pa4;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
/**ScoreTable class give words scores by a specified principle.
 * @param int[] score letter scores
 * @param alpha       the first letter of English alphabet.
 * PRE: the anagram words list is not null.
 * */
public class ScoreTable {
  private static final int[] score={1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
  private static final char alpha = 'a';
  /**score method calculates scores of words
   * @param str the anagram word from list
   * @param scoreTotal calculate total score of a word
   * @param scoreTableMap the program store scores and words in the map
   *                      which keys are words and values are scores
   * @return scoreTableMap
   * */
  private Map<String,Integer> score(ArrayList<String> output){
    Map<String,Integer> scoreTableMap = new TreeMap<String,Integer>();
    for(int i=0;i<output.size();i++){
      String str = output.get(i).toLowerCase();
      int scoreTotal = 0;
      for(int j=0;j<str.length();j++){
        scoreTotal+=score[str.charAt(j)-alpha];
       /*************************************************************************
        *  instead method:
        * char c = str.charAt(j);
        switch(c){
          case 'a': case 'e': case 'i': case 'o':
          case 'u': case 'l': case 'n': case 's':
          case 't': case 'r':                         score=score+1; break;
          case 'd': case 'g':                         score=score+2; break;
          case 'b': case 'c': case 'm': case 'p':     score=score+3; break;
          case 'f': case 'h': case 'v': case 'w':
          case 'y':                                   score=score+4; break;
          case 'k':                                   score=score+5; break;
          case 'j': case 'x':                         score=score+8; break;
          case 'q': case 'z':                         score=score+10;break;
          default:                                    break;
        }************************************************************************/
      }
      scoreTableMap.put(output.get(i), scoreTotal);
    }
    return scoreTableMap;	
  }
  /**scoreTableMap sort scoretable by values in the decreasing order 
   * @param list collect the scoretable and sort by values in the decreasing order
   * @return list
   * */
  private List<Map.Entry<String,Integer>> scoreTableMapSort(Map<String,Integer> scoreTableMap){
    List<Map.Entry<String,Integer>> list = new LinkedList<Entry<String, Integer>>(scoreTableMap.entrySet());
    Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){
      public int compare(Map.Entry<String,Integer> o1,Map.Entry<String,Integer> o2){
        return o2.getValue().compareTo(o1.getValue());
      }
    });
    return list;
  }
  /**getMapSort method support a interface. outside class can get sorted score table
   * from here.
   * @return scoreTableMapSort
   * */
  
  public List<Map.Entry<String,Integer>> getMapSort(ArrayList<String> output){
    Map<String,Integer> scoreTableMap = score(output);
    return 	scoreTableMapSort(scoreTableMap);	
  }
}
