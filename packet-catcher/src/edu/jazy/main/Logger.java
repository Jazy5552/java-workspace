package edu.jazy.main;

public class Logger {

	public void log(String msg) {
		synchronized (this) {
		//Implement default logging method
		System.out.println(msg);
		}
	}
	
	public void msglog(String msg) {
		synchronized (this) {
		//Implement method for received message
		log(">" + msg);
		}
	}
	
	public void syslog(String msg) {
		synchronized (this) {
		//Console output
		System.out.print(msg);
		}
	}
	
	public void dlog(String msg) {
		synchronized (this) {
		//Debug logging method
		System.err.println(msg);
		}
	}
	
}
