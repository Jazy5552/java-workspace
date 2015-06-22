import java.awt.AWTException;

/**
 * 
 * @author Jazy5552
 * Just a simple clock I made
 * for show.
 *
 */
public class mainClass {

	public static void main(String[] args) {
		new mainClass();
	}
	
	public mainClass(){
		new Clock();
		try {
		new RobotSaver();
		} catch (AWTException e) {}
	}

}
