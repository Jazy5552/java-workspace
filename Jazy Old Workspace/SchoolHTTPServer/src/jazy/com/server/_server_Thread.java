package jazy.com.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import jazy.com.gui._gui;

public class _server_Thread extends Thread{
	ServerSocket ss;
	ArrayList<serverClientsThread> clients = new ArrayList<serverClientsThread>(0);
	
	public _server_Thread(){
		try{
			ss = new ServerSocket(8080);
			start();
			maintain.setDaemon(true);
			maintain.start();
			toLog("Listening on port:"+ss.getLocalPort(),"green");
		}catch(Exception ee){
			ee.printStackTrace();
			toLog("Error Starting server: "+ee.getMessage(),"red");
			try{
				ss.close();
			}catch(Exception ignore){}
		}
	}
	
	public void run(){
		while (!ss.isClosed()){
			try{
				clients.add(new serverClientsThread(ss.accept()));
				System.out.println("DEBUG: Connection made");
				Thread.sleep(100); //Small delay
			}catch(Exception e){}
		}
	}
	
	public synchronized void removeClient(Socket s){ //An over ride
		try{
			for (int i = 0;i < clients.size(); i++){
				if (clients.get(i).s == s){
					clients.remove(i);
				}
			}
		}catch(Exception ignore){}
	}
	
	public void close(){
		try{
			for (int i = 0;i < clients.size(); i++){
				try{
					System.out.println("Removed: "+clients.get(i).id);
					clients.get(i).disconnect();
				}catch(Exception ignore){System.out.println(ignore.getMessage());}
				clients.remove(i);
			}
			ss.close();
		}catch(Exception e){System.out.println(e.getMessage());}
	}
	
	Thread maintain = new Thread(new Runnable(){ //Maintenance thread of the clients list
		public void run(){
			while (!ss.isClosed()){
				try{
					for (int i = 0;i < clients.size(); i++){
						if (clients.get(i).s.isClosed()){
							try{
								clients.get(i).in.close();
								clients.get(i).out.close();
							}catch(Exception ignore){}
							clients.remove(i);
						}
					}
					Thread.sleep(700); //So it wont abuse the cpu
				}catch(Exception e){}
			}
		}
	});
	
	private void toLog(final String mes,final String style){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				_gui.log(mes, style);
			}
		});
	}
	
}
