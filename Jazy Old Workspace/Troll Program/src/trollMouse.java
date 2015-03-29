import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * This will steal the mouse away and do fun stuff with it ;)
 * @author Jazy
 *
 */
public class trollMouse {
	
	public void main(String args[]){
		
		BufferedImage myPicture = ImageIO.read(new File("images/1.jpg"));
		JLabel picLabel = new JLabel(new ImageIcon( myPicture ))
		picLabel add( picLabel )
	}
	

}
