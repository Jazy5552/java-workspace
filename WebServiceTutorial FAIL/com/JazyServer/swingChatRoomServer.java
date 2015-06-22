package com.JazyServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class swingChatRoomServer extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	static int listenPort = 5502;
	
	ServerSocket ser;
	JPanel mainPanel = new JPanel();
	JTextArea inputArea = new JTextArea(3,40); //Input
	JTextPane logArea = new JTextPane(); //Display window
	StyledDocument doc;
	inputListener ils = new inputListener();
	JLabel stat; //Make a JLabel to display the status
	clientHandler cl = new clientHandler(10);
	
	swingChatRoomServer(int portL){ //Make the swing gui
		listenPort = portL;
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
		jp.setAutoscrolls(true);
		jp2.setAutoscrolls(true);
		jp2.setPreferredSize(new Dimension(200,150));
		jp2.setMinimumSize(new Dimension(200,150));
		
		getContentPane().add(jp2,BorderLayout.NORTH);
		getContentPane().add(jp,BorderLayout.SOUTH);
		getContentPane().add(buttonPanel,BorderLayout.CENTER);
		
		setTitle("ChatRoom Server: By Jazy5552");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		addWindowListener(ils);
		cl.start();
	}
	
	public static void main(String args[]){
		new swingChatRoomServer(listenPort);
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
					cl.postMessage("Server",inputArea.getText());
					inputArea.setText("");
				}
				
			}
		}
		public void keyTyped(KeyEvent e) {
		}
		public void actionPerformed(ActionEvent e){ //Send Button hit
			if (e.getActionCommand().equals("send")){
				cl.postMessage("Server",inputArea.getText());
				inputArea.setText("");
			} else if (e.getActionCommand().equals("clear")){
				inputArea.setText("");
				logArea.setText("(SERVER) Chat Room:\n");
			}
		}
		public void windowActivated(WindowEvent arg0) {
		}
		public void windowClosed(WindowEvent arg0) {
		}
		public void windowClosing(WindowEvent arg0) {
			try{
				cl.closeConnections();
				ser.close();
				dispose();
			}catch(Exception e){}
		}
		public void windowDeactivated(WindowEvent arg0) {
		}
		public void windowDeiconified(WindowEvent arg0) {
		}
		public void windowIconified(WindowEvent arg0) {
		}
		public void windowOpened(WindowEvent arg0) {
		}
	}
	
	private class clientHandler extends Thread{
		boolean live = true;
		int maxConnections;
		ArrayList<client> connections = new ArrayList<client>(0);
		
		clientHandler(int maxClients){
			maxConnections = maxClients;
		}
		
		public void run(){
		try{
			ser = new ServerSocket(listenPort);
			stat.setText("Port="+listenPort);
		}catch(Exception e){
			outputText("Can't listen on port:"+listenPort+"\n"+e.getMessage(),"console");
			stat.setText("Failed");
			return; //TODO Close program or ask for new port
		}
			while(live){ //live = Universal power switch!
				stat.setText("Connections:"+connections.size()+" Port:"+listenPort);
				while((connections.size()<maxConnections)&&(live)){
					try{
					client newClient = new client(ser.accept());
					connections.add(newClient);
					outputText(newClient.ipaddress+" Connected!","console");
					newClient.start(); //Let the client shit run on it's own thread
					stat.setText("Connections:"+connections.size()+" Port:"+listenPort);
					}catch(Exception e){
						outputText("Connection failed:"+e.getMessage(),"console");
					}
				}
			try{
			Thread.sleep(1000); //Just take a nap while the room is full
			}catch(Exception e){}
			}
		}
		
		public synchronized void removeClient(client c){
			for (int i=0;i<connections.size();i++){
				if (connections.get(i)==c){
					connections.remove(i);
					postMessage(c.ipaddress,"has disconnected!");
					c.disconnect();
				}
			}
		}
		
		public synchronized void postMessage(String from,String mes){ //Gota style the packets for the clients
			if (from=="Server"){
			outputText(mes,"local");
			}else{
				outputText(mes,from);
			}
			for (int i=0;i<connections.size();i++){
				connections.get(i).sendMessage(from,mes);
			}
		}
		
		public void closeConnections(){
			live=false;
			for (int i=0;i<connections.size();i++){
				cl.removeClient(connections.get(i));
			}
		}
		
	}
	
	private class client extends Thread{
		boolean connected;
		Socket cSocket;
		String ipaddress;
		String devIPAddress;
		BufferedWriter out;
		BufferedReader in;
		
		client(Socket me){
			connected = true;
			cSocket = me;
			ipaddress = me.getInetAddress().getHostName();
			devIPAddress = me.getInetAddress().getHostAddress();
			try{
			out = new BufferedWriter(new OutputStreamWriter(cSocket.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
			}catch(Exception e){
				outputText("Error connecting to "+ipaddress+"\n"+e.getMessage(),"console");
				try{
					out.close();
					in.close();
					cSocket.close();
				}catch(Exception ee){}
				connected = false;
				return;
			}
			String inmes = "";
			try {
				while((connected)&&((inmes = in.readLine())!=null)){
					cl.postMessage(ipaddress, inmes);
				}
			} catch (IOException e) {
				outputText(ipaddress+" lost connection:"+e.getMessage(),"console");
				cl.postMessage(ipaddress, "Lost Connection!");
				cl.removeClient(this);
			}
		}
		
		public void sendMessage(String from,String mes){
			String toSend = "";
			toSend += "<from:"+from+"/from>";
			toSend += "<text:"+mes+"/text>";
			toSend += "<com:"+0+"/com>";
			try{
			out.write(toSend);
			out.flush();
			}catch(Exception e){
				outputText(ipaddress+" failed to send:"+e.getMessage(),"console");
			}
		}
		
		public void disconnect(){
			try{
				out.flush();
				out.close();
				in.close();
				cSocket.close();
				connected = false;
			}catch(Exception e){
				outputText("ERROR:"+e.getMessage(),"console");
			}
		}
	}
}
