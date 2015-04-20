/**
 * Constructor only needs a port integer and the logger object
 * This class will hold and start the UDPServerThread which will listen and print
 * any incoming packets on the specified port using the method in the Logger class
 *
 */
package edu.jazy.udpcatcher;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

import edu.jazy.main.Logger;

public class UDPMain {
	private final Logger l;
	private DatagramSocket soc;
	private UDPServerThread udpst;
	private SocketAddress currentClient;
	
	public UDPMain(int port, final Logger l) {
		this.l = l;
		try {
			soc = new DatagramSocket(port);
			udpst = new UDPServerThread(soc, this.l);
			currentClient = null;
			l.dlog("UDP Main initialized");
			
			//udpst.start(); //Moved to start function
		} catch (Exception e) {
			l.dlog(e.getMessage());
			soc = null;
			udpst = null;
		}
	}
	
	public void startListen() {
		if (udpst != null) {
			udpst.start();
		}
	}
	
	public void send(String msg) {
		try {
			if (currentClient == null) {
				useLastClient();
			}
			//Sending on same thread
			byte[] buffer = msg.getBytes(StandardCharsets.UTF_8);
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length, currentClient);
			
			soc.send(dp);
		} catch (Exception e) {
			l.dlog(e.getMessage());
			l.log("Error: No address! (Use setip)");
		}
	}
	
	public void setCurrentClient(SocketAddress sa) {
		currentClient = sa;
	}
	
	public void setCurrentClient(String host, int port) {
		try {
			currentClient = new InetSocketAddress(host, port);
		} catch(Exception e) {
			l.log("Error: " + e.getMessage());
			currentClient = null;
		}
	}
	
	public String getCurrentClientIp() {
		if (currentClient == null) {
			return "No current client";
		} else {
			return currentClient.toString();
		}
	}
	
	public void disconnectCurrentClient() {
		currentClient = null;
	}
	
	public void useLastClient() {
		if (udpst != null) {
			currentClient = udpst.getLastClient();
		}
	}
	
	public void shutdown() {
		if (udpst != null) {
			udpst.shutdown();
			soc = null;
			udpst = null;
		}
	}
}
