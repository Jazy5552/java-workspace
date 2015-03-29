package game.applet;

import java.applet.Applet;
import java.awt.Event;
import java.awt.Graphics;

public class SampleThread extends Applet implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	Thread t;
	int i;
	String message = "";
	
	public void run() {
		while(true)
		{
	  	repaint();

	  	try {
	    	Thread.sleep(1000/30);
	  	} catch (InterruptedException e) { ; }
		}
	}
	
	public void init(){
		t = new Thread(this);
		t.start();
		
		i=0;
	}
	
	public void paint(Graphics g){ //PAINTER
		g.drawString("Message = "+message,10,20);
	}
	
	public boolean keyDown()
	{
	  message = message + key;
	  if (e.shiftDown()) message = "";
	  return true;
	}
	
	public boolean keyUp(Event e, int key)
	{
	  return true;
	}
}
