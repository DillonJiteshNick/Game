package gameProject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AITroopTwo extends MovingObject
{

		private BufferedImage image;

		//Constructor that sets the location of the AITroopTwo- the MovingObject object- from the super class
	    public AITroopTwo(double x, double y, int left, int right, int top, int bottom) {
	    	super(x, y, left + 10, right - 10, top + 10, bottom - 10);
	    	//Set the image from the selected file
	       try {                
	          image = ImageIO.read(new File("src/TroopTwoGoodReversed.png"));
	       } catch (IOException ex) {
	            System.out.println("Error with Image");
	       }
	       
	       
	    
	    }

	    //Draw the image- getting the X and Y coordinates
		
	    /**
	     * 
	     * Draw the troop
	     */
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
