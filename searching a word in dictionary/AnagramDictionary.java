package pa4;
// Name: 
// USC NetID: 
// CS 455 PA4
// Fall 2017

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;



/**
 * A dictionary of all anagram sets. 
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {
  private ArrayList<String> dictionarySet = new ArrayList<>(); 
  private Map<String, TreeSet<String>> dictionaryMap;

   /**
    * Create an anagram dictionary from the list of words given in the file
    * indicated by fileName.  
    * PRE: The strings in the file are unique.
    * @param fileName  the name of the file to read from
    * @param dictionaryMap collect all words from dictionary and rearrange letters of each words by alphabet order
    * @throws FileNotFoundException  if the file is not found
    */
  public AnagramDictionary(String fileName) throws FileNotFoundException {
    File dictionary = new File(fileName);
    Scanner scan = new Scanner(dictionary);
    while(scan.hasNext()){
      dictionarySet.add(scan.next());
    }
    dictionaryMap = dictionarySetArrange();
  }
   

   /**
    * Get all anagrams of the given string. This method is case-sensitive.
    * E.g. "CARE" and "race" would not be recognized as anagrams.
    * @param s string to process
    * @param letters separate string s to char
    * @param countLetters collect every letters and frequencies of string s.
    * @param numbers frequencies of letters in a word.
    * @param subRack get all subStrings of rack.
    * @return anagramSet  a list of the anagrams of s
    * 
    */
  public ArrayList<String> getAnagramsOf(String s) {
    Map<Character,Integer> countLetters = new TreeMap<>();
    String letterRerrange ="";
    Rack rack = new Rack();   
    char[] letters = s.toCharArray();
    for(int i=0;i<letters.length;i++){
      if(countLetters.containsKey(letters[i])){
        countLetters.put(letters[i], countLetters.get(letters[i])+1);
      }
      else{
        countLetters.put(letters[i], 1);
      }
    }
    int[] numbers = new int[countLetters.size()];
    int i=0;
    for(char key : countLetters.keySet()){
      letterRerrange+= key;
      numbers[i++]=countLetters.get(key);
    }
    //get all substrings of rack by use getAllSet in Rack class.
    ArrayList<String> subRack = rack.getAllSet(letterRerrange, numbers, 0);
    ArrayList<String> anagramSet = new ArrayList<String>();
    /*collect all anagram words by compare the substrings of rack and dictionary words,
     *if it can found exactly words from dictionary, the program collect these words. 
     */
    for(i=0;i<subRack.size();i++){
      if(dictionaryMap.containsKey(subRack.get(i))){
        anagramSet.addAll(dictionaryMap.get(subRack.get(i)));
      }
    } 
    return anagramSet; 
  }
  
  /**dictionarySetArrange sort letters of every words in dictionary in alphabet order.
   * and find the same anagram words collect together
   * @param listier a list iterator to scan the words from dictionary.
   * @param dicMap  collect all anagram words of dictionary words in a map,
   *                which keys are anagram words in alphabet order, and
   *                values are words which have the same anagram words in alphabet order
   * @param anaSet  collect the words which have same anagram words
   * @return dicMap
   * */

  private Map<String, TreeSet<String>> dictionarySetArrange(){
    ListIterator<String> listier = dictionarySet.listIterator(); 
    Map<String, TreeSet<String>> dicMap = new TreeMap<String, TreeSet<String>>();
    Set<String> set = new TreeSet<String>();
    while(listier.hasNext()){
      String wordChange ="";
      String word = listier.next();
      char[] wordLetter = word.toCharArray();
      Arrays.sort(wordLetter);
      for(int i=0;i<wordLetter.length;i++){
        wordChange+=wordLetter[i];
      }
      if(dicMap.containsKey(wordChange)){
        dicMap.get(wordChange).add(word); 
      }
      else{
        TreeSet<String> anaSet = new TreeSet<String>();
        anaSet.add(word);
        dicMap.put(wordChange,anaSet);	   
      }		   
    }   
    return dicMap;  
  }
}
