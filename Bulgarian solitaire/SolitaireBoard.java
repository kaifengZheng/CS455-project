package pa2;

//Name:Kaifeng Zheng
//USC NetID:kaifengz
//CSCI455 PA2
//Fall 2017


import java.util.ArrayList;
import java.util.Random;

/*
class SolitaireBoard
The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
for CARD_TOTAL that result in a game that terminates.
(See comments below next to named constant declarations for more details on this.)
*/

public class SolitaireBoard {

  public static final int NUM_FINAL_PILES = 9;
  // number of piles in a final configuration
  // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)
  public static final int IN_CASE_OVERFLOW=1;
  //IN_CASE_OVER_FILLED keeps the array partial filled in case of overflow.
  public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
  public static final int FIXED_ARRAY_SIZE=CARD_TOTAL+IN_CASE_OVERFLOW;
  // FIXED_ARRAY_SIZE is fixed array length by IN_CASE_OVERFLOW 
  // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
  // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
  // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES
  // Note to students: you may not use an ArrayList -- see assgt description for details.


  /**
   Representation invariant:
   the numbers in getpiles need >0 and the sum of those numbers equals CARD_TOTAL.
   pileNumber equals how many numbers store in getpiles. it need >0 and finially it 
   will equals NUM_FINAL_PILES.

   */
  private int[] getPiles;
  private int pileNumber;


  /**
  Creates a solitaire board with the configuration specified in piles.
  piles has the number of cards in the first pile, then the number of cards in the second pile, etc.
  PRE: piles contains a sequence of positive numbers that sum to SolitaireBoard.CARD_TOTAL
  */
  public SolitaireBoard(ArrayList<Integer> piles) {
	  
    getPiles = new int[FIXED_ARRAY_SIZE];
    pileNumber = piles.size();
    for(int i=0;i<piles.size();i++){
      getPiles[i] = piles.get(i);
    }
    assert isValidSolitaireBoard(); 
  }


  /**
    Creates a solitaire board with a random initial configuration.
  */
  public SolitaireBoard() {
    getPiles = new int[FIXED_ARRAY_SIZE];
    pileNumber = 0;
    Random pile = new Random();
    int pilenum=CARD_TOTAL;
    for(int i=0;pilenum>0;i++){ 
      getPiles[i]= pile.nextInt(pilenum)+1;
      pilenum = pilenum - getPiles[i];
      pileNumber++;
    }
    assert isValidSolitaireBoard(); 
  }


  /**
   Plays one round of Bulgarian solitaire.  Updates the configuration according to the rules
   of Bulgarian solitaire: Takes one card from each pile, and puts them all together in a new pile.
   The old piles that are left will be in the same relative order as before, 
   and the new pile will be at the end.
  */
  public void playRound() {
    for(int i=0;i<pileNumber;i++){
      if(getPiles[i]!=0){
        getPiles[i]=getPiles[i]-1;
      }
    }
    getPiles[pileNumber]=pileNumber;
    pileNumber++;
		
    for(int i=0;i<pileNumber;i++){
      if(getPiles[i]==0){
        for(int j=i;j<pileNumber-1;j++){
          getPiles[j]=getPiles[j+1];
          getPiles[j+1]=0;
        }
        pileNumber--;
        i--;
      }
    }
    assert isValidSolitaireBoard(); 
  }
	

  /**
    Returns true iff the current board is at the end of the game.  That is, there are NUM_FINAL_PILES
    piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES, in any order.
   */

  public boolean isDone() {
    if(pileNumber == NUM_FINAL_PILES){
      for(int i=0;i<pileNumber-1;i++){
        for(int j=i+1; j<pileNumber-1;j++){
          if((getPiles[i]>pileNumber)||(getPiles[i]==getPiles[j])){
            assert isValidSolitaireBoard(); 
            return false;
          }
        }
      }
      assert isValidSolitaireBoard(); 
      return true;
    }
    return false;  // dummy code to get stub to compile
  }


  /**
     Returns current board configuration as a string with the format of
     a space-separated list of numbers with no leading or trailing spaces.
     The numbers represent the number of cards in each non-empty pile.
   */
  public String configString() {
    String configCurrent = new String();
    if(pileNumber!=0){
      for(int i=0;i<pileNumber-1;i++){
        configCurrent = configCurrent+getPiles[i]+" ";
      }
      configCurrent = configCurrent + getPiles[pileNumber-1];
      assert isValidSolitaireBoard(); 
      return configCurrent;
    }
    return "";   // dummy code to get stub to compile
  }

/**
   Returns true iff the solitaire board data is in a valid state
   (See representation invariant comment for more details.)
 */
  private boolean isValidSolitaireBoard() {
    int s = 0;
    for(int i=0;i<pileNumber;i++){
      if(getPiles[i]>0){
        s = s + getPiles[i];
      }
      else{
        return false;
      }
    }
    if(s==CARD_TOTAL){
      return true;
    }
    return false;  // dummy code to get stub to compile
  }
}

