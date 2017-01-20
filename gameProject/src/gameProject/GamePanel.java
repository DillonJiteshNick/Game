package gameProject;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable{

	Timer timer;
	int counter=30;
	public static BufferedImage base;
	public static boolean mouseButtonDown = false;
	public static int x = 0;
	public static int y = 0;
	public static int[] positionArray=new int[15];
	
	int width = 1350;
	int height = 700;
	//Dimension width2 = Toolkit.getDefaultToolkit().getScreenSize();
	
	
	final int pauseDuration = 50;
	
	static int numTroopOne = 10;
	
	TroopOne2[] troopOne = new TroopOne2[numTroopOne];

	public static void main(String[] args) {

		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	      //setBounds(0,0,screenSize.width, screenSize.height);
	      //setVisible(true);

	     // pack()

		JFrame frame = new JFrame("Age of War");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setBounds(0, 0, screenSize.width, screenSize.height);
	//	frame.setSize(new Dimension(1350, 700));
		frame.setAutoRequestFocus(false);
		frame.setVisible(true);
		Container c = frame.getContentPane();
		c.add(new GamePanel());
		frame.pack();
		/*this.setPreferredSize(new Dimension(500, 300));
		this.setBackground(Color.WHITE);*/

	}

	//or if it is the same coordinates of the image- get image coordinates, then it is a click and hit
	
	
	
	
	public GamePanel(){
		
		
		MouseAdapter mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) { 	
		    System.out.println("Image Hit");
	    
		    setBackground(Color.BLACK);
//		    try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		    
//		    setBackground(Color.WHITE);
		    	
		    }
		  };
		
		  addMouseListener(mouseListener);
					this.setPreferredSize(new Dimension(1350, 700));
						this.setBackground(Color.WHITE);


		JPanel turret1Panel;
		turret1Panel=new JPanel();
		turret1Panel.setBounds(0, 0, 100, 100);
		add(turret1Panel);


		JButton turret1BTN;
		turret1BTN = new JButton ("Play");
		turret1BTN.setFont(new Font("Arial", Font.PLAIN, 375));
		setLayout(null);
		turret1BTN.setPreferredSize(new Dimension(100, 100));


		turret1Panel.add(turret1BTN);
		
		turret1BTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBackground(Color.WHITE);
			}
		});
		
		//addActionListener(turret1BTN);
		
		

		//		
		//		Timer timer = new Timer(1000,new ActionListener(){
		//			@Override
		//			public void actionPerformed(ActionEvent e){
		//				int r+=110;
		//				if (r>=1000){
		//					r=1000;
		//					((Timer)e.getSource()).stop*();
		//				}
		//				//repaint();
		//			}
		//		});

		
		this.setPreferredSize(new Dimension(width, height));
		
		for (int i = 0; i < numTroopOne; i++) {
			
			//troopOne[i] = new TroopOne2(650, 300, 0, 100, 0, 100);
			troopOne[i] = new TroopOne2(650, 300, 0, width, 0, height);
			troopOne[i].setXSpeed(Math.random() * 16-8);
			troopOne[i].setYSpeed(Math.random() * 16-8);

		}
		
		Thread gameThread = new Thread(this);
		gameThread.start();
		
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
		g.drawRect(20, 50, 1000, 1000);
		g.drawRect(1100, 200, 100, 100);
		
		for (int i = 0; i < numTroopOne; i++) {
			troopOne[i].draw(g);

		}
		
		
		troopOne[0].draw(g);
	}

	public void run()
	{
		while (true) {
			repaint();
			try {
				Thread.sleep(pauseDuration);
			} catch (InterruptedException e) {
			}
		}
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
		System.out.println(x + "," + y);
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
	public static int[] getPositionArray ()
	{
		return positionArray;
	}

	public void setPostionArray(int [] positionArray)
	{
		positionArray = GamePanel.positionArray;
	}
	
	
	/**
	 * 
	 * Paint component- draw the Nacks on the frame in their set location
	 */
	

}