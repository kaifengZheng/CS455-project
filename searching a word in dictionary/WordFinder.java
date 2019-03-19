package pa4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**This program help Scrabble player to find words can be organized
   from specific Rack. 
   FOR EXAMPLE:if you put "cmal" from Keyboard,you will get:
               Rack?  cmal
               We can make 11 words from "aclm"
               All of the words with their scores (sorted by score):
               8: calm
               8: clam
               7: cam
               7: mac
               5: lac
               5: lam
               5: mal
               4: am
               4: ma
               2: al
               2: la
   To do this, firstly, we need to load a dictionary, and then find the 
   words which equals racks canonical forms.
   This program include 4 class:
   AnagramDictionary:Find all anagram words from dictionary which have the same anagram words of rack
   Rack: get all substrings of a rack in alphabet order.
   ScoreTable: get words and evaluate them by score
   WordFinder: it contains main class and I/O operator.
   @param  fileName: the name of a dictionary file, it locate in the same
                     location of this program files.
   @param  gameBeginning: preprocessing the dictionary.
   @param  rack         : rack player need to type in.
   @param  outPut       : anagram words get from dictionary.
   @param  scoreTableM  : a map record scores and words related.
   PRE: file is exist.
        rack should not contain other symbols except english letter and whitespace.
        if rack has whitespace inside, because next method of scanner, the program 
        will run some times.  
   @author Kaifeng Zheng
*/
public class WordFinder {
  public static void main(String[] args){
    String fileName = "";
    try {
      if (args.length < 1) {
        fileName = "sowpods.txt"; 
      }
      else {
        fileName = args[0]; 
      }
      AnagramDictionary gameBeginning = new AnagramDictionary(fileName);
      String rack = "";      
      System.out.println("Type . to quit");
      Scanner sc = new Scanner(System.in);
      //if type ".", the program will terminate.
      while(!rack.equals(".")){
        ArrayList<String> outPut = new ArrayList<>();
        System.out.print("Rack? ");
        rack = sc.next();
        if(!rack.equals(".")){
          rack=rearrangeOrder(rack);
          outPut=gameBeginning.getAnagramsOf(rack);
          System.out.println("We can make "+outPut.size()+" words from \""+rack+"\"");  
          if(outPut.size()!=0){
            System.out.println("All of the words with their scores (sorted by score):");
          }
          List<Map.Entry<String,Integer>> scoreTableM = new LinkedList<>();
          ScoreTable scoreTAble = new ScoreTable();
          scoreTableM = scoreTAble.getMapSort(outPut);
          for(int i=0;i<scoreTableM.size();i++){
            System.out.print( scoreTableM.get(i).getValue()+": "+scoreTableM.get(i).getKey());
            /*if a player types a rack which has more than 7 letters, it is possible find a 
             * word has 7 more letters. When the program find a words has 7 letters will report
             * this. */
            if(scoreTableM.get(i).getKey().length()>7){
              System.out.print("\t"+" REPORT: the word from dictionary have more than 7 letters! ");
            }
            System.out.println();
          }
        }  
      }
    }
    //if no file found will throw this error message.
    catch (FileNotFoundException exc) {
      System.out.println("ERROR: File not found: " + fileName);
    }
  }
/** This method rearrange rack letters by ascending order of alphabet.
    @param str: rearrange word
    @param strToChar: the rack word be separated into char and store into char array.
    PRE: String can be only English letters, if not, the method will delete it.
    
*/
  private static String rearrangeOrder(String s){
    String str ="";
    s = s.replaceAll("[\\W]","");
    char[] strToChar = s.toCharArray();
    Arrays.sort(strToChar);
    for(int i=0;i<strToChar.length;i++){
    str+= strToChar[i];
    }
    return str;
  }
}
