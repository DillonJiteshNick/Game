package gameProject;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

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
	
	public static BufferedImage image;
	
	public static void main(String[] args) {

		// Set up main window (using Swing's Jframe)
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
		 try {                
	          image = ImageIO.read(new File("/hd-backgrounds-19.jpg"));
	       } catch (IOException ex) {
	            // handle exception...
	       }
		super.paintComponent(g);
		g.drawRect(0, 0, 1265, 100);
		g.drawLine(1150, 0, 1150, 100);
		g.drawLine(1150, 50, 1265, 50);
		g.drawString("Money:", 1185, 20);
		g.drawString("Units:", 10, 20);
		g.drawString("Turrets:", 550, 20);
		g.drawRect(50, 200, 100, 100);
		g.drawRect(1100, 200, 100, 100);
		g.drawImage(image, 0, 0, this);
		int x=51;
		int y=15;
		while (true) {
			
		}
		
		
		
	}
	
	public void run()
	{
		
	}
	int[] postionArray=new int[15];
	
}
