import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

public class RobotSaver {
	Robot r = new Robot();
	
	int firstServer = 250;
	int secondServer = 500;
	
	stringToKeyEvents stk = new stringToKeyEvents();
	
	int startText[] = stk.toKeyEvents("say Jazy autoSave running!");
	int startUp[] = stk.toKeyEvents("save-off");
	int save[] = stk.toKeyEvents("save-all");
	int saveText[] = stk.toKeyEvents("say World Saved!");
	int presaveText[] = stk.toKeyEvents("say World Saving!");
	int presaveText2[] = stk.toKeyEvents("say Stand by for lag...");
	
	RobotSaver() throws AWTException{
		
		r.setAutoDelay(100);
		
		r.mouseMove(150, firstServer); //First server
		r.mousePress(InputEvent.BUTTON1_MASK);
		r.mouseRelease(InputEvent.BUTTON1_MASK);
		startup();
		r.mouseMove(150, secondServer); //Second server
		r.mousePress(InputEvent.BUTTON1_MASK);
		r.mouseRelease(InputEvent.BUTTON1_MASK);
		startup();
		
		Timer t = new Timer(60000*120,new saveWorlds());
		t.start();
		
		t = new Timer(60000*30,new printTime());
		t.start();
		
	}
	
	public void startup(){
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		for (int i=0;i<startText.length;i++){
			r.keyPress(startText[i]);
		}
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		for (int i=0;i<startUp.length;i++){
			r.keyPress(startUp[i]);
		}
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		displayTime();
	}
	
	public void saveWorld(){
		for (int i=0;i<presaveText.length;i++){
			r.keyPress(presaveText[i]);
		}
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		for (int i=0;i<presaveText2.length;i++){
			r.keyPress(presaveText2[i]);
		}
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		for (int i=0;i<save.length;i++){
			r.keyPress(save[i]);
		}
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		for (int i=0;i<saveText.length;i++){
			r.keyPress(saveText[i]);
		}
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public void displayTime(){
		String timeText = "say Current time- "+Clock.timeD.getText();
		int[] time = stk.toKeyEvents(timeText);
		for (int i=0;i<time.length;i++){
			r.keyPress(time[i]);
		}
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}
	
	//Classes Below (Threads)

	class saveWorlds implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			r.mouseMove(150, firstServer); //First server
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			saveWorld();
			r.mouseMove(150, secondServer); //Second server
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			saveWorld();
		}
	
	}
	
	class printTime implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			r.mouseMove(150, firstServer); //First server
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			displayTime();
			r.mouseMove(150, secondServer); //Second server
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			displayTime();
		}
	}
	
	
}
