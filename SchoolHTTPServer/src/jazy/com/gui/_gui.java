package jazy.com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import jazy.com.server._server_Thread;

public class _gui {
	static final String version = "1.4";
	static JFrame f = new JFrame("Jazy/Erian Server Interface");
	static JTextPane logArea = new JTextPane(makeDoc()); //TODO Add document
	static StyledDocument d = logArea.getStyledDocument();
	static _server_Thread server;
	public static JLabel info = new JLabel("Jazy/Erian Server Interface (For iPod App)");
	public static File xmlFile;
	static BufferedInputStream icon;
	static _button_Listener bl = new _button_Listener();
	
	_gui(){
		JPanel mainPanel = new JPanel();
		BorderLayout lay1 = new BorderLayout();
		mainPanel.setLayout(lay1);
		info.setHorizontalAlignment(JLabel.CENTER);
		mainPanel.add(info,BorderLayout.NORTH);
		
		JPanel topButtons = new JPanel();
		JButton close = new JButton("Exit");
		close.setActionCommand("close");
		close.addActionListener(bl);
		close.setBackground(Color.red);
		close.setForeground(Color.blue);
		JButton help = new JButton("Info");
		help.setActionCommand("info");
		help.addActionListener(bl);
		JLabel spacing = new JLabel();
		//Resize logo
		ImageIcon ic = new ImageIcon(this.getClass().getResource("/resources/logo.png"),"My Logo");
		Image newI = ic.getImage();
		BufferedImage cop = new BufferedImage(100,40,BufferedImage.TYPE_INT_ARGB);
		cop.createGraphics().drawImage(newI,0,0,100,40,null);
		
		spacing.setPreferredSize(new Dimension(100,40));
		spacing.setIcon(new ImageIcon(cop));
		
		topButtons.add(help);
		topButtons.add(spacing);
		topButtons.add(close);
		mainPanel.add(topButtons,BorderLayout.CENTER);
		
		logArea.setEditable(false);
		JScrollPane jp = new JScrollPane(logArea);
		jp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp.setAutoscrolls(true);
		jp.setPreferredSize(new Dimension(300,100));
		mainPanel.add(jp,BorderLayout.SOUTH);
		
		f.add(mainPanel);
		f.setUndecorated(false); //Remove title bar
		f.addWindowListener(new myWindowListener());
		
		int x = Toolkit.getDefaultToolkit().getScreenSize().width/2; //Center width
		int y = Toolkit.getDefaultToolkit().getScreenSize().height/2; //Center height
		f.setLocation(x-(f.getSize().width)/2, y-(f.getSize().height)/2); //Set to center of screen
		f.pack();
		f.setVisible(true); //Show (draw) the frame
		
		xmlFile = findXmlFile();
		icon = findIconFile();
		server = new _server_Thread();
		log("Made by Jazy, Version: "+version,"regular");
	}
	
	
	public static void log(String mes, String style){
		try{
			d.insertString(d.getLength(),mes + "\n" , d.getStyle(style));
			logArea.setCaretPosition(d.getLength());
		}catch(Exception e){}
	}
	
	
	
	public static StyledDocument makeDoc(){
		StyledDocument re = new DefaultStyledDocument();
		Style regular = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		Style style = re.addStyle("red", regular);
		StyleConstants.setForeground(style, Color.red);
		style = re.addStyle("bold", regular);
		StyleConstants.setBold(style, true);
		style = re.addStyle("regular", regular);
		style = re.addStyle("green", regular);
		StyleConstants.setForeground(style, Color.green);
		style = re.addStyle("blue", regular);
		StyleConstants.setForeground(style, Color.blue);
		
		return re;
	}
	
	public static void main(String args[]){
		SwingUtilities.invokeLater(new Runnable(){ //Runs the GUI on the AWT Thread
			public void run(){
				new _gui();
			}
		});
		
	}
	
	private class myWindowListener implements WindowListener{

		public void windowActivated(WindowEvent arg0) {
		}

		public void windowClosed(WindowEvent arg0) {
			System.exit(0);
		}

		public void windowClosing(WindowEvent arg0) {
			f.dispose();
			server.close();
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
	
	private File findXmlFile(){ //Locate the xml file in the same folder as the jar
		try{
		URI jarLoc = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI();
		System.out.println(jarLoc.toString());
		File[] files = new File(jarLoc).getParentFile().listFiles();
		for (int i = 0; i < files.length; i++){
			System.out.println(files[i].getAbsolutePath());
			if (!files[i].getName().contains(".")) continue;
			if (files[i].getName().substring(files[i].getName().indexOf(".")).equals(".xml")){
				//Found it
				System.out.println("Found: "+files[i].getAbsolutePath());
				_gui.log("XML file found:"+files[i].getName(),"green");
				return files[i];
			}
		}
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Error XML:"+e.getMessage());
		}
		_gui.log("Error: Couldn't find .xml file, Place it inside the same folder as this jar file","red");
		_gui.log("Will generate a dummy one now!","blue");
		File to = new File("XMLFile.xml");
		BufferedInputStream x = null;
		FileOutputStream too = null;
		try{
			x = new BufferedInputStream(this.getClass().getResourceAsStream("/resources/xmlFile.xml"));
			to.createNewFile();
			too = new FileOutputStream(to);
			byte[] buffer = new byte[1024];
			int bytesread;
			while ((bytesread = x.read(buffer))!=-1){
				too.write(buffer,0,bytesread);
			}
			too.flush();
			return to;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				x.close();
				too.close();
			}catch(Exception eer){}
		}
		_gui.log("\nError extracting dummy xml file... check permissions?","red");
		return null;
	}
	
	public static BufferedInputStream findIconFile(){
		BufferedInputStream t;
		try{
		t = new BufferedInputStream(_gui.class.getResourceAsStream("/resources/favicon.ico"));
		}catch (Exception e){
			System.out.println("Favico.ico missing!");
			return null;
		}
		return t;
	}
}
