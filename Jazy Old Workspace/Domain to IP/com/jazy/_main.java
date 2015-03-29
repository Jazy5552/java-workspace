package com.jazy;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class _main extends JFrame{
	private static final long serialVersionUID = 1L;
	static JTextArea textArea = new JTextArea(6,20);
	static JTextArea inputArea = new JTextArea(2,20);
	final static JLabel status = new JLabel("Made by Jazy v:1.0");
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new _main();
			}
		});
	}
	
	public static synchronized void log(final String mes) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				textArea.setText(textArea.getText()+mes+"\n");
				textArea.setCaretPosition(textArea.getText().length());
			}
		});
	}
	
	public static synchronized void logLast(final String mes) { //Progress (Edit last line)
		String tmp = textArea.getText();
		int begin = tmp.lastIndexOf("\n") + 1;
		tmp = tmp.substring(0,begin);
		final String ntmp = tmp + mes;
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				textArea.setText(ntmp);
				textArea.setCaretPosition(textArea.getText().length());
			}
		});
	}
	
	_main() {
		setTitle("Jazy's Domain to Ip");
		JPanel pan = new JPanel();
		BorderLayout layout = new BorderLayout();
		pan.setLayout(layout);
		
		pan.add(status,BorderLayout.NORTH);
		
		JScrollPane jp = new JScrollPane(textArea);
		jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jp.setPreferredSize(textArea.getPreferredSize());
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setText("Enter domain below\n");
		textArea.setEditable(false);
		pan.add(jp,BorderLayout.CENTER);
		
		inputArea.setText("http://www.google.com");
		inputArea.selectAll();
		inputArea.addKeyListener(new myKeyListener());
		pan.add(inputArea,BorderLayout.SOUTH);
		
		addWindowListener(new myWindowListener());
		add(pan);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	private class myKeyListener implements KeyListener{
		public void keyReleased(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
				inputArea.setText(inputArea.getText().substring(0,inputArea.getText().length()-1));
			}
		}

		public void keyPressed(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
				try{
					new DomainToIp(inputArea.getText(0,inputArea.getText().length()));
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
		}

		public void keyTyped(KeyEvent arg0) {
		}
	}
	
	private class myWindowListener implements WindowListener{

		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void windowClosing(WindowEvent arg0) {
			dispose();
		}

		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
