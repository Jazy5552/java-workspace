import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Calendar;

import java.awt.MouseInfo;

public class Clock extends JFrame{
	
	private static final long serialVersionUID = 1L;
	//Components
	static JTextField timeD; //THe time display
	static JPanel panel; //Where everything will be
	
	public Clock(){
		super("Clock made by Jazy5552");
		setSize(225,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(true);
		//Set position on mouse
		setLocation(MouseInfo.getPointerInfo().getLocation().x-(getSize().width/2), MouseInfo.getPointerInfo().getLocation().y-(getSize().height/2));
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		timeD = new JTextField(7);
		timeD.setBackground(Color.cyan);
		timeD.setHorizontalAlignment(JTextField.CENTER);
		timeD.setEditable(false);
		timeD.setFont(new Font("Serif",Font.PLAIN,48));
		
		panel.add(timeD);
		
		add(panel); //add the panel to the JFrame
		
		pack();
		
		Timer t = new Timer(1000, new Listener()); //1 second Timer
		t.start();
		
	}
	
	class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			Calendar current = Calendar.getInstance();
			
			int hour = current.get(Calendar.HOUR_OF_DAY);
			int min = current.get(Calendar.MINUTE);
			int sec = current.get(Calendar.SECOND);
			String timeO = "";
			String sero = "",sero2 = "";
			
			//Add the pm/am and remove the military format
			if (hour<12){
				timeO = "AM";
			}else if (hour==12){
				timeO = "PM";
			}else if (hour==24){
				timeO = "AM";
				hour=hour-12;
			}else if (hour>12){
				timeO = "PM";
				hour=hour-12;
			}
			
			//Add a zero before
			if (sec<10){
				sero = "0";
			}
			if (min<10){
				sero2 = "0";
			}
			
			timeD.setText(hour+":"+sero2+min+":"+sero+sec+" "+timeO);
			
		}
	}
	
}
