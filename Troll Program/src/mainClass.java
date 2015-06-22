/**
 * My simple harmless Troll virus >:D
 * @author J***
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class mainClass {

	boolean stop = false; //Master Switch
	
	public static void main(String[] args) {
		new mainClass();
	}
	
	mainClass(){//The juice
		
	}
	
	
	//Different Class Below
	class trollKeyboard implements KeyListener,Runnable{
		char[] keystostop = {'j','a','z','y'};

		public void run() {
			
		}

		public void keyPressed(KeyEvent arg0) {
			
		}

		public void keyReleased(KeyEvent arg0) {
			
		}

		public void keyTyped(KeyEvent arg0) {
		}
	}
	
	class trollMouse implements MouseListener,Runnable{
		public void run() {
			
		}

		public void mouseClicked(MouseEvent arg0) {
		}

		public void mouseEntered(MouseEvent arg0) {
		}

		public void mouseExited(MouseEvent arg0) {
		}

		public void mousePressed(MouseEvent arg0) {
		}

		public void mouseReleased(MouseEvent arg0) {
			//Make the NoNoNooo image appear in a wave form
		}
	}
	
	public void nonoImage(double x,double y){
		//Make nono image in a new thread asap!
		//TODO
	}
	
	class trollImage implements Runnable{
		public void run() {
		}
	}
	
	class trollSong implements Runnable{
		public void run(){
			//Development!!
		}
	}

}
