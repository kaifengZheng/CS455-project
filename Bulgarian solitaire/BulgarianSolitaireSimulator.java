package pa2;

import java.util.ArrayList;
import java.util.Scanner;

//Name:Kaifeng Zheng
//CSCI455 PA2
//Fall 2017



public class BulgarianSolitaireSimulator {
	
	
  public static void main(String[] args) {

 /********************************************************************************** 
  * This program will let user to play Bulgarian Solitaire game. There are four ways
  * to run this program.
  * 1. if running by -u, user need to input numbers by hand, when user 
  *    click enter, the result will come out.
  * 2. if running by -s, user will get random initial configuration and then
  *    continued by steps when click enter.
  * 3. if running by -u -s, user need to input numbers by hand, and then need
  *    click enter to continue program by step.
  * 4. if running by neither -u nor -s, program will get random initial configuration,
  *    then the result will come out automatically.
  ***********************************************************************************/
    boolean singleStep = false;
    boolean userConfig = false;
    ArrayList<Integer> getpiles = new ArrayList<Integer>();
    String currentPiles="";
   
 
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-u")) {
        userConfig = true;     
      }
      else if (args[i].equals("-s")) {
        singleStep = true;
      }
    }
   
   
    if(userConfig == true&&singleStep == true){
      getpiles = playGameInatialByHand(getpiles);
      playGameContinueByEnter(new SolitaireBoard(getpiles),currentPiles);
      getpiles.clear();
    }
    if(userConfig == true&&singleStep == false){
      getpiles = playGameInatialByHand(getpiles);
	  playGameAutomatically(new SolitaireBoard(getpiles),currentPiles); 
	  getpiles.clear();
    }
    if(userConfig == false&&singleStep == true){
      playGameContinueByEnter(new SolitaireBoard(),currentPiles);
    }
    if(userConfig == false&&singleStep == false){
      playGameAutomatically(new SolitaireBoard(),currentPiles);
    }
  }


  private static ArrayList<Integer> playGameInatialByHand(ArrayList<Integer> nums){
	
  /**	
     This method make sure valid numbers reading from keyboard. iff not valid,
     that is, arraylist num=null, it will give an error prompt and required new inputs
     @param nums An empty arraylist.
     @return  get the valid numbers from input method for the initial configuration
     
     PRE: nums is an empty arraylist. 
   */
	
    System.out.println("Number of total cards is "+SolitaireBoard.CARD_TOTAL);
    System.out.println("You will be entering the initial configuration of the"
	   		            + "cards (i.e., how many in each pile).");
    for(;;){
      System.out.println("Please enter a space-separated list of positive "
			           + "integers followed by newline:");
      nums = inputFromKeyboard(nums);
      if(nums==null){
        System.out.println("ERROR: Each pile must have at least one card "
			   		            + "and the total number of cards must be "
        		                + SolitaireBoard.CARD_TOTAL);
      }
      else{
        break;
      }
    }
    return nums;
  }


  private static void playGameContinueByEnter(SolitaireBoard newSolitaire,String currentPiles){

  /** 
     This method can let user continue running this program by step. 
      @param currentpiles 
             record each number series(string type) by step
      @param count 
             count row numbers and print on screen, so we can print in a pattern [count++]
             eg: [1] [2] [3]....
   */
    currentPiles = newSolitaire.configString();
    System.out.print("Initial configuration: "+currentPiles);
    int count = 1;
    Scanner in = new Scanner(System.in);
    
	  
    while(!newSolitaire.isDone()){
      in.nextLine();
      newSolitaire.playRound();
      currentPiles = newSolitaire.configString();
      System.out.print("["+ count++ +"] "+"Current configuration: "+currentPiles+'\n');
      System.out.print("<Type return to continue>");
    } 
    
    System.out.println();
    System.out.println("Done!");
  } 
  private static void playGameAutomatically(SolitaireBoard newSolitaire,String currentPiles){

  /**
    This method can be used to play the game automatically
    PRE: currentPiles is a empty string.
         
   */
    currentPiles = newSolitaire.configString();
    int count=1;
    System.out.print("Initial configuration: "+currentPiles+'\n');
    while(!newSolitaire.isDone()){
      newSolitaire.playRound();
      currentPiles = newSolitaire.configString();
      System.out.print("["+count++ +"] "+"Current configuration: "+currentPiles+'\n');
    }
  }
  private static ArrayList<Integer> inputFromKeyboard(ArrayList<Integer> nums){
  /**
     This method get valid numbers from keyboard, then transform string to integers
     and save in arraylist nums.
     @param strNum         record single string number from string(numbers read from keyboard
                           Separated by Space).
     @param string         store the string numbers
     @param stringToNumber change the string number to integer number
     @param sum            add every numbers in the string and judge whether it is
                           equals CARD_TOTAL.
     @return if inputs are not valid, then return a null array; if inputs are valid
             then store numbers in nums and return it.
     conditions:     The strings in strNum are only numbers.
                     sum=CARD_TOTAL
                     stringToNumber > 0
     PRE:            nums is empty arraylist.
                      
   */
    String strNum ="";
    nums = new ArrayList<Integer>();
    int stringToNumber;
    int sum=0;
    Scanner in = new Scanner(System.in);
    for(int i=0;i<SolitaireBoard.CARD_TOTAL;i++){
      String string = in.nextLine();
      Scanner numRecog = new Scanner(string);//scan the string
      while(numRecog.hasNext()){
        strNum = numRecog.next();
        //if strNum is not number return null.
        //'+'represent one or more numbers from 0 to 9 following.
        if(!strNum.matches("[0-9]+")){
          return null;
        }
        else{
          stringToNumber = Integer.parseInt(strNum);
          sum = sum+stringToNumber;
          if(stringToNumber<=0){
            return null;
          }
          else{
            nums.add(stringToNumber);
          }
        }
      }
      if(sum!=SolitaireBoard.CARD_TOTAL){
        return null;
      }
      break;
    }
    return nums;
  }
}