/**
 * Author's Comments
 * Free to take, modify, reuse
 * Credit is not demanded but appreciated
 * By: Jazy5552
 * 
 * This is a package .jar file that runs several programs
 * from this main console.
 */

package general;

import bookstore.*;
import garage.*;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class mainClass{
	static boolean debug = false;
	final static String version = "1.08";
	
	static final String[] classes = {"12 Days of Christmas","Batting Average","Keypad Converter","Stack Calculator","Hash Set",
		"Lemming Opinion","Book Store","Garage"};
	 
	static JFrame frame;
	static JTextArea text;
	static String te;
	static JTextArea input;
	static JPanel buttonsP;
	static JPanel ioPanel;
	static JScrollPane scroller;
	static JLabel temp;
	static JButton x;
	static Thread thread;
	 
	static String currentClass;
	static KeyListener kl = new keyListener();
	
	public static void main(String args[]){
		new mainClass(); //Commence
		//Music Player is commenced in the keyListener class (Which is also paired with this)
	}
	
	mainClass(){
		makeGui(false);
	}
	
	public static void makeGui(boolean cl){
		if (cl&&(frame!=null)) {
			te=null;
			text = null;
			frame.setVisible(false);
			frame.removeAll();
			frame.dispose();
			frame = new JFrame("Class: "+currentClass);
		}
		else {
			frame = new JFrame("Main Menu");
			frame.setName("Main Menu");
		}
		buttonsP = new JPanel(new GridLayout(classes.length+2,1));
		ioPanel = new JPanel();
		ioPanel.setLayout(new BoxLayout(ioPanel,BoxLayout.Y_AXIS));
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		text = new JTextArea(25,40);
		if (!cl) text.setText("Console: Output will be displayed here :)\n");
		if (debug) text.setText(text.getText()+"\nDebug is on:\n");
		text.setWrapStyleWord(true);
		text.setLineWrap(true);
		text.setEditable(false);
		text.setToolTipText("Output will be displayed here");
		scroller = new JScrollPane(text);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setWheelScrollingEnabled(true);
		input = new JTextArea(1,40);
		input.setBackground(Color.cyan);
		input.setWrapStyleWord(false);
		input.addKeyListener(kl);
		input.setLineWrap(false);
		input.setToolTipText("Insert any Input here");
		temp = new JLabel("Place input below...");
		x = new JButton("Reset"); //Main Menu button
		x.setActionCommand("reset");
		x.addActionListener((ActionListener) kl);
		x.setBackground(Color.red);
		buttonsP.add(x);
		buttonsP.add(new JLabel());
		for (int i=0;i<classes.length;i++){
			x = new JButton(classes[i]);
			x.setActionCommand(classes[i]);
			x.addActionListener((ActionListener) kl);
			buttonsP.add(x);
		}
		ioPanel.add(scroller);
		ioPanel.add(temp);
		ioPanel.add(input);
		frame.add(ioPanel);
		frame.add(buttonsP);
		frame.pack();
		frame.setVisible(true);
		
		if (frame.getName().equals("Main Menu")){
			println("Welcome to Jazy5552's Multi-Program Interface");
			println("This is version: "+version);
			println("This program was made in 2011 for a Programming class");
			println("Select one of the buttons on the right to load that program");
		}
	}
	
	static void runClazz(final int which){
		if (debug) System.out.println("Worksss!");
		currentClass = classes[which];
		if (debug) System.out.println("Cleared and renewed?");
		try{ thread = null;
		switch(which){
		case 0: {thread = new Thread(new Runnable(){ //Run Christmas
			public void run() {
				clear();
				mainClass.makeGui(true);
				christmas.main(null);
			}
		});
		thread.start();
		return;
		}
		
		case 1: {thread = new Thread(new Runnable(){ //Run Batting
			public void run() {
				clear();
				mainClass.makeGui(true);
				batting.main(null);
			}
		});
		thread.start();
		return;
		}
		
		case 2: {thread = new Thread(new Runnable(){ //Run keypad
			public void run() {
				clear();
				mainClass.makeGui(true);
				keypad.main(null);
			}
		});
		thread.start();
		return;
		}
		
		case 3: {thread = new Thread(new Runnable(){ //Run keypad
			public void run() {
				clear();
				mainClass.makeGui(true);
				stackCalculator.main(null);
			}
		});
		thread.start();
		return;
		}
		
		case 4: {thread = new Thread(new Runnable(){ //Run keypad
			public void run() {
				clear();
				mainClass.makeGui(true);
				hashSet.main(null);
			}
		});
		thread.start();
		return;
		}
		
		case 5: {thread = new Thread(new Runnable(){ //Run keypad
			public void run() {
				clear();
				mainClass.makeGui(true);
				try{lemmingOpinion.main(null);}catch(Exception ButWhy){}
			}
		});
		thread.start();
		return;
		}
		
		case 6: {thread = new Thread(new Runnable(){ //Run keypad
			public void run() {
				clear();
				mainClass.makeGui(true);
				bookStore.main(null);
			}
		});
		thread.start();
		return;
		}
		
		case 7: {thread = new Thread(new Runnable(){ //Run keypad
			public void run() {
				clear();
				mainClass.makeGui(true);
				garageClient.main(null);
			}
		});
		thread.start();
		return;
		}
		
		
		
		
		default: println("Internal Error...");
		
		
		}
		}catch(Exception ignores){}
		
		
	}
	
	public static void print(String mes){
		text.setText(text.getText()+mes);
		text.setCaretPosition(text.getText().length());
	}
	
	public static void println(String mes){
		text.setText(text.getText()+mes+"\n");
		text.setCaretPosition(text.getText().length());
	}
	
	public static void println(){
		text.setText(text.getText()+"\n");
		text.setCaretPosition(text.getText().length());
	}
	
	public static void printf(String mes, Object...vars){
		if (vars==null) printf(mes);
		print(String.format(mes, vars));
		text.setCaretPosition(text.getText().length());
	}
	
	public static void printf(String mes){
		print(String.format(mes));
		text.setCaretPosition(text.getText().length());
	}
	
	public static String nextLine(){
		te = null;
		while(te==null) keyListener.wait(100);
		return te;
	}
	
	public static String next(){
		te = null;
		while(te==null) keyListener.wait(100);
		//Not sure if I should add a  try te.length()>0  ...?
		if ((te.contains(" "))&&(te.indexOf(" ")!=0)) te = te.substring(0,te.indexOf(" "));
		else if (te.contains(" ")) {
			for (int i=0;i<te.length();i++){
				if (te.charAt(i)!=' ') te = te.substring(i,te.indexOf(" "));
			}
		}
		return te;
	}
	
	public static int nextInt(){
		te = null;
		String tmp = "";
		int re=0;
		while(te==null) keyListener.wait(100);
		for (int i=0;i<te.length();i++){
			try{if ((tmp!="")&&(te.substring(i,i+1).equals(" ")))break; tmp += Integer.parseInt(te.substring(i,i+1));}catch(Exception e){if (tmp!="") break;} //The amount of exceptions is limit less xD
		}
		try{re=Integer.parseInt(tmp);}catch(Exception e){try{throw new Exception();}catch(Exception ee){}}
		return re;
	}
	
	public static double nextDouble(){
		te = null;
		String tmp = "";
		double re=0;
		while(te==null) keyListener.wait(100);
		for (int i=0;i<te.length();i++){
			try{if ((tmp!="")&&(te.substring(i,i+1).equals(" ")))break; if (te.substring(i,i+1).equals(".")) tmp+=".";  else tmp += Integer.parseInt(te.substring(i,i+1));}catch(Exception e){if (tmp!="") break;} //The amount of exceptions is limit less xD
		}
		try{re=Double.parseDouble(tmp);}catch(Exception e){try{throw new Exception();}catch(Exception ee){}}
		return re;
	}
	
	public static void setTitle(String set){
		frame.setTitle(set);
	}
	
	public static void setLayout(LayoutManager lay){
		frame.getContentPane().setLayout(lay);
	}
	
	public static void addJLabel(String txt){
		frame.getContentPane().add(new JLabel(txt));
	}
	public static void reset(){
		 frame.dispose();
		 frame = null;
		 try{thread = new Thread(new Runnable(){
				public void run() {
					new mainClass();
				}
			});
		 }catch(Exception ignore){}
		 thread.start();
	 }
	public static void clear(){
	 	te=null;
	 	text = null;
		frame.setVisible(false);
		frame.removeAll();
		frame.dispose();
		frame = null;
		makeGui(true);
	  }
	
}