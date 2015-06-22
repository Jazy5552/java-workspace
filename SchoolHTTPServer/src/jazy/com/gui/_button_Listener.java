package jazy.com.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

public class _button_Listener implements ActionListener{
	static infoFile info = new infoFile();
	
	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();
		if (com.equals("close")){
			_gui.f.dispose();
			_gui.server.close();
		}else if (com.equals("info")){
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					info.showInfo();
					info.toFront();
					info.repaint();
				}
			});
		}
	}

}
