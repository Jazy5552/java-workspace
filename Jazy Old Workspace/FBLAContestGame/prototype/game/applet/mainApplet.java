package game.applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class mainApplet extends Applet{

	private static final long serialVersionUID = 5552;

	public void paint(Graphics g){ //When this.paint() is called
		g.drawString("Hello World!",25,25);
	}
	
	public void init(){ //Initialize
		
	}

}
