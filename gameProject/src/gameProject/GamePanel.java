package gameProject;


//Have a counter for number of troops defeated and how many of your troops died
//Put this at the end, save to a file, and then sort

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable{

	//public static int AIChoice = (int) (Math.random() * 10);
	public static int AICharacterChoice = ((int) (Math.random() * 11) + 1);

	Timer timer;
	Timer timer2;
	Timer timer3;
	Timer timer4;
	//int counter = 30;
	//Timer timer;
	int counter=30;
	public static BufferedImage base;
	public static boolean mouseButtonDown = false;
	public static int x = 0;
	public static int y = 0;
	public static int[] positionArray=new int[15];

	public static int troopOneCurrent = -1;
	public static int troopTwoCurrent = -1;
	public static int troopThreeCurrent = -1;

	public static int troopOneCurrentAI = -1;
	public static int troopTwoCurrentAI = -1;
	public static int troopThreeCurrentAI = -1;

	public static boolean turretOneClicked = false;
	public static boolean turretTwoClicked = false;
	public static boolean turretThreeClicked = false;

	public static boolean turretOneActive = false;
	public static boolean turretTwoActive = false;
	public static boolean turretThreeActive = false;

	public static boolean turretActive = false;
	public static int turretRockCurrent = -1;

	public int troopOneDamage = 2;
	public int troopTwoDamage = 5;
	public int troopThreeDamage = 10;

	public static int userBaseHealth = 100;
	public static int compBaseHealth = 100;

	JLabel turretOneLabel;
	JLabel turretTwoLabel;
	JLabel turretThreeLabel;
	JLabel refreshTurretLabel;
	public static JLabel moneyLabel;
	public static JPanel moneyPanel;

	public static int totalMoney = 200;
	public int TROOP_ONE_MONEY = 5;
	public int TROOP_TWO_MONEY = 15;
	public int TROOP_THREE_MONEY = 25;

	public int TURRET_ONE_MONEY = 75;
	public int TURRET_TWO_MONEY = 175;
	public int TURRET_THREE_MONEY = 350;


	public static int turretRockDamage = 1;

	public static JButton turret1BTN;
	public static JButton turret2BTN;
	public static JButton turret3BTN;

	public static boolean pauseGame = false;

	boolean mouseOut = false;



	//ArrayList<JLabel> troopOneLabel = new ArrayList<JLabel>();




	int width = 1350;
	int height = 700;
	//Dimension width2 = Toolkit.getDefaultToolkit().getScreenSize();


	final int pauseDuration = 50;

	//static int numTroopOne = 1;

	//TroopOne[] troopOne = new TroopOne[numTroopOne];
	ArrayList<TroopOne> troopOne = new ArrayList<TroopOne>();

	ArrayList<TroopTwo> troopTwo = new ArrayList<TroopTwo>();

	ArrayList<TroopThree> troopThree = new ArrayList<TroopThree>();

	ArrayList<AITroopOne> troopOneAI = new ArrayList<AITroopOne>();
	ArrayList<AITroopTwo> troopTwoAI = new ArrayList<AITroopTwo>();
	ArrayList<AITroopThree> troopThreeAI = new ArrayList<AITroopThree>();








	ArrayList<turretShooter> turretRock = new ArrayList<turretShooter>();

	//static int numTroopTwo = 1;

	//TroopTwo[] troopTwo = new TroopTwo[numTroopTwo];

	//static int numTroopThree = 1;

	//TroopThree[] troopThree = new TroopThree[numTroopThree];

	private BufferedImage castleImageLeft;
	private BufferedImage castleImageRight;

	private BufferedImage turretOneImage;

	private BufferedImage turretTwoImage;

	private BufferedImage turretThreeImage;

	public BufferedImage refreshTurretImage;

	public JPanel baseHouseComPanel;
	public JLabel baseHouseComLabel;

	JPanel baseHealthUserPanel;
	JLabel baseHealthUserLabel;

	JPanel troop1Panel;
	JButton troop1BTN;
	JPanel troop2Panel;
	JButton troop2BTN;
	JPanel troop3Panel;
	JButton troop3BTN;

	JPanel turret1Panel;
	JPanel turret2Panel;
	JPanel turret3Panel;

	JPanel playAgainPanel;
	JButton playAgainBTN;
	JButton quitBTN;

	JPanel startBTNPanel;
	JPanel startBTN;


	public static void main(String[] args) {


		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//setBounds(0,0,screenSize.width, screenSize.height);
		//setVisible(true);

		// pack()
		//		JFrame introFrame = new JFrame("Endless War");
		//		introFrame.setVisible(true);
		//		introFrame.setSize(new Dimension(1200, 700));
		//		introFrame.setAutoRequestFocus(false);
		//		introFrame.setVisible(true);
		//		Container w = introFrame.getContentPane();
		//		//w.add(new GamePanel());
		//		introFrame.pack();



		JFrame frame = new JFrame("Endless War");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//frame.setBounds(0, 0, screenSize.width, screenSize.height);
		frame.setSize(new Dimension(1200, 700));
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



//		startBTNPanel=new JPanel();
//		startBTNPanel.setBounds(0, 0, 1200, 600);
//		add(startBTNPanel);
//
//
//		JButton startBTN;
//		startBTN = new JButton ("Play");
//		startBTN.setFont(new Font("Arial", Font.PLAIN, 375));
//		setLayout(null);
//		startBTN.setPreferredSize(new Dimension(1200, 600));
//
//
//		startBTNPanel.add(startBTN);
//		//timer.stop();
//
//		startBTN.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				remove(startBTNPanel);
//				//timer.start();
//			}
//		});






		moneyPanel = new JPanel();
		moneyPanel.setBounds(875, 0, 100, 100);
		//moneyPanel.setBackground(new Color(181,164,13));
		//moneyPanel.setBackground(new Color(205,185,10));
		moneyPanel.setBackground(Color.LIGHT_GRAY);
		add(moneyPanel);

		moneyLabel = new JLabel();
		moneyLabel.setBounds(875, 0, 100, 100);
		setLayout(null);
		moneyLabel.setFont(new Font("Arial", Font.PLAIN, 45));

		BufferedImage coinImage = null;
		try {                
			coinImage = ImageIO.read(new File("src/coinImage.png"));
		} catch (IOException ex) {
			System.out.println("Error with Image");
		}

		JLabel coinImageLabel = new JLabel(new ImageIcon(coinImage));

		moneyPanel.add(coinImageLabel);


		moneyPanel.add(moneyLabel);


		baseHouseComPanel = new JPanel();
		baseHouseComPanel.setBounds(1100, 50, 85, 50);
		//moneyPanel.setBackground(new Color(181,164,13));
		baseHouseComPanel.setBackground(Color.LIGHT_GRAY);
		add(baseHouseComPanel);

		baseHouseComLabel = new JLabel();
		baseHouseComLabel.setBounds(1100, 50, 85, 50);
		setLayout(null);
		baseHouseComLabel.setFont(new Font("Arial", Font.PLAIN, 15));


		baseHouseComPanel.add(baseHouseComLabel);
		baseHouseComLabel.setText("<html>Comp Base<br>"
				+ "Health: " + compBaseHealth);


		baseHealthUserPanel = new JPanel();
		baseHealthUserPanel.setBounds(1100, 0, 85, 50);
		//moneyPanel.setBackground(new Color(181,164,13));
		baseHealthUserPanel.setBackground(Color.LIGHT_GRAY);
		add(baseHealthUserPanel);

		baseHealthUserLabel = new JLabel();
		baseHealthUserLabel.setBounds(1100, 0, 85, 50);
		setLayout(null);
		baseHealthUserLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		baseHealthUserPanel.add(baseHealthUserLabel);
		baseHealthUserLabel.setText("<html>User Base<br>"
				+ "Health: " + userBaseHealth);





		try {                
			castleImageLeft = ImageIO.read(new File("src/CastleGood.png"));
		} catch (IOException ex) {
			System.out.println("Error with Image");
		}

		try {                
			castleImageRight = ImageIO.read(new File("src/CastleGood 2.png"));
		} catch (IOException ex) {
			System.out.println("Error with Image");
		}

		try {                
			turretOneImage = ImageIO.read(new File("src/TurretOne.png"));
		} catch (IOException ex) {
			System.out.println("Error with Image");
		}

		try {                
			turretTwoImage = ImageIO.read(new File("src/TurretTwo.png"));
		} catch (IOException ex) {
			System.out.println("Error with Image");
		}

		try {                
			turretThreeImage = ImageIO.read(new File("src/TurretThree.png"));
		} catch (IOException ex) {
			System.out.println("Error with Image");
		}

		try {                
			refreshTurretImage = ImageIO.read(new File("src/BlankWhiteBackground.png"));
		} catch (IOException ex) {
			System.out.println("Error with Image");
		}



		Cursor cursor = Cursor.getDefaultCursor();

		//Change the cursor
		cursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR); 
		setCursor(cursor);

		timer = new Timer(3000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pauseGame == true) {

				}
				else {
					AICharacterChoice = ((int) (Math.random() * 11) + 1);

					if (AICharacterChoice <=4) {
						troopOneCurrentAI = troopOneCurrentAI +1;
						troopOneAI.add(new AITroopOne(1200, 465, 0, width, 0, height));
						troopOneAI.get(troopOneAI.size()-1).setXSpeed(-5);
						troopOneAI.get(troopOneAI.size()-1).setYSpeed(0);

						if (troopOneAI.get(troopOneAI.size()-1).getX() == 0) {
							troopOneAI.remove(troopOneAI.size()-1);
						}

					}
					else if (AICharacterChoice <=8) {
						//Makes AI Troop Two appear
						troopTwoCurrentAI = troopTwoCurrentAI +1;
						troopTwoAI.add(new AITroopTwo(1200, 460, 0, width, 0, height));
						troopTwoAI.get(troopTwoAI.size()-1).setXSpeed(-5);
						troopTwoAI.get(troopTwoAI.size()-1).setYSpeed(0);

						if (troopTwoAI.get(troopTwoAI.size()-1).getX() == 0) {
							troopTwoAI.remove(troopTwoAI.size()-1);
						}
					}
					else {
						//Makes AI Troop Three appear
						troopThreeCurrentAI = troopThreeCurrentAI +1;
						troopThreeAI.add(new AITroopThree(1200, 520, 0, width, 0, height));
						troopThreeAI.get(troopThreeAI.size()-1).setXSpeed(-5);
						troopThreeAI.get(troopThreeAI.size()-1).setYSpeed(0);

						if (troopThreeAI.get(troopThreeAI.size()-1).getX() == 0) {
							troopThreeAI.remove(troopThreeAI.size()-1);
						}
					}

				}
			}
		});
		timer.start();



		startBTNPanel=new JPanel();
		startBTNPanel.setBounds(0, 0, 1200, 600);
		startBTNPanel.setBackground(Color.YELLOW);
		add(startBTNPanel);


		JButton startBTN;
		startBTN = new JButton ("<html>___Play___<br> Endless War");
		startBTN.setBackground(Color.YELLOW);
		startBTN.setFont(new Font("Arial", Font.PLAIN, 200));
		setLayout(null);
		startBTN.setPreferredSize(new Dimension(1200, 600));


		startBTNPanel.add(startBTN);
		timer.stop();
		remove(moneyPanel);
		remove(baseHouseComPanel);
		remove(baseHealthUserPanel);
		

		startBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add(baseHouseComPanel);
				add(baseHealthUserPanel);
				add(moneyPanel);
				remove(startBTNPanel);
				timer.start();
			}
		});








		//addMouseListener(mouseListener);
		this.setPreferredSize(new Dimension(1200, 700));
		this.setBackground(Color.WHITE);



		//		JPanel imagePanel = new JPanel();
		//		imagePanel.setBounds(0, 0, 100, 100);
		//		add(imagePanel);
		//		
		//		imagePanel.add(turretOneImage);

		//		JLabel picLabel = new JLabel(new ImageIcon(turretOneImage));
		//		add(picLabel);


		troop1Panel=new JPanel();
		troop1Panel.setBounds(75, 0, 100, 100);
		add(troop1Panel);



		troop1BTN = new JButton ("<html>Troop 1<br>" + "5 Coins");
		troop1BTN.setFont(new Font("Arial", Font.PLAIN, 12));
		setLayout(null);
		troop1BTN.setPreferredSize(new Dimension(100, 100));


		troop1Panel.add(troop1BTN);




		//turretRock.add(new turretShooter(0, 465, 0, width, 0, height));






		troop1BTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pauseGame == true) {

				}
				else {
					if (totalMoney < 5) {
						//troop1BTN.setEnabled(false);
					}
					else {
						troop1BTN.setEnabled(true);


						totalMoney = totalMoney - TROOP_ONE_MONEY;
						//moneyLabel.setText(String.valueOf(totalMoney));

						//				turretRock.add(new turretShooter(0, 465, 0, width, 0, height));
						//				//turretRock.add(new turretShooter(0, 465, 0, width, 0, height));
						//				turretRock.get(turretRock.size()-1).setX(10);
						//				turretRock.get(turretRock.size()-1).setY(10);
						//				
						troopOneCurrent = troopOneCurrent +1;





						troopOne.add(new TroopOne(0, 465, 0, width, 0, height));
						troopOne.get(troopOne.size()-1).setXSpeed(5);
						troopOne.get(troopOne.size()-1).setYSpeed(0);

						setBackground(Color.WHITE);
					}

				}
			}
		});




		troop2Panel=new JPanel();
		troop2Panel.setBounds(175, 0, 100, 100);
		add(troop2Panel);



		troop2BTN = new JButton ("<html>Troop 2<br>" + "15 Coins");
		troop2BTN.setFont(new Font("Arial", Font.PLAIN, 12));
		setLayout(null);
		troop2BTN.setPreferredSize(new Dimension(100, 100));


		troop2Panel.add(troop2BTN);

		troop2BTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				if (pauseGame == true) {

				}
				else {
					if (totalMoney < 15) {
						//troop2BTN.setEnabled(false);
					}
					else {
						troop2BTN.setEnabled(true);

						totalMoney = totalMoney - TROOP_TWO_MONEY;

						troopTwoCurrent = troopTwoCurrent +1;
						troopTwo.add(new TroopTwo(0, 460, 0, width, 0, height));
						troopTwo.get(troopTwo.size()-1).setXSpeed(5);
						troopTwo.get(troopTwo.size()-1).setYSpeed(0);

						if (troopTwo.get(troopTwo.size()-1).getX() == 1200) {
							troopTwo.remove(troopTwo.size()-1);
						}




						setBackground(Color.WHITE);
					}
				}
			}
		});



		troop3Panel=new JPanel();
		troop3Panel.setBounds(275, 0, 100, 100);
		add(troop3Panel);



		troop3BTN = new JButton ("<html>Troop 3<br>" + "25 Coins");
		troop3BTN.setFont(new Font("Arial", Font.PLAIN, 12));
		setLayout(null);
		troop3BTN.setPreferredSize(new Dimension(100, 100));


		troop3Panel.add(troop3BTN);

		troop3BTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pauseGame == true) {

				}
				else {
					if (totalMoney < 25) {
						//troop3BTN.setEnabled(false);
					}
					else {
						troop3BTN.setEnabled(true);

						totalMoney = totalMoney - TROOP_THREE_MONEY;

						troopThreeCurrent = troopThreeCurrent +1;
						troopThree.add(new TroopThree(0, 520, 0, width, 0, height));
						troopThree.get(troopThree.size()-1).setXSpeed(5);
						troopThree.get(troopThree.size()-1).setYSpeed(0);

						if (troopThree.get(troopThree.size()-1).getX() == 1200) {
							troopThree.remove(troopThree.size()-1);
						}


						setBackground(Color.WHITE);
					}
				}
			}
		});




		turret1Panel=new JPanel();
		turret1Panel.setBounds(465, 0, 100, 100);
		add(turret1Panel);



		turret1BTN = new JButton ("<html>Turret 1<br>" + "75 Coins");
		turret1BTN.setFont(new Font("Arial", Font.PLAIN, 12));
		setLayout(null);
		turret1BTN.setPreferredSize(new Dimension(100, 100));


		turret1Panel.add(turret1BTN);


		turretOneLabel = new JLabel(new ImageIcon(turretOneImage));
		turretOneLabel.setBounds(0, 100, turretOneImage.getWidth(), turretOneImage.getHeight());
		add(turretOneLabel);

		turretOneLabel.setVisible(false);

		turretTwoLabel = new JLabel(new ImageIcon(turretTwoImage));
		turretTwoLabel.setBounds(0, 100, turretTwoImage.getWidth(), turretTwoImage.getHeight());
		add(turretTwoLabel);

		turretTwoLabel.setVisible(false);

		turretThreeLabel = new JLabel(new ImageIcon(turretThreeImage));
		turretThreeLabel.setBounds(0, 100, turretThreeImage.getWidth(), turretThreeImage.getHeight());
		add(turretThreeLabel);

		turretThreeLabel.setVisible(false);

		turret1BTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pauseGame == true) {

				}
				else {
					if (totalMoney < 75) {

					}
					else {



						turret1BTN.setEnabled(false);
						totalMoney = totalMoney - TURRET_ONE_MONEY;

						if (turretRockCurrent > -1) {
							try {
								try {
									timer2.stop();
								}
								catch (NullPointerException w) {

								}
								try {
									timer3.stop();
								}
								catch (NullPointerException w) {

								}
								try {
									timer4.stop();
								}
								catch (NullPointerException w) {

								}

								for (int i = turretRockCurrent; i > -1; i --) {
									turretRock.remove(i);
								}
							}
							catch (IndexOutOfBoundsException o) {
								System.out.println("Clearing the turrets");
							}
						}

						timer2 = new Timer(4000, new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent ie) {

								turretRock.add(new turretShooter(20, 90, 0, width, 0, height));
								turretRock.get(turretRock.size()-1).setXSpeed(10);
								turretRock.get(turretRock.size()-1).setYSpeed(2);
								turretRockCurrent++;
								compBaseHealth = compBaseHealth - 1;
								//turretRock.get(turretRock.size()-1).draw(g);
								System.out.println("Rock Fire");
							}
						});
						timer2.start();


						turretActive = true;



						turretOneClicked = true;


						turretOneLabel.setVisible(true);

						if (turretTwoClicked)  {


							turretTwoLabel.setVisible(false);

						}
						if (turretThreeClicked) {
							turretThreeLabel.setVisible(false);
						}




						System.out.println("Turret 1 Clicked");
					}
				}
			}
		});




		turret2Panel=new JPanel();
		turret2Panel.setBounds(565, 0, 100, 100);
		add(turret2Panel);

		turret2BTN = new JButton ("<html>Troop 2<br>" + "175 Coins");
		turret2BTN.setFont(new Font("Arial", Font.PLAIN, 12));
		setLayout(null);
		turret2BTN.setPreferredSize(new Dimension(100, 100));


		turret2Panel.add(turret2BTN);

		turret2BTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pauseGame == true) {

				}

				else {
					if (totalMoney < 175) {

					}
					else {
						turret2BTN.setEnabled(false);
						totalMoney = totalMoney - TURRET_TWO_MONEY;


						if (turretRockCurrent > -1) {
							try {
								try {
									timer2.stop();
								}
								catch (NullPointerException w) {

								}
								try {
									timer3.stop();
								}
								catch (NullPointerException w) {

								}
								try {
									timer4.stop();
								}
								catch (NullPointerException w) {

								}

								for (int i = turretRockCurrent; i > -1; i --) {
									turretRock.remove(i);
								}
							}
							catch (IndexOutOfBoundsException o) {
								System.out.println("Clearing the turrets");
							}
						}

						timer3 = new Timer(2000, new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent ie) {

								turretRock.add(new turretShooter(20, 90, 0, width, 0, height));
								turretRock.get(turretRock.size()-1).setXSpeed(10);
								turretRock.get(turretRock.size()-1).setYSpeed(2);
								turretRockCurrent++;
								compBaseHealth = compBaseHealth - 1;
								//turretRock.get(turretRock.size()-1).draw(g);
								System.out.println("Rock Fire");
							}
						});
						timer3.start();

						turretActive = true;

						turretTwoClicked= true;

						//				turretTwoLabel = new JLabel(new ImageIcon(turretTwoImage));
						//				turretTwoLabel.setBounds(0, 100, turretTwoImage.getWidth(), turretTwoImage.getHeight());
						//				add(turretTwoLabel);

						turretTwoLabel.setVisible(true);

						if (turretOneClicked ) {
							turretOneLabel.setVisible(false);

						}
						if (turretThreeClicked) {
							turretThreeLabel.setVisible(false);
						}

						//turretTwoClicked= false;

						System.out.println("Turret 2 Clicked");
					}
				}
			}
		});




		turret3Panel=new JPanel();
		turret3Panel.setBounds(665, 0, 100, 100);
		add(turret3Panel);



		turret3BTN = new JButton ("<html>Troop 3<br>" + "350 Coins");
		turret3BTN.setFont(new Font("Arial", Font.PLAIN, 12));
		setLayout(null);
		turret3BTN.setPreferredSize(new Dimension(100, 100));


		turret3Panel.add(turret3BTN);

		turret3BTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pauseGame == true) {

				}

				else {
					if (totalMoney < 350) {

					}
					else {
						turret3BTN.setEnabled(false);


						totalMoney = totalMoney - TURRET_THREE_MONEY;

						if (turretRockCurrent > -1) {
							try {
								try {
									timer2.stop();
								}
								catch (NullPointerException w) {

								}
								try {
									timer3.stop();
								}
								catch (NullPointerException w) {

								}
								try {
									timer4.stop();
								}
								catch (NullPointerException w) {

								}

								for (int i = turretRockCurrent; i > -1; i --) {
									turretRock.remove(i);
								}
							}
							catch (IndexOutOfBoundsException o) {
								System.out.println("Clearing the turrets");
							}
						}


						timer4 = new Timer(1000, new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent ie) {

								turretRock.add(new turretShooter(20, 90, 0, width, 0, height));
								turretRock.get(turretRock.size()-1).setXSpeed(10);
								turretRock.get(turretRock.size()-1).setYSpeed(2);
								turretRockCurrent++;
								compBaseHealth = compBaseHealth - 1;
								//turretRock.get(turretRock.size()-1).draw(g);
								System.out.println("Rock Fire");
							}
						});
						timer4.start();

						turretActive = true;

						turretThreeClicked = true;

						turretThreeLabel.setVisible(true);

						if (turretOneClicked)  {
							turretOneLabel.setVisible(false);

						}
						if (turretTwoClicked) {
							turretTwoLabel.setVisible(false);
						}


						//	turretThreeClicked = false;


						System.out.println("Turret 3 Clicked");
					}
				}
			}
		});





		//	this.setPreferredSize(new Dimension(width, height));
		this.setPreferredSize(new Dimension(1200, 600));




		Thread gameThread = new Thread(this);
		gameThread.start();

		addMouseListener(new MouseAdapter() { 
			public void mouseClicked(MouseEvent e) { 	
				System.out.println("Image Hit");

				System.out.println("X Click: " + e.getX() + " Y Click: " + e.getY());


			}
			public void mouseExited(MouseEvent e) {
				//timer.stop();
				//				troopOneDamage = 0;
				//				troopTwoDamage = 0;
				//				troopThreeDamage = 0;
				//pauseGame = true;
				System.out.println("Mouse Exited");
				//mouseOut = true;
				//while (mouseOut == true) {
				//				try {
				//					gameThread.sleep(1000);
				//				} catch (InterruptedException e1) {
				//					// TODO Auto-generated catch block
				//					e1.printStackTrace();
				//				}
				//}
			}
			public void mouseEntered(MouseEvent i) {
				//timer.start();

				troopOneDamage = 2;
				troopTwoDamage = 5;
				troopThreeDamage = 10;
				//mouseOut = false;
				pauseGame = false;
				System.out.println("Mouse Entered");
			}
		});


		//
		//
		//		startBTNPanel.setBounds(0, 0, 1200, 600);
		//		startBTN.setBounds(0, 0, 1200, 600);
		//		startBTNPanel.add(startBTN);
		//

	}



	@SuppressWarnings("deprecation")
	public void paintComponent(Graphics g){

		super.paintComponent(g);
		if (pauseGame == true) {

		}
		else {



			g.drawImage(castleImageLeft, -75, 180, null);
			g.drawImage(castleImageRight, 1070, 180, null);



			g.drawRect(0, 0, 1200, 100);


			g.drawString("Units:", 10, 20);
			g.drawString("Turrets:", 400, 20);
			g.drawString("Money:", 800, 20);
			g.drawString("Base Health:", 1000, 20);



			baseHealthUserLabel.setText("<html>User Base<br>"
					+ "Health: " + userBaseHealth);


			baseHouseComLabel.setText("<html>Comp Base<br>"
					+ "Health: " + compBaseHealth);




			for (int i = 0; i < turretRockCurrent + 1; i++) {
				try {
					turretRock.get(i).draw(g);
					moneyLabel.setText(String.valueOf(totalMoney));

				}
				catch (IndexOutOfBoundsException n) {
					System.out.println("Turret Rock Shoot");
				}

				//try {

				//}
				//			catch (IndexOutOfBoundsException io) {
				//				System.out.println("Rock Hit Base");
				//			}
			}










			for (int i = 0; i < troopOneCurrent + 1; i ++) {
				if (troopOneCurrent > -1) {
					troopOne.get(i).draw(g);

					moneyLabel.setText(String.valueOf(totalMoney));

					//moneyLabel.setText(String.valueOf(totalMoney));
					System.out.println(totalMoney);


					if (troopOne.get(troopOneCurrent).getX() - 1200 > -15 && troopOne.get(troopOneCurrent).getX()- 1200 < 15) {


						troopOne.remove(troopOneCurrent);
						troopOneCurrent = troopOneCurrent - 1;
						compBaseHealth = compBaseHealth - troopOneDamage;

						if (compBaseHealth == 0) {
							//stop all the moving object and stop the buttons
						}






						System.out.println("Troop One Removed: " + compBaseHealth);
					}

				}


			}



			for (int i = 0; i < troopTwoCurrent + 1; i ++) {
				if (troopTwoCurrent > -1) {
					troopTwo.get(i).draw(g);

					moneyLabel.setText(String.valueOf(totalMoney));

					//moneyLabel.setText("" + totalMoney);


					if (troopTwo.get(troopTwoCurrent).getX() - 1200 > -15 && troopTwo.get(troopTwoCurrent).getX()- 1200 < 15) {
						troopTwo.remove(troopTwo.size()-1);
						troopTwoCurrent = troopTwoCurrent - 1;
						System.out.println("Troop Two Removed");

						compBaseHealth = compBaseHealth - troopTwoDamage;

						baseHouseComLabel.setText("<html>Comp Base<br>"
								+ "Health: " + compBaseHealth);


						System.out.println("Troop Two Remove: " + compBaseHealth);



					}

				}

			}

			for (int i = 0; i < troopThreeCurrent + 1; i ++) {
				if (troopThreeCurrent > -1) {
					troopThree.get(i).draw(g);

					moneyLabel.setText(String.valueOf(totalMoney));

					try {
						if (troopThree.get(troopThreeCurrent).getX() - 1200 > -15 && troopThree.get(troopThreeCurrent).getX()- 1200 < 15) {
							troopThree.remove(troopThree.size()-1);
							troopThreeCurrent = troopThreeCurrent - 1;
							System.out.println("Troop Three Removed");


							compBaseHealth = compBaseHealth - troopThreeDamage;

							baseHouseComLabel.setText("<html>Comp Base<br>"
									+ "Health: " + compBaseHealth);

							System.out.println("Troop Three Remove: " + compBaseHealth);
						}
					}
					catch (IndexOutOfBoundsException io) {

					}
				}

			}

			for (int i = 0; i < troopOneCurrentAI + 1; i ++) {
				if (troopOneCurrentAI > -1) {
					troopOneAI.get(i).draw(g);

					moneyLabel.setText(String.valueOf(totalMoney));

					//moneyLabel.setText("" + totalMoney);

					if (troopOneAI.get(i).getX() - 0 > -15 && troopOneAI.get(i).getX()- 0 < 15) {
						troopOneAI.remove(i);
						troopOneCurrentAI = troopOneCurrentAI - 1;
						System.out.println("Troop OneAI Removed");


						userBaseHealth = userBaseHealth - troopOneDamage;

						baseHealthUserLabel.setText("<html>User Base<br>"
								+ "Health: " + userBaseHealth);

						System.out.println("Troop OneAI Remove: " + userBaseHealth);
					}

					else {
						try {
							for (int k = 0; k < troopOne.size(); k ++) {
								//this k loop works
								if (troopOneAI.get(i).getX() - troopOne.get(k).getX() >-10 && troopOneAI.get(i).getX() - troopOne.get(k).getX() < 10) {
									int troopOneNum = ((int) (Math.random() * 50) + 1);
									int troopOneNumAI = ((int) (Math.random() * 50) + 1);

									if (troopOneNum > troopOneNumAI) { 
										troopOneAI.remove(i);
										troopOneCurrentAI = troopOneCurrentAI -1;
										System.out.println("Troop One Hit Troop OneAI-removed");
										totalMoney = totalMoney + TROOP_ONE_MONEY;
										moneyLabel.setText(String.valueOf(totalMoney));
									}
									else if (troopOneNum < troopOneNumAI) {
										troopOne.remove(k);
										troopOneCurrent = troopOneCurrent -1;
										System.out.println("Troop One-removed Hit Troop OneAI");
									}
								}
							}
						}
						catch (IndexOutOfBoundsException e) {
							System.out.println("Troop One Enemy");
						}

						try {
							for (int k = 0; k < troopTwo.size(); k ++) {
								//this k loop works
								if (troopOneAI.get(i).getX() - troopTwo.get(k).getX() >-10 && troopOneAI.get(i).getX() - troopTwo.get(k).getX() < 10) {
									int troopTwoNum = ((int) (Math.random() * 75) + 1);
									int troopOneNumAI = ((int) (Math.random() * 25) + 1);

									if (troopTwoNum > troopOneNumAI) {
										troopOneAI.remove(i);
										troopOneCurrentAI = troopOneCurrentAI -1;
										System.out.println("Troop Two Hit Troop OneAI");
										totalMoney = totalMoney + TROOP_ONE_MONEY;
										moneyLabel.setText(String.valueOf(totalMoney));
									}
									else if (troopTwoNum < troopOneNumAI){
										troopTwo.remove(k);
										troopTwoCurrent = troopTwoCurrent - 1;
										System.out.println("Troop Two-removed Hit Troop OneAI");
									}

								}
							}
						}
						catch (IndexOutOfBoundsException e) {
							System.out.println("Troop Two Enemy");
						}

						try {

							for (int k = 0; k < troopThree.size(); k ++) {
								//something is wrong with troop three
								if (troopOneAI.get(i).getX() - troopThree.get(k).getX() >-20 && troopOneAI.get(i).getX() - troopThree.get(k).getX() < 20) {
									int troopThreeNum = ((int) (Math.random() * 90) + 1);
									int troopOneNumAI = ((int) (Math.random() * 10) + 1);

									if (troopThreeNum > troopOneNumAI) {
										troopOneAI.remove(i);
										troopOneCurrentAI = troopOneCurrentAI -1;
										System.out.println("Troop Three Hit Troop OneAI");
										totalMoney = totalMoney + TROOP_ONE_MONEY;
										moneyLabel.setText(String.valueOf(totalMoney));
									}
									else if (troopThreeNum < troopOneNumAI){ {
										troopThree.remove(k);
										troopThreeCurrent = troopThreeCurrent - 1;
										System.out.println("Troop Three-removed Hit Troop OneAI");
									}
									}
								}
							}
						}
						catch (IndexOutOfBoundsException e) {
							System.out.println("Troop Three Enemy");
						}

					}
				}
			}

			for (int i = 0; i < troopTwoCurrentAI + 1; i ++) {
				if (troopTwoCurrentAI > -1) {
					troopTwoAI.get(i).draw(g);

					moneyLabel.setText(String.valueOf(totalMoney));

					if (troopTwoAI.get(i).getX() - 0 > -15 && troopTwoAI.get(i).getX()- 0 < 15) {
						troopTwoAI.remove(i);
						troopTwoCurrentAI = troopTwoCurrentAI - 1;
						System.out.println("Troop TwoAI Removed");


						userBaseHealth = userBaseHealth - troopTwoDamage;

						baseHealthUserLabel.setText("<html>User Base<br>"
								+ "Health: " + userBaseHealth);

						System.out.println("Troop TwoAI Remove: " + userBaseHealth);
					}
					else {

						try {
							for (int k = 0; k <troopOne.size(); k++) {
								//this k loop works
								if (troopTwoAI.get(i).getX() - troopOne.get(k).getX() >-10 && troopTwoAI.get(i).getX() - troopOne.get(k).getX() < 10) {
									int troopOneNum = ((int)(Math.random() * 25) + 1);
									int troopTwoNumAI = ((int)(Math.random() * 75) + 1);

									if (troopOneNum > troopTwoNumAI) {
										troopTwoAI.remove(i);
										troopTwoCurrentAI = troopTwoCurrentAI -1;
										System.out.println("Troop TwoAI Hit");
										totalMoney = totalMoney + TROOP_TWO_MONEY;
										moneyLabel.setText(String.valueOf(totalMoney));
									}

									else if (troopOneNum < troopTwoNumAI) {
										troopOne.remove(k);
										troopOneCurrent = troopOneCurrent -1;
										System.out.println("Troop One Hit");
									}
								}
							}
						}
						catch (IndexOutOfBoundsException e) {
							System.out.println("Troop Two Enemy");
						}



						try {
							for (int k = 0; k <troopTwo.size(); k++) {
								//this k loop works
								if (troopTwoAI.get(i).getX() - troopTwo.get(k).getX() >-10 && troopTwoAI.get(i).getX() - troopTwo.get(k).getX() < 10) {
									int troopTwoNum = ((int)(Math.random() * 50) + 1);
									int troopTwoNumAI = ((int)(Math.random() * 50) + 1);

									if (troopTwoNum > troopTwoNumAI) {
										troopTwoAI.remove(i);
										troopTwoCurrentAI = troopTwoCurrentAI -1;
										System.out.println("Troop TwoAI Hit");
										totalMoney = totalMoney + TROOP_TWO_MONEY;
										moneyLabel.setText(String.valueOf(totalMoney));
									}

									else if (troopTwoNum < troopTwoNumAI) {
										troopTwo.remove(k);
										troopTwoCurrent = troopTwoCurrent -1;
										System.out.println("Troop Two Hit");
									}
								}
							}
						}
						catch (IndexOutOfBoundsException e) {
							System.out.println("Troop Two Enemy");
						}

						try {
							for (int k = 0; k <troopThree.size(); k++) {
								//something is wrong with troop three
								if (troopTwoAI.get(i).getX() - troopThree.get(k).getX() >-20 && troopTwoAI.get(i).getX() - troopThree.get(k).getX() < 20) {
									int troopThreeNum = ((int)(Math.random() * 75) + 1);
									int troopTwoNumAI = ((int)(Math.random() * 25) + 1);

									if (troopThreeNum > troopTwoNumAI) {
										troopTwoAI.remove(i);
										troopTwoCurrentAI = troopTwoCurrentAI -1;
										System.out.println("Troop TwoAI Hit");
										totalMoney = totalMoney + TROOP_TWO_MONEY;
										moneyLabel.setText(String.valueOf(totalMoney));
									}

									else if (troopThreeNum < troopTwoNumAI) {
										troopThree.remove(k);
										troopThreeCurrent = troopThreeCurrent -1;
										System.out.println("Troop Three Hit");
									}
								}
							}
						}
						catch (IndexOutOfBoundsException e) {
							System.out.println("Troop Two Enemy");
						}



					}
				}

			}
			for (int i = 0; i < troopThreeCurrentAI + 1; i ++) {
				if (troopThreeCurrentAI > -1) {
					troopThreeAI.get(i).draw(g);

					moneyLabel.setText(String.valueOf(totalMoney));

					if (troopThreeAI.get(i).getX() - 0 >-15 && troopThreeAI.get(i).getX() - 0 < 15) {
						troopThreeAI.remove(i);
						troopThreeCurrentAI = troopThreeCurrentAI -1;
						System.out.println("Troop ThreeAI Removed");

						userBaseHealth = userBaseHealth - troopThreeDamage;

						baseHealthUserLabel.setText("<html>User Base<br>"
								+ "Health: " + userBaseHealth);
						System.out.println("Troop ThreeAI Remove: " + userBaseHealth);

					}
					else {

						try {
							for (int k = 0; k < troopOne.size(); k++) {
								if (troopThreeAI.get(i).getX() - troopOne.get(k).getX() >-10 && troopThreeAI.get(i).getX() - troopOne.get(k).getX() < 10) {
									int troopOneNum = ((int)(Math.random() * 10) + 1);
									int troopThreeNumAI = ((int)(Math.random() * 90) + 1);

									if (troopOneNum > troopThreeNumAI) {
										troopThreeAI.remove(i);
										troopThreeCurrentAI = troopThreeCurrentAI -1;
										System.out.println("Troop ThreeAI Hit");
										totalMoney = totalMoney + TROOP_THREE_MONEY;
										moneyLabel.setText(String.valueOf(totalMoney));
									}
									else if (troopOneNum < troopThreeNumAI) {
										troopOne.remove(k);
										troopOneCurrent = troopOneCurrent - 1;
										System.out.println("Troop One Hit");

									}
								}
							}
						}
						catch (IndexOutOfBoundsException e) {
							System.out.println("Troop Three Hit Enemy");
						}


						try {
							for (int k = 0; k < troopTwo.size(); k++) {
								if (troopThreeAI.get(i).getX() - troopTwo.get(k).getX() >-10 && troopThreeAI.get(i).getX() - troopTwo.get(k).getX() < 10) {
									int troopTwoNum = ((int)(Math.random() * 25) + 1);
									int troopThreeNumAI = ((int)(Math.random() * 75) + 1);

									if (troopTwoNum > troopThreeNumAI) {
										troopThreeAI.remove(i);
										troopThreeCurrentAI = troopThreeCurrentAI -1;
										System.out.println("Troop ThreeAI Hit");
										totalMoney = totalMoney + TROOP_THREE_MONEY;
										moneyLabel.setText(String.valueOf(totalMoney));
									}
									else if (troopTwoNum < troopThreeNumAI) {
										troopTwo.remove(k);
										troopTwoCurrent = troopTwoCurrent - 1;
										System.out.println("Troop Two Hit");

									}
								}
							}
						}
						catch (IndexOutOfBoundsException e) {
							System.out.println("Troop Three Hit Enemy");
						}

						try {
							for (int k = 0; k < troopThree.size(); k++) {
								if (troopThreeAI.get(i).getX() - troopThree.get(k).getX() >-10 && troopThreeAI.get(i).getX() - troopThree.get(k).getX() < 10) {
									int troopThreeNum = ((int)(Math.random() * 50) + 1);
									int troopThreeNumAI = ((int)(Math.random() * 50) + 1);

									if (troopThreeNum > troopThreeNumAI) {
										troopThreeAI.remove(i);
										troopThreeCurrentAI = troopThreeCurrentAI -1;
										System.out.println("Troop ThreeAI Hit");
										totalMoney = totalMoney + TROOP_THREE_MONEY;
										moneyLabel.setText(String.valueOf(totalMoney));
									}
									else if (troopThreeNum < troopThreeNumAI) {
										troopThree.remove(k);
										troopThreeCurrent = troopThreeCurrent - 1;
										System.out.println("Troop Three Hit");

									}
								}
							}
						}
						catch (IndexOutOfBoundsException e) {
							System.out.println("Troop Three Hit Enemy");
						}

					}

				}

			}



			moneyLabel.setText(String.valueOf(totalMoney));


			if (compBaseHealth < 1 || userBaseHealth < 1) {
				pauseGame = true;

				baseHouseComPanel.setBounds(300, 0, 500, 100);
				baseHealthUserPanel.setBounds(300, 100, 500, 100);


				baseHouseComLabel.setFont(new Font("Arial", Font.PLAIN, 40));

				baseHealthUserLabel.setFont(new Font("Arial", Font.PLAIN, 40));
				if (compBaseHealth < 1) {
					baseHouseComLabel.setText("<html>Comp Base<br>"
							+ "Health: " + "0");
					compBaseHealth = 0;
				}
				if (userBaseHealth < 1) {
					baseHealthUserLabel.setText("<html>User Base<br>"
							+ "Health: " + "0");
					userBaseHealth = 0;
				}

				JPanel namePanel = new JPanel();
				namePanel.setBounds(300, 300, 500, 50);
				add(namePanel);





				JTextField nameText = new JTextField();
				nameText.setBounds(300, 300, 300, 50);
				nameText.setPreferredSize(new Dimension(300,35));
				namePanel.add(nameText);

				JButton nameSubmit;
				nameSubmit = new JButton();
				nameSubmit.setBounds(350, 300, 50, 50);
				nameSubmit.setText("Enter");
				namePanel.add(nameSubmit);

				playAgainPanel = new JPanel();
				playAgainPanel.setBounds(400, 450, 300, 100);


				add(playAgainPanel);
				playAgainPanel.setVisible(true);


				playAgainBTN = new JButton();
				playAgainBTN.setPreferredSize(new Dimension(150,50));
				playAgainBTN.setBounds(400, 450, 150, 45);
				playAgainBTN.setEnabled(false);
				playAgainBTN.setText("Play Again");
				playAgainPanel.add(playAgainBTN);

				playAgainBTN.update(g);




				quitBTN = new JButton();
				quitBTN.setPreferredSize(new Dimension(150,50));
				quitBTN.setBounds(400, 450, 150, 45);
				quitBTN.setEnabled(false);
				quitBTN.setText("Quit Game");
				playAgainPanel.add(quitBTN);
				quitBTN.update(g);

				nameSubmit.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ae){
						String textFieldValue = nameText.getText();
						nameSubmit.setEnabled(false);

						playAgainBTN.setEnabled(true);
						quitBTN.setEnabled(true);

						System.out.println(textFieldValue);
						try {
							File newTextFile = new File("src/gameProject/userNameTextFile");

							//FileWriter fw = new FileWriter(newTextFile);
							PrintWriter fw = new PrintWriter(new FileWriter(newTextFile, true));
							fw.write(textFieldValue + ", Comp Base: " + compBaseHealth + ", User Base: " + userBaseHealth +"\n");
							fw.close();


						} catch (IOException iox) {
							//do stuff with exception
							iox.printStackTrace();
						}
						// .... do some operation on value ...

						quitBTN.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent i){
								System.exit(0);
							}
						});

						playAgainBTN.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent i){
								troopOneCurrent = -1;
								troopTwoCurrent = -1;
								troopThreeCurrent = -1;

								troopOneCurrentAI = -1;
								troopTwoCurrentAI = -1;
								troopThreeCurrentAI = -1;

								turretOneClicked = false;
								turretTwoClicked = false;
								turretThreeClicked = false;

								turretOneActive = false;
								turretTwoActive = false;
								turretThreeActive = false;

								turretActive = false;
								turretRockCurrent = -1;


								userBaseHealth = 100;
								compBaseHealth = 100;

								turret1BTN.setEnabled(true);
								turret2BTN.setEnabled(true);
								turret3BTN.setEnabled(true);

								troop1Panel.setVisible(true);
								troop1BTN.setVisible(true);
								troop2Panel.setVisible(true);
								troop2BTN.setVisible(true);
								troop3Panel.setVisible(true);
								troop3BTN.setVisible(true);
								turret1Panel.setVisible(true);
								turret2Panel.setVisible(true);
								turret3Panel.setVisible(true);
								turretOneLabel.setVisible(false);
								turretTwoLabel.setVisible(false);
								turretThreeLabel.setVisible(false);
								totalMoney = 200;


								AICharacterChoice = ((int) (Math.random() * 11) + 1);

								timer.start();
								//timer2.start();
								//timer3.start();
								//timer4.start();

								remove(namePanel);
								namePanel.remove(nameText);
								namePanel.remove(nameSubmit);
								remove(playAgainPanel);
								playAgainPanel.remove(playAgainBTN);
								playAgainPanel.remove(quitBTN);

								//back to orginal
								baseHouseComPanel.setBounds(1100, 50, 85, 50);
								baseHealthUserPanel.setBounds(1100, 0, 85, 50);

								baseHouseComLabel.setFont(new Font("Arial", Font.PLAIN, 15));

								baseHealthUserLabel.setFont(new Font("Arial", Font.PLAIN, 15));


								pauseGame = false;


							}
						});
					}
				});


				try {

					try {
						timer.stop();
					}
					catch (NullPointerException w) {

					}

					try {
						timer2.stop();
					}
					catch (NullPointerException w) {

					}
					try {
						timer3.stop();
					}
					catch (NullPointerException w) {

					}

					try {
						timer4.stop();
					}
					catch (NullPointerException w) {

					}


					turret1BTN.setEnabled(false);
					turret2BTN.setEnabled(false);
					turret3BTN.setEnabled(false);

					troop1Panel.setVisible(false);
					troop1BTN.setVisible(false);
					troop2Panel.setVisible(false);
					troop2BTN.setVisible(false);
					troop3Panel.setVisible(false);
					troop3BTN.setVisible(false);
					turret1Panel.setVisible(false);
					turret2Panel.setVisible(false);
					turret3Panel.setVisible(false);
					turretOneLabel.setVisible(false);
					turretTwoLabel.setVisible(false);
					turretThreeLabel.setVisible(false);




					//stop all the movement
					for (int h = 0; h < troopOneCurrent + 1; h++) {
						troopOne.remove(h);
					}
					for (int h = 0; h < troopTwoCurrent + 1; h++) {
						troopTwo.remove(h);
					}
					for (int h = 0; h < troopThreeCurrent + 1; h++) {
						troopThree.remove(h);
					}
					for (int h = 0; h < troopOneCurrentAI + 1; h++) {
						troopOneAI.remove(h);
					}
					for (int h = 0; h < troopTwoCurrentAI + 1; h++) {
						troopTwoAI.remove(h);
					}
					for (int h = 0; h < troopThreeCurrentAI + 1; h++) {
						troopThreeAI.remove(h);
					}
					for (int h = 0; h < turretRockCurrent + 1; h++) {
						turretRock.remove(h);
					}
					troopOneCurrentAI = -1;
					troopTwoCurrentAI = -1;
					troopThreeCurrentAI = -1;



				}
				catch (IndexOutOfBoundsException ip) {
					System.out.println("Clear the screen");
				}
			}

		}
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