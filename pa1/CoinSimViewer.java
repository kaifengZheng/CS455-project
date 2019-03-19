package pa1;
import java.util.Scanner;
import javax.swing.JFrame;

//Name:Kaifeng Zheng

public class CoinSimViewer {
	public static void main(String[] args){
		
		
		Scanner scan1 = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		Scanner scan3 = new Scanner(System.in);
		System.out.println("How many times tosses?(Enter an integer large than 0):");
		int numTry = scan1.nextInt();
		System.out.println("Please enter the frame width(Enter an integer large than 0):");
		int frameWidth = scan2.nextInt();
		System.out.println("Please enter the frame height(Enter an integer large than 0):");
		int frameHeight = scan3.nextInt();
		if(numTry<0||frameWidth<0||frameHeight<0){
			System.out.println("Error:You must enter an integer large than 0!");
			System.exit(-1);
		}
		JFrame frame = new JFrame();
		frame.setSize(frameWidth,frameHeight);
		frame.setTitle("Coinsim");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CoinSimComponent coin = new CoinSimComponent(numTry,frameWidth,frameHeight);
		frame.add(coin);
		frame.setVisible(true);
	
		
		/*int a = frame.getBounds().width;
		int b = frame.getBounds().height;
		System.out.println("("+a+" , "+b+")");*/
		
		
	}
}
