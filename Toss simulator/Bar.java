package pa1;

//Name:Kaifeng Zheng
//USC NetID: 5051921484
//CS 455 PA1
//Fall 2017

//we included the import statements for you
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
* Bar class
* A labeled bar that can serve as a single bar in a bar graph.
* The text for the label is centered under the bar.
* 
* NOTE: we have provided the public interface for this class. Do not change
* the public interface. You can add private instance variables, constants,
* and private methods to the class. You will also be completing the
* implementation of the methods given.
* 
*/
public class Bar {
	private int bottomn;
	private int leftn;
	private int widthn;
	private int barHeightn;
    private Color colorn;
    private double scalen;
    private String labeln;
    private int frameHeight;
    private int frameWidth;
    

/**
   Creates a labeled bar.  You give the height of the bar in application
   units (e.g., population of a particular state), and then a scale for how
   tall to display it on the screen (parameter scale). 

   @param bottom  location of the bottom of the label
   @param left  location of the left side of the bar
   @param width  width of the bar (in pixels)
   @param barHeight  height of the bar in application units
   @param scale  how many pixels per application unit
   @param color  the color of the bar
   @param label  the label at the bottom of the bar
*/
public Bar(int bottom, int left, int width, int barHeight,double scale, Color color, String label) {
	bottomn = bottom;
	leftn = left;
	widthn = width;
	barHeightn = barHeight;
	scalen = scale;
	colorn = color;
	labeln = label;
	frameWidth = (int)Math.round (width*(800/100.0));
	frameHeight = (int)Math.round(bottom/0.8);
	System.out.println(frameHeight+" "+frameWidth);
}

/**
   Draw the labeled bar. 
   @param g2  the graphics context
*/
public void draw(Graphics2D g2) {
	   
	   
	   Rectangle Rect = new Rectangle(leftn,bottomn-barHeightn,widthn,barHeightn); 
	   g2.setColor(colorn);
	   g2.fill(Rect);
	
	   
	   g2.setFont(new Font("TimesRoman",Font.PLAIN,(int)Math.round(12)));
	   g2.setColor(Color.BLACK);
	   

	   //labels properties
	   Font font = g2.getFont();
	   FontRenderContext context = g2.getFontRenderContext();
	   Rectangle2D labelBounds = font.getStringBounds(labeln, context);
	   int widthOfLabel = (int) labelBounds.getWidth();
	   int heightOfLabel = (int) labelBounds.getHeight();
	   
	 
	   
	   
	   //(using for test) perform labels properties to test if they are in the correct position  

	  // System.out.println("labelBounds= "+labelBounds+" "+frameWidth);
	   //System.out.println("widthOfLabel= "+widthOfLabel);
	   //System.out.println("heightOfLabel= "+heightOfLabel);
	   
	   //(using for test) Perform the properties of each rectangle  
	  /*****************************************************
	   int a = Rect.getBounds().x;
	   int b = Rect.getBounds().y;
	   int c = Rect.getBounds().width;
	   int d = Rect.getBounds().height;
	   System.out.println("("+a+" , "+b+")");
	   System.out.println(" width= "+c+" , "+"height= "+d);
	   *****************************************************/
	   
	   g2.drawString(labeln,(leftn+(int)Math.round(((50/800.0)*frameWidth)-(widthOfLabel)/2)), (int)Math.round(bottomn+(15/500.0)*frameHeight));  //draw the label and make the label centered
	  
	   //(using for test) perform the coordinates of labels
	   /*****************************************************
	   int a = (leftn+50)-(widthOfLabel)/2;
	   int b = bottomn+15;
	   System.out.println("("+a+" , "+b+")");
	   *****************************************************/
	  
}
}