/*
 * Connection Checker Thread
 * Will periodically loop through an array of TCPClientSockets and check for
 * connectivity. If the socket is not connected then it is removed from the 
 * arraylist. This class will also handle removing client sockets and closing them
 * 
 */
package edu.jazy.tcpcatcher;

import java.util.ArrayList;

public class CCThread extends Thread {

	private volatile ArrayList<TCPClientSocket> cons;
	
	public CCThread(ArrayList<TCPClientSocket> cons) {
		this.cons = cons;
	}
	
}
