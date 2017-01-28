package gameProject;


/**
 * 
 * @author nicholasdietrich
 * @version 5- January 25, 2017
 * ISU4U Final Culminating Assignment
 * 
 * This is the GamePanel class- it contains the most of the code and should be run from
 * This class contains most of the timers and the variables used in the game
 * It creates the main JFrame and paints the objects that are added to it
 * 
 * The instructions are in the text file called 'Instructions'
 * 
 */

//Imports
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable{

	//Global variables
	public static int AICharacterChoice = ((int) (Math.random() * 11) + 1);

	Timer timer;
	Timer timer2;
	Timer timer3;
	Timer timer4;

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

	public JLabel turretOneLabel;
	public JLabel turretTwoLabel;
	public JLabel turretThreeLabel;
	public JLabel refreshTurretLabel;
	public static JLabel moneyLabel;
	public static JPanel moneyPanel;

	public static int totalMoney = 250;
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



	int width = 1350;
	int height = 700;


	final int pauseDuration = 50;

	//Making the arralists of the troop objetcs
	ArrayList<TroopOne> troopOne = new ArrayList<TroopOne>();

	ArrayList<TroopTwo> troopTwo = new ArrayList<TroopTwo>();

	ArrayList<TroopThree> troopThree = new ArrayList<TroopThree>();

	ArrayList<AITroopOne> troopOneAI = new ArrayList<AITroopOne>();
	ArrayList<AITroopTwo> troopTwoAI = new ArrayList<AITroopTwo>();
	ArrayList<AITroopThree> troopThreeAI = new ArrayList<AITroopThree>();



	//Making the array list of the turret rock shooter object
	ArrayList<turretShooter> turretRock = new ArrayList<turretShooter>();


	private BufferedImage castleImageLeft;
	private BufferedImage castleImageRight;

	private BufferedImage turretOneImage;

	private BufferedImage turretTwoImage;

	private BufferedImage turretThreeImage;

	public BufferedImage refreshTurretImage;

	public JPanel baseHouseComPanel;
	public JLabel baseHouseComLabel;

	public JPanel baseHealthUserPanel;
	public JLabel baseHealthUserLabel;

	public	JPanel troop1Panel;
	public 	JButton troop1BTN;
	public	JPanel troop2Panel;
	public JButton troop2BTN;
	public JPanel troop3Panel;
	public JButton troop3BTN;

	public JPanel turret1Panel;
	public JPanel turret2Panel;
	public JPanel turret3Panel;

	public JPanel playAgainPanel;
	public JButton playAgainBTN;
	public JButton quitBTN;

	public JPanel startBTNPanel;
	public JPanel startBTN;
	public Clip music = null;



	/**
	 * 
	 * Main method where the game frame is made
	 * @param args
	 */
	public static void main(String[] args) {

		//making the game frame and setting its dimensions

		JFrame frame = new JFrame("Endless War");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		frame.setSize(new Dimension(1200, 600));
		frame.setAutoRequestFocus(false);
		frame.setVisible(true);
		Container c = frame.getContentPane();
		c.add(new GamePanel());
		frame.pack();




	}

	/**
	 * 
	 * GamePanel constructor- contains the audio, the timers, the button clicks, the labels, and most of the code
	 */

	public GamePanel(){

		//Background music
		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File("src/Medieval_Music_-_Medieval_Travelers.wav"));
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			music = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			music.open(audioInputStream);
		} catch (LineUnavailableException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		music.loop(Clip.LOOP_CONTINUOUSLY);
		music.start();


		//Money panel- where the money is labeled and set in the top right corner

		moneyPanel = new JPanel();
		moneyPanel.setBounds(875, 0, 100, 100);

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


		//Base house and user house- health labels set and coloured
		baseHouseComPanel = new JPanel();
		baseHouseComPanel.setBounds(1100, 50, 85, 50);

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

		baseHealthUserPanel.setBackground(Color.LIGHT_GRAY);
		add(baseHealthUserPanel);

		baseHealthUserLabel = new JLabel();
		baseHealthUserLabel.setBounds(1100, 0, 85, 50);
		setLayout(null);
		baseHealthUserLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		baseHealthUserPanel.add(baseHealthUserLabel);
		baseHealthUserLabel.setText("<html>User Base<br>"
				+ "Health: " + userBaseHealth);




		//Bases are added to the game field

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



		//Cursor is changed to the cross-haired one
		Cursor cursor = Cursor.getDefaultCursor();


		cursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR); 
		setCursor(cursor);

		//Main timer for the AI troop spawn- it fires and adds a new random AI troop to the field
		timer = new Timer(3000, new ActionListener() {
			@Override
			/**
			 * 
			 * Timer action listener
			 */
			public void actionPerformed(ActionEvent e) {
				if (pauseGame == true) {

				}
				else {

					//Setting the odds for the type of AI troop
					AICharacterChoice = ((int) (Math.random() * 11) + 1);

					if (AICharacterChoice <=4) {
						//Makes AI troop One appear- initializes and makes a new troopOneAI- adds it to the array list
						//Same for all of the other AI troops added- their speeds and the curret AI is set
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
		//Timer is started
		timer.start();



		//Start panel and button for the introduction screen
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
			/**
			 * When clicked the button clears itself and makes the game active- along with re-adding the panels that
			 * were removed
			 */
			public void actionPerformed(ActionEvent e) {
				add(baseHouseComPanel);
				add(baseHealthUserPanel);
				add(moneyPanel);
				remove(startBTNPanel);
				timer.start();
			}
		});









		this.setPreferredSize(new Dimension(1200, 700));
		this.setBackground(Color.WHITE);



		//Making the troop panels and butons that are clicked on to add a troop

		troop1Panel=new JPanel();
		troop1Panel.setBounds(75, 0, 100, 100);
		add(troop1Panel);



		troop1BTN = new JButton ("<html>Troop 1<br>" + "5 Coins");
		troop1BTN.setFont(new Font("Arial", Font.PLAIN, 12));
		setLayout(null);
		troop1BTN.setPreferredSize(new Dimension(100, 100));


		troop1Panel.add(troop1BTN);



		//Like for all troops when clicked a new one is added to its array list and initialized- the current number
		//is increased- but the user must have enough money for this to occur

		troop1BTN.addActionListener(new ActionListener() {
			/**
			 * 
			 * When pressed the new troop is added to teh game field
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				if (pauseGame == true) {

				}
				else {
					if (totalMoney < 5) {

					}
					else {
						troop1BTN.setEnabled(true);


						totalMoney = totalMoney - TROOP_ONE_MONEY;

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



		//Same as troop one
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


		//Same as troop one
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




		//Turret panels- adds the specific turret to the game field based on the button pressed
		//and if enough money is available
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
			/**
			 * 
			 * Just like the troop- the turret image is added to the field
			 */
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

						//This is the timer that adds the turret rocks to the array list and fires them across the field
						timer2 = new Timer(4000, new ActionListener() {
							@Override
							/**
							 * 
							 * Fires every four seconds and increases the value of the current turretRock
							 */
							public void actionPerformed(ActionEvent ie) {

								turretRock.add(new turretShooter(20, 90, 0, width, 0, height));
								turretRock.get(turretRock.size()-1).setXSpeed(10);
								turretRock.get(turretRock.size()-1).setYSpeed(2);
								turretRockCurrent++;
								//Destroys comp base health by 1 each fire
								compBaseHealth = compBaseHealth - 1;

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




		//Same as turret one

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

						//Same as turret one rock- but fires every two seconds and is more money to add
						timer3 = new Timer(2000, new ActionListener() {
							@Override
							/**
							 * 
							 * timer for turret two
							 */
							public void actionPerformed(ActionEvent ie) {

								turretRock.add(new turretShooter(20, 90, 0, width, 0, height));
								turretRock.get(turretRock.size()-1).setXSpeed(10);
								turretRock.get(turretRock.size()-1).setYSpeed(2);
								turretRockCurrent++;
								compBaseHealth = compBaseHealth - 1;

								System.out.println("Rock Fire");
							}
						});
						timer3.start();

						turretActive = true;

						turretTwoClicked= true;

						turretTwoLabel.setVisible(true);

						if (turretOneClicked ) {
							turretOneLabel.setVisible(false);

						}
						if (turretThreeClicked) {
							turretThreeLabel.setVisible(false);
						}

						System.out.println("Turret 2 Clicked");
					}
				}
			}
		});





		//Same as turret one
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


						//same as the other turrets- just fires every one second and is more expensive to purchase
						timer4 = new Timer(1000, new ActionListener() {
							@Override
							/**
							 * timer for turret three
							 */
							public void actionPerformed(ActionEvent ie) {

								turretRock.add(new turretShooter(20, 90, 0, width, 0, height));
								turretRock.get(turretRock.size()-1).setXSpeed(10);
								turretRock.get(turretRock.size()-1).setYSpeed(2);
								turretRockCurrent++;
								compBaseHealth = compBaseHealth - 1;

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


						System.out.println("Turret 3 Clicked");
					}
				}
			}
		});




		//Ensure game frame sizing

		this.setPreferredSize(new Dimension(1200, 600));



		//Start the game thread
		Thread gameThread = new Thread(this);
		gameThread.start();

		//Mouse listener- for debugging purposes
		addMouseListener(new MouseAdapter() { 
			public void mouseClicked(MouseEvent e) { 	
				System.out.println("Image Hit");

				System.out.println("X Click: " + e.getX() + " Y Click: " + e.getY());


			}
		});


	}

	//Paint component- this is where all the added troops or turret rocks are actually drawn on teh game frame

	@SuppressWarnings("deprecation")
	/**
	 * Paint component
	 * 
	 */
	public void paintComponent(Graphics g){

		super.paintComponent(g);
		//Ensure the game is active- for when the game ends
		if (pauseGame == true) {

		}
		else {


			//Draw the base of the frame- the lines taht separate compartments and the labels

			g.drawImage(castleImageLeft, -75, 180, null);
			g.drawImage(castleImageRight, 1070, 180, null);



			g.drawRect(0, 0, 1200, 100);


			g.drawString("Units:", 10, 20);
			g.drawString("Turrets:", 400, 20);
			g.drawString("Money:", 800, 20);
			g.drawString("Base Health:", 1000, 20);



			//Labels for the user and comp base health
			
			baseHealthUserLabel.setText("<html>User Base<br>"
					+ "Health: " + userBaseHealth);


			baseHouseComLabel.setText("<html>Comp Base<br>"
					+ "Health: " + compBaseHealth);




			//Draws the turret rocks- this is what accesses the current value and draws it on the screen
			for (int i = 0; i < turretRockCurrent + 1; i++) {
				try {
					turretRock.get(i).draw(g);
					moneyLabel.setText(String.valueOf(totalMoney));

				}
				catch (IndexOutOfBoundsException n) {
					System.out.println("Turret Rock Shoot");
				}

			
			}


			//Draws troop one on the screen- this just recognizes what the speed is- it just draws the troop
			for (int i = 0; i < troopOneCurrent + 1; i ++) {
				//Ensure that there is actually a current troop to draw
				if (troopOneCurrent > -1) {
					troopOne.get(i).draw(g);

					moneyLabel.setText(String.valueOf(totalMoney));

					System.out.println(totalMoney);


					//Removes the troop when it hits the comp base- so the current number is low, doesn't waste space
					if (troopOne.get(troopOneCurrent).getX() - 1200 > -15 && troopOne.get(troopOneCurrent).getX()- 1200 < 15) {


						troopOne.remove(troopOneCurrent);
						troopOneCurrent = troopOneCurrent - 1;
						compBaseHealth = compBaseHealth - troopOneDamage;

						if (compBaseHealth == 0) {
							//For debugging purposes
							System.out.println("Comp Base 0");
						}






						System.out.println("Troop One Removed: " + compBaseHealth);
					}

				}


			}


			//Draws troop two on the screen- this just recognizes what the speed is- it just draws the troop
			//This is the same as troop one- just for troop two
			for (int i = 0; i < troopTwoCurrent + 1; i ++) {
				if (troopTwoCurrent > -1) {
					troopTwo.get(i).draw(g);

					moneyLabel.setText(String.valueOf(totalMoney));

					


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

			//Draws troop three on the screen- this just recognizes what the speed is- it just draws the troop
			//This is the same as troop one and two- just for troop three
			for (int i = 0; i < troopThreeCurrent + 1; i ++) {
				if (troopThreeCurrent > -1) {
					try {
						troopThree.get(i).draw(g);
					}
					catch (IndexOutOfBoundsException io) {

					}

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

			
			//Like the user's troops- this draws the AI troops on the game field
			//Draws troop one AI on the screen- this just recognizes what the speed is- it just draws the troop
			for (int i = 0; i < troopOneCurrentAI + 1; i ++) {
				if (troopOneCurrentAI > -1) {
					troopOneAI.get(i).draw(g);

					moneyLabel.setText(String.valueOf(totalMoney));

					

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
								
								//Checks for collisions with the user's troops- and acts on it
								
								if (troopOneAI.get(i).getX() - troopOne.get(k).getX() >-10 && troopOneAI.get(i).getX() - troopOne.get(k).getX() < 10) {
									int troopOneNum = ((int) (Math.random() * 50) + 1);
									int troopOneNumAI = ((int) (Math.random() * 50) + 1);

									//sets a random number for each troop- corresponding to their value
									//either removes the user of AI troop
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
								
								//sets a random number for each troop- corresponding to their value
								//either removes the user of AI troop
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
								//sets a random number for each troop- corresponding to their value
								//either removes the user of AI troop
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

			//Same as troop one AI- just for troop two
			for (int i = 0; i < troopTwoCurrentAI + 1; i ++) {
				if (troopTwoCurrentAI > -1) {
					//Updates the money and teh base values
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
								//sets a random number for each troop- corresponding to their value
								//either removes the user of AI troop
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
								//sets a random number for each troop- corresponding to their value
								//either removes the user of AI troop
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
								//sets a random number for each troop- corresponding to their value
								//either removes the user of AI troop
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
			
			//Same as troop one and two- but for troop three
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
							//sets a random number for each troop- corresponding to their value
							//either removes the user of AI troop
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
							//sets a random number for each troop- corresponding to their value
							//either removes the user of AI troop
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
							//sets a random number for each troop- corresponding to their value
							//either removes the user of AI troop
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


			//Ensure the labels and the text are updated- when the paint of the troop or rocks are not drawing

			moneyLabel.setText(String.valueOf(totalMoney));


			//Game over part- when either base reaches a health of 0
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

				
				//JPanel and JButtons for the name submit, play agian, or quit part
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
				playAgainBTN.setEnabled(true);
				quitBTN.setEnabled(true);

				nameSubmit.addActionListener(new ActionListener(){
					/**
					 * When the name submit button is pressed
					 */
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

						//Quit game
						quitBTN.addActionListener(new ActionListener(){
							/**
							 * 
							 * Quit game action performed
							 */
							public void actionPerformed(ActionEvent i){
								System.exit(0);
								music.stop();

							}
						});

						
						//Play again
						playAgainBTN.addActionListener(new ActionListener(){
							/**
							 * 
							 * P{lay again action performed
							 */
							public void actionPerformed(ActionEvent i){
								//reset of variables, labels, and buttons
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
								totalMoney = 250;


								AICharacterChoice = ((int) (Math.random() * 11) + 1);

								//Restart the timer that was stopped when the base reached 0- for the next game
								timer.start();
								
								remove(namePanel);
								namePanel.remove(nameText);
								namePanel.remove(nameSubmit);
								remove(playAgainPanel);
								playAgainPanel.remove(playAgainBTN);
								playAgainPanel.remove(quitBTN);

								
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
					
					//Game over part- stop everything that is moving and make the game over screen- with the text box and name submit- stop the timers

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

	/**
	 * 
	 * run method- the game is run and the thread is repainted- in correspondence to the pause duration
	 */
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
	/**
	 * 
	 * @return
	 */
	public static int[] getPositionArray ()
	{
		return positionArray;
	}

	/**
	 * 
	 * @param positionArray
	 */
	public void setPostionArray(int [] positionArray)
	{
		positionArray = GamePanel.positionArray;
	}



}