package jazy.com.gui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.StyledDocument;

public class infoFile extends JFrame{
	private static final long serialVersionUID = 1L;
	boolean inInfo;
	JTextPane infoArea = new JTextPane(_gui.makeDoc());
	StyledDocument d = infoArea.getStyledDocument();
	final String path = "/resources/info.txt";
	windowManager wl = new windowManager();
	Point lo;
	
	infoFile(){
		setTitle("Info Window: Close to exit");
		
		infoArea.setEditable(false);
		JScrollPane jp = new JScrollPane(infoArea);
		jp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp.setPreferredSize(new Dimension(300,150));
		
		getContentPane().add(jp);
		pack();
		processInfo();
	}
	
	void processInfo(){
			Scanner in;
				try{
					in = new Scanner(this.getClass().getResourceAsStream(path));
				}catch(final Exception e){
					System.out.println("ERROREX:"+e.getMessage());
					SwingUtilities.invokeLater(new Runnable(){
						public void run(){
							try{d.insertString(0, "ERROR:"+e.getMessage(), d.getStyle("red"));}catch(Exception ne){System.out.println("Error printing:"+ne.getMessage());}
						}});
					return;
				}
				String inP;
				while (in.hasNextLine()){
					inP = in.nextLine();
					if (inP.startsWith("#")) {
						continue; //Just a comment
					}
					try{d.insertString(d.getLength(), inP + "\n", d.getStyle("regular"));}catch(Exception ee){System.out.println("Error:"+ee.getMessage());}
				}
				try{d.insertString(d.getLength(), "(Exit to return)", d.getStyle("red"));}catch(Exception et){}
				//Complete
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						infoArea.setStyledDocument(d);
					}
				});
	}
	
	public void showInfo(){
		lo = _gui.f.getLocationOnScreen();
		_gui.f.setVisible(false);
		setLocation(lo);
		setVisible(true);
		addWindowListener(wl);
	}
	
	private class windowManager implements WindowListener{
		public void windowActivated(WindowEvent e) {
		}
		public void windowClosed(WindowEvent e) {
			removeWindowListener(wl);
		}
		public void windowClosing(WindowEvent e) {
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					dispose();
					_gui.f.setVisible(true);
					_gui.f.toFront();
					_gui.f.validate();
					_gui.f.repaint();
				}
			});
		}
		public void windowDeactivated(WindowEvent e) {
		}
		public void windowDeiconified(WindowEvent e) {
		}
		public void windowIconified(WindowEvent e) {
		}
		public void windowOpened(WindowEvent e) {
		}
	}
}
