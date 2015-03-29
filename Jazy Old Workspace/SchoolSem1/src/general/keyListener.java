package general;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyListener implements KeyListener,ActionListener{
	static boolean busy=false;
	static musicPlayer musicplayer=null;
	public void keyPressed(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ENTER){
			mainClass.input.setText(mainClass.input.getText().substring(0,mainClass.input.getText().length()-1));
			mainClass.te = mainClass.input.getText();
			mainClass.println(mainClass.input.getText());
			if ((mainClass.frame.getName().equals("Main Menu"))&&(!busy)){ //Menu *Hidden* commands (Mostly for debug)
				busy=true;
				Thread the = new Thread(new Runnable(){
				public void run(){
					String in = mainClass.te.toLowerCase();
					if (in.equals("test")){
						mainClass.println("::Next Line::");
						mainClass.println(":"+mainClass.nextLine());
						mainClass.println("::Next::");
						mainClass.println(":"+mainClass.next());
						mainClass.println("::Next Int::");
						mainClass.printf(":%d\n",mainClass.nextInt());
						mainClass.println("::Next Double::");
						mainClass.printf(":%f\n",mainClass.nextDouble());
					} else if (in.equals("music")){
						if (musicplayer==null) musicplayer = new musicPlayer();
						mainClass.println("Playing Music *BETA*");
						busy=false;
						mainClass.println("Playing!");
						musicplayer.play(null);
					} else if ((in.equals("next"))&&(musicPlayer.playing)){
						musicplayer.stop();
						try{Thread.sleep(500);}catch(Exception e){}
						musicplayer.play(null);
					} else if ((in.equals("stop"))&&(musicPlayer.playing)){
						mainClass.println("Stopping...");
						musicplayer.stop();
						musicplayer = null;
					}
				}
				});
				the.start();
				busy=false;
			}
			mainClass.input.setText("");
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
	public static void wait (int n){
		long time=System.currentTimeMillis();
        long tnow,tto;
        tnow=time;
        tto=time+(n);
        while (tto>tnow){
        	tnow=System.currentTimeMillis();;
        }
	}
	
	public void actionPerformed(ActionEvent e) { //Run the classes
		if (e==null||e.getActionCommand()==null) return; //Habit from LUA :D
		if (mainClass.debug) System.out.println("(keyListeners) modifiers: "+e.getModifiers());
		if (mainClass.debug) mainClass.println("Action recieved...");
		if (e.getModifiers()==17) {
			if (mainClass.debug) {
				mainClass.debug=false;
				mainClass.println("Debug off...");
			}
			else {
				mainClass.debug=true;
				mainClass.println("Debug on...");
				}
			
			return;
		}
		
		if (e.getActionCommand().equals("reset")){
			mainClass.reset();
			return;
		}
		
		String ch = e.getActionCommand();
		for (int i=0;i<mainClass.classes.length;i++){ 
			if(ch.equals(mainClass.classes[i])){
				if (mainClass.debug) mainClass.println("Class ran?...");
				mainClass.runClazz(i);
			}
		}
	}
}
