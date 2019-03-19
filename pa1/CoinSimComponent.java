package pa1;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

//Name:Kaifeng Zheng
//Java 8

public class CoinSimComponent extends JComponent {
	private int numTryn;
	private int frameWidthn;
	private int frameHeightn;

	public CoinSimComponent(){
		this(0,0,0);
		numTryn=0;
		
	}
	public CoinSimComponent(int numTry,int frameWidth,int frameHeight){
		numTryn = numTry;
		frameWidthn = frameWidth ;
		frameHeightn = frameHeight;
	}
	public void paintComponent(Graphics g){
		CoinTossSimulator coinSim = new CoinTossSimulator();
		coinSim.run(numTryn);
		double numtrails = coinSim.getNumTrials();
		int twoheads = coinSim.getTwoHeads();
		int headtails = coinSim.getHeadTails();
		int twotails = coinSim.getTwoTails();
		int scaleOnBar1 = 0;
		int scaleOnBar2 = 0;
		int scaleOnBar3 = 0;
		double scale = 0;
		double scaleHeight = 0;

	
	
	/*******************************************************
	 * According to assignment documentation, the highest  *
	 * bar need in a height range from: bottom to          *
	 * bottom minus top margin, called vb in text which    *
	 * equals 50 in my program, equals 350. the scale      *
	 * calculated based on the 1/5 of the shortest bar.    *
	 *******************************************************/
		scale = (Math.min(twoheads,Math.min(headtails,twotails))*scaleHeight)/5;
			
			scaleHeight = (frameHeightn*(350/500.0))/headtails;
			scaleOnBar2 = (int) Math.round(scaleHeight*headtails);
			scaleOnBar1 = (int) (twoheads*scaleHeight);
			scaleOnBar3 = (int) (twotails*scaleHeight);
         //scale equals the height of one in five parts of the lowest bar.
		//System.out.println(scale+" "+scaleHeight);
		int space = (int)Math.round((100/500.0)*frameHeightn);
		int bottom = frameHeightn-space;
		System.out.println(bottom);
	
	/********************************************************
	 * in this part, I build up a 2D Graphics to store bars *
	 * I set the bar bottom equals 400; the gap between two *
	 * bars equals 100; widths of bars equals 100.colors    *
	 * are red, green, blue, separately. Finally, making    *
	 * labels explicitly explains the information of the bar*
	 * graph.                                               *
	 ********************************************************/
	
		Graphics2D g2 = (Graphics2D)g;
		Bar barTwoHeads= new Bar(bottom,(int)((150/800.0)*frameWidthn),(int)((100/800.0)*frameWidthn),scaleOnBar1,scaleHeight,Color.RED,"Two Heads: "+twoheads+"("+Math.round((twoheads/numtrails)*100)+"%"+")");
		Bar barHeadTail= new Bar(bottom,(int)((350/800.0)*frameWidthn),(int)((100/800.0)*frameWidthn),scaleOnBar2,scaleHeight,Color.GREEN,"A Head and tail: "+headtails+"("+Math.round((headtails/numtrails)*100)+"%"+")");
		Bar barTwoTails= new Bar(bottom,(int)((550/800.0)*frameWidthn),(int)((100/800.0)*frameWidthn),scaleOnBar3,scaleHeight,Color.BLUE,"two tails: "+twotails+"("+Math.round((twotails/numtrails)*100)+"%"+")");
		barTwoHeads.draw(g2);
		barHeadTail.draw(g2);
		barTwoTails.draw(g2);
		coinSim.reset();
	}
}
