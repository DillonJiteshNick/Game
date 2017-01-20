package gameProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * This is the ImageFaceAppear class that extends moving object and runs from the game panel main class
 * 
 * This class contains the basis data of the "Nack" an ImageFaceAppear object made from this class
 * @author nicholasdietrich
 * @Version 2.0 December 24, 2016
 *
 */
public  class TroopOne2 extends MovingObject2 {
	
	//Make the image that is the Nack
	
	private BufferedImage image;

	//Constructor that sets the location of the Nack- the ImageFaceAppear object- from the super class
    public TroopOne2(double x, double y, int left, int right, int top, int bottom) {
    	super(x, y, left + 10, right - 10, top + 10, bottom - 10);
    	//Set the image from the selected file
       try {                
          image = ImageIO.read(new File("src/Troop1.png"));
       } catch (IOException ex) {
            System.out.println("Error with Image");
       }
       
       
    
    }

    //Draw the image- getting the X and Y coordinates
	
	public void draw(Graphics g) {
		

		g.drawImage(image, (int) getX(), (int) getY(), null);

	}

/**
 * 
 * AnimateOneStep Part
 */
	@Override
	public void animateOneStep() {
		// TODO Auto-generated method stub
		
	}
	
	
}
