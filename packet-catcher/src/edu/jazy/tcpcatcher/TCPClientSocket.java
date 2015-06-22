/*
 * TCP Client Socket
 * Will hold the client socket and start a thread to listen and print
 * the data it receives. Data will be handled here
 */
package edu.jazy.tcpcatcher;

import edu.jazy.main.Logger;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCPClientSocket implements Runnable{
	final Socket clientSoc;
	final Logger l;
	final String remoteAddress;
	volatile boolean connected = false;
	
	
	public TCPClientSocket(Socket clientSoc, final Logger l) {
		this.l = l;
		this.clientSoc = clientSoc;
		remoteAddress = clientSoc.getRemoteSocketAddress().toString();
		if (clientSoc != null && !clientSoc.isClosed())
		{
			connected = true;
		}
		Thread t = new Thread(this);
		t.setDaemon(true);
		t.start(); //Start listening for messages
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			BufferedInputStream br = new BufferedInputStream(clientSoc.getInputStream());
			byte[] buffer = new byte[1024];
			int bytesread;
			while ((bytesread = br.read(buffer)) != -1) {
				String msg = new String(buffer, 0, bytesread);
				l.tcpclog(msg, this);
			}
		} catch (Exception e) {
			l.tcpclog("Disconnected", this);
			connected = false;
		}
	}
	
	public void send(String msg) {
		//Send the message to the remote
		if (!connected) {
			l.log("Error: Socket is not connected");
			return;
		}
		
		try {
		byte[] buffer = msg.getBytes(StandardCharsets.UTF_8);
		clientSoc.getOutputStream().write(buffer, 0, buffer.length);
		clientSoc.getOutputStream().flush();
		} catch(IOException ioe) {
			//Not connected for output
			l.log("Error: Socket disconnected");
			connected = false;
		} catch(Exception e) {
			l.dlog(e.getMessage());
		}
	}
	
	public void close() {
		try {
			clientSoc.close();
		} catch (IOException e) {
			l.dlog(e.toString());
		}
		connected = false;
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	public String toString() {
		return remoteAddress;
	}
}
