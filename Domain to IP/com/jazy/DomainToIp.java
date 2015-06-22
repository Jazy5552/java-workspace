package com.jazy;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

public class DomainToIp extends Thread{

	String loc;
	boolean complete = false;
	long ping = 0;
	
	public DomainToIp(String loc) {
		if (loc.toLowerCase().startsWith("http")){
			if (loc.contains("://")){
				loc = loc.substring(loc.indexOf("://")+3);
			}else{
				loc = loc.substring(loc.indexOf("http")+4);
			}
		}
		this.loc = loc;
		start();
	}
	
	public void run(){
		try{
			InetAddress net = InetAddress.getByName(loc);
			final String ip = net.getHostAddress();
			//Now ping it 5 times
			new Thread(new Runnable(){
				public void run(){
					long[] pings = new long[5];
					long now;
					URL c;
					for (int i=0;i<pings.length;i++) {
						try{
							now = System.currentTimeMillis();
							c = new URL("http", ip, 80, "/");
							c.openConnection();
							pings[i] = System.currentTimeMillis() - now;
							Thread.sleep(300);
						}catch(Exception e){
							System.out.println(e.getMessage());
							pings[i] = -1;
						}
					}
					for (int x=0;x<pings.length;x++) {
						ping += pings[x];
					}
					ping = ping/(pings.length);
					complete = true;
				}
			}).start();
			int d = 0;
			String logText;
			while (!complete) {
				d++;
				logText = "Pinging";
				Thread.sleep(250);
				for (int i=0;i<d;i++) {
					logText += ".";
				}
				_main.logLast(logText);
				if (d>2) d = 0;
			}
			_main.logLast("Ping Complete!\n");
			//Done
			_main.log("Hostname:"+net.getHostName()+"\n"+
					"IP:"+net.getHostAddress()+"\n"+
					"Ping:"+ping+"ms\n");
		}catch(UnknownHostException uhe){
			_main.log("Invalid host!");
		}catch(Exception e){
			e.printStackTrace();
			_main.log("Error: "+e.getMessage());
		}
	}
}
