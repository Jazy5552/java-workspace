package edu.jazy.udpcatcher;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

import edu.jazy.main.Logger;

public class UDPServerThread extends Thread {
	private final Logger l;
	private volatile SocketAddress lastClient;
	private volatile DatagramSocket soc;
	
	UDPServerThread(DatagramSocket soc, final Logger l) {
		this.l = l;
		this.soc = soc;
		lastClient = null;
	}
	
	@Override
	public void run() {
		l.dlog("UDPServer started");
		while (soc != null) {
			try {
				byte[] buffer = new byte[1024];
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
				soc.receive(dp);
				
				lastClient = dp.getSocketAddress();
				String msg = new String(buffer).trim();
				l.msglog(msg);
			} catch (Exception e) {
				//Catch general exception!
				l.dlog(e.getMessage());
			}
		}
	}
	
	@Override
	public void start() {
		super.start();
	}
	
	public void shutdown() {
		soc.close();
		soc = null;
		lastClient = null;
	}
	
	public SocketAddress getLastClient() {
		return lastClient;
	}

}
