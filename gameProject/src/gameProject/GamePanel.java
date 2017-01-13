package gameProject;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	
	
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
		this.setPreferredSize(new Dimension(1275, 950));
		this.setBackground(Color.WHITE);
	
		Graphics g = getGraphics();
		g.drawRect(0, 0, 1275, 100);
	}
	public void paintHUD(Graphics g){
		g.drawRect(0, 0, 1275, 100);
	}
	
	public void run()
	{
		
	}
	int[] postionArray=new int[15];
	
}
