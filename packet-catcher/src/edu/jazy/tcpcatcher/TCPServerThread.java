/*
 * This will be started from the main and will control the server socket along
 * with the thread on which the server socket will listen to incoming connections
 * on. After a connection has been established the client socket will be stored in an
 * arraylist provided by the tcp main (TODO)
 */
package edu.jazy.tcpcatcher;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import edu.jazy.main.Logger;

public class TCPServerThread extends Thread implements Runnable {
	final Logger l;
	ServerSocket serverSoc;
	//final ArrayList<TCPClientSocket> cSocs;
	int port;
	

	public TCPServerThread(int port, Logger l) {
		// TODO Auto-generated constructor stub
		this.l = l;
		this.port = port;
		serverSoc = null; //TODO
		try {
				
		} catch (Exception e) {
			l.dlog(e.toString());
		}
	}
	
}
