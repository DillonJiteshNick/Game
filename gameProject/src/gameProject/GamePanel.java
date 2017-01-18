package gameProject;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class GamePanel extends JPanel implements Runnable{
	
	//public static BufferedImage base;
	public static boolean mouseButtonDown=false;
	public static int x=0;
	public static int y=0;
	public static void main(String[] args) {

		JFrame frame = new JFrame("Age of War");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setSize(new Dimension(1000, 500));
		frame.setAutoRequestFocus(false);
		frame.setVisible(true);
		Container c = frame.getContentPane();
		c.add(new GamePanel());
		frame.pack();
		/*this.setPreferredSize(new Dimension(500, 300));
		this.setBackground(Color.WHITE);*/

	}
	public GamePanel(){
		this.setPreferredSize(new Dimension(1265, 950));
		this.setBackground(Color.WHITE);
		

	}
	public void paintComponent(Graphics g){
//		 try {                
//	          base = ImageIO.read(new File("/base.png"));
//	       } catch (IOException ex) {
//	            // handle exception...
//	       }
		super.paintComponent(g);
		//g.drawImage(base, 0, 0, this);
		g.drawRect(0, 0, 1265, 100);
		g.drawLine(1150, 0, 1150, 100);
		g.drawLine(1150, 50, 1265, 50);
		g.drawString("Money:", 1185, 20);
		g.drawString("Units:", 10, 20);
		g.drawString("Turrets:", 550, 20);
		g.drawRect(50, 20, 90, 60);
		g.drawRect(170, 20, 90, 60);
		g.drawRect(290, 20, 90, 60);
		//g.drawRect(20, 50, 1000, 1000);
		g.drawRect(1100, 200, 100, 100);		
	}
	public void paint(Graphics g)
	{
		
	}
	
	public void run()
	{
		
	}
	  /**
	    * called when a mouse button is pressed
	    * @param e The mouse event
	    **/
	    public void mousePressed (MouseEvent e)
	    {
	    	mouseButtonDown = true;
			x = e.getX ();
			y = e.getY ();
			repaint ();
	    }
	    
	    /**
	    * called when a mouse button is released
	    * @param e The mouse event
	    **/
	    public void mouseReleased (MouseEvent e)
	    {
	    	mouseButtonDown = false;
	    	repaint ();
	    }
	int[] postionArray=new int[15];
	
}
