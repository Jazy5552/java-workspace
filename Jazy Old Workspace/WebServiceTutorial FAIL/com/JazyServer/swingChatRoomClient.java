/**
 * Messages recieved must have
 * <from: HOSTNAME /from>
 * <text: TEXTSENT/RECIEVED /text>
 * <com: INT /com>
 * 
 * The com is optional and is simply a debug/development tool
 * 	for such as telling the server I want to disconnect or kick somebody
 * 	if com==0 then it is OK 1->Disconnect 2->Banned 3->Misc :D
 */

package com.JazyServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class swingChatRoomClient extends JFrame {
	private static final long serialVersionUID = 1L;
	
	boolean busy = false; //Just for safety (Habit from Lua)
	JPanel mainPanel = new JPanel();
	JTextArea inputArea = new JTextArea(3,40); //Input
	JTextPane logArea = new JTextPane(); //Display window
	StyledDocument doc;
	connectionToServer cts = new connectionToServer(); //Socket Manager
	inputListener ils = new inputListener();
	JLabel stat; //Make a JLabel to display the status
	
	swingChatRoomClient(String ho,int po){ //Make the swing gui
		BorderLayout layout = new BorderLayout();
		layout.setHgap(10);
		layout.setVgap(10);
		getContentPane().setLayout(layout);
		
		JPanel buttonPanel = new JPanel();
		JButton sendB = new JButton();
		sendB.setText("Send");
		sendB.setActionCommand("send");
		sendB.addActionListener(ils);
		JButton clearB = new JButton();
		clearB.setText("Clear");
		clearB.setActionCommand("clear");
		clearB.addActionListener(ils);
		//JButton saveB = new JButton();
		//saveB.setText("Save");
		//saveB.setActionCommand("save");
		//saveB.addActionListener(ils);
		buttonPanel.add(sendB);
		//buttonPanel.add(saveB);
		stat = new JLabel();
		stat.setText("Starting up...");
		buttonPanel.add(stat);
		buttonPanel.add(clearB);
		
		
		inputArea.setWrapStyleWord(true); //This wraps words instead of breaking them
		inputArea.setLineWrap(true); //This wraps in general (When reaches width)
		inputArea.setBackground(Color.green);
		inputArea.addKeyListener(ils);
		inputArea.setEditable(true);
		inputArea.setText("Input Area");
		
		logArea.setEditable(false);
		doc = logArea.getStyledDocument(); //Use doc to add text now!!!
		Style regular = doc.addStyle("regular",StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE)); //Just gets the default style
		Style style = doc.addStyle("green", regular);
		StyleConstants.setForeground(style, Color.green);
		style = doc.addStyle("bold",regular);
		StyleConstants.setBold(style, true);
		style = doc.addStyle("red",regular);
		StyleConstants.setForeground(style, Color.red);
		logArea.setText("Chat Room:\n");
		
		
		JScrollPane jp = new JScrollPane(inputArea);
		JScrollPane jp2 = new JScrollPane(logArea);
		jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp2.setPreferredSize(new Dimension(200,150));
		jp2.setMinimumSize(new Dimension(200,150));
		
		getContentPane().add(jp2,BorderLayout.NORTH);
		getContentPane().add(jp,BorderLayout.SOUTH);
		getContentPane().add(buttonPanel,BorderLayout.CENTER);
		
		setTitle("ChatRoom Client");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		addWindowListener(ils);
		
		cts.setHostPort(ho, po);
		cts.start();
		
	}
	
	public static void main(String args[]){
		new swingChatRoomClient("localhost",5502);
	}
	
	public void logStat(String text){
		stat.setText(text);
	}
	
	public void outputText(String txt,String from){
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String nowDateTime = df.format(new Date());
		try {
			if (from.equals("local")) { //Sent message
				doc.insertString(doc.getLength(), "["+nowDateTime+"]:", doc.getStyle("bold")); //Time stamp
				doc.insertString(doc.getLength(), txt+"\n", doc.getStyle("green"));
			}else if (from.equals("console")){ //Console message
				doc.insertString(doc.getLength(), "["+nowDateTime+"]:", doc.getStyle("bold")); //Time stamp
				doc.insertString(doc.getLength(), txt+"\n", doc.getStyle("red"));
			}else{
				doc.insertString(doc.getLength(), from + " ["+nowDateTime+"]:", doc.getStyle("bold")); //Time stamp
				doc.insertString(doc.getLength(), txt+"\n", doc.getStyle("regular"));
			}
		} catch (Exception e) {}
		logArea.setCaretPosition(doc.getLength());
	}
	
	
	private class inputListener implements KeyListener,ActionListener,WindowListener {
		public void keyPressed(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode()==KeyEvent.VK_ENTER){
				if (e.isShiftDown()){ //Enter an enter
					inputArea.setText(inputArea.getText()+"\n");
				}else{
					cts.sendString(inputArea.getText());
					inputArea.setText("");
				}
				
			}
		}
		public void keyTyped(KeyEvent e) {
		}
		public void actionPerformed(ActionEvent e){ //Send Button hit
			if (e.getActionCommand().equals("send")){
				System.out.println("Mes Sent with button:"+inputArea.getText());
				cts.sendString(inputArea.getText());
				inputArea.setText("");
			} else if (e.getActionCommand().equals("clear")){
				inputArea.setText("");
				logArea.setText("Chat Room:\n");
			}
		}
		public void windowActivated(WindowEvent arg0) {
		}
		public void windowClosed(WindowEvent arg0) {
			cts.disconnect();
		}
		public void windowClosing(WindowEvent arg0) {
			try{
			dispose();
			cts.sendString(cts.sock.getInetAddress() + " disconnecting!");
			cts.out.close();
			cts.in.close();
			cts.sock.close();
			}catch(Exception e){} //Not connected, who cares?
		}
		public void windowDeactivated(WindowEvent arg0) {
		}
		public void windowDeiconified(WindowEvent arg0) {
			try{
			cts.sendString(cts.sock.getInetAddress() + " is Back");
			}catch(Exception e){} //Not connected, who cares?
		}
		public void windowIconified(WindowEvent arg0) {
			try{
			cts.sendString(cts.sock.getInetAddress() + " is AFK");
			}catch(Exception e){} //Not connected, who cares?
		}
		public void windowOpened(WindowEvent arg0) {
		}
	}

	
	private class connectionToServer extends Thread{
		
		String H = null;
		int P = 0;
		BufferedReader in = null;
		BufferedWriter out = null;
		Socket sock = null;
		boolean connection = false;
		
		public void setHostPort(String hostName,int portNum){
			H = hostName;
			P = portNum;
		}
		
		public void run(){
			if (H==null){
				outputText("No host name was specified!","console"); //Ask for host name
			}
			try{
				logStat("Connecting...");
				sock = new Socket(H,P);
				out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
				in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				connection = true;
				outputText("Connected to host:"+H+" port:"+P,"console");
				logStat("Connected to:"+H);
			}catch(Exception e){
				outputText("Error connecting:"+e.getMessage(),"console");
				connection = false;
				logStat("Offline");
				return;
			}
			String inS;
				try {
					logStat("Online");
					while(((inS = in.readLine())!=null)&&connection){
						processIncommingPacket(inS);
					}
				} catch (Exception e) {
					System.out.println("End of stream:"+e.getMessage());
				} finally {
					connection = false;
					outputText("Connection lost!","console");
					disconnect();
				}
		}
		
		public void processIncommingPacket(String inp){
			String From = inp.substring(inp.indexOf("<from:")+6,inp.indexOf("/from>"));
			String Text = inp.substring(inp.indexOf("<text:")+6,inp.indexOf("/text>"));
			if (inp.contains("<com:")){
				int Com = Integer.parseInt(inp.substring(inp.indexOf("<com:")+5,inp.indexOf("/com>")));
				if (Com==1){ //Server wants us to disconnect... D:
					outputText("You are being disconnected by the server...","console");
					disconnect();
					return;
				}
			}
			
			outputText(Text,From);
		}
		
		public void disconnect(){ //Perhaps make a button for this
			try{
				logStat("Disconnecting...");
				connection = false;
				in.close();
				out.close();
				sock.close();
				in = null;
				out = null;
				sock = null;
			}catch(Exception e){}
			outputText("Disconnected from \nHost:"+H+" Port:"+P,"console");
			logStat("Offline");
		}
		
		
		
		public void sendString(String txt) { //Send the string to server
			if (!connection){ //Lets connect shall we?
				outputText("There is no connection! Establishing now...","console");
				cts = new connectionToServer();
				cts.setHostPort(H, P);
				cts.start();
				return;
			}
			logStat("Sending message...");
			//We are gona let the server style the packet for the others
			try {
				out.write(txt);
				out.flush();
				logStat("Message sent!");
			} catch (IOException e) {
				outputText("Error Sending!:"+e.getMessage(),"console");
				logStat("Sending failed!");
			}
		}
	}

}
