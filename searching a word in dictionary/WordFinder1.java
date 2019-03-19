package pa4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WordFinder1 {
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
		      while(!rack.equals(".")){
		    	  ArrayList<String> outPut = new ArrayList<>();
		    	  System.out.print("Rack? ");
		    	  rack = sc.next();
		    	  if(!rack.equals(".")){
		    		  rack=rearrangeOrder(rack);
		    		  outPut=gameBeginning.getAnagramsOf(rack);
		    		  System.out.println("We can make "+outPut.size()+" words from "+rack);
		    		  
		    		  System.out.println("All of the words with their scores (sorted by score):");
		    		  List<Map.Entry<String,Integer>> scoreTableM = new LinkedList<>();
		    		  ScoreTable scoreTAble = new ScoreTable();
		    		  scoreTableM = scoreTAble.getMapSort(outPut);
		    		  for(int i=0;i<scoreTableM.size();i++){
		    			  System.out.println( scoreTableM.get(i).getValue()+": "+scoreTableM.get(i).getKey());
		    		  }
		    		  Scanner scc = new Scanner(System.in);
		    		  String breakTheWhile ="";
		    		  while(!breakTheWhile.equals("quit")){
		    			  System.out.println("words fragments you want to find");
		    			  breakTheWhile =scc.nextLine();
		    			  findWords(scoreTableM,breakTheWhile);
		    		  }
		    		  
		    	  }  
		      }
		    }
		    catch (FileNotFoundException exc) {
		        System.out.println("ERROR: File not found: " + fileName);
		      }
	}
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
	private static void findWords(List<Map.Entry<String,Integer>> scoreTableM,String str){
		
		for(int i=0;i<scoreTableM.size();i++){
			char[] charT = str.toCharArray();
			String pattern="(.*)";
			for(int j=0;j<charT.length;j++){
				if(charT[j]==' '){
					pattern +="(.*)";
				}
				else{
					pattern+=charT[j];
				}
			}
			pattern=pattern+"(.*)";
				if(scoreTableM.get(i).getKey().matches(pattern)){
					System.out.println( scoreTableM.get(i).getValue()+": "+scoreTableM.get(i).getKey());
				}
			
		}
		
	}

}
