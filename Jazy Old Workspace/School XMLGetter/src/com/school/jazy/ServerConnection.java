package com.school.jazy;
//TODO GET ACTIVITY!!!
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class ServerConnection extends Thread {

	String uiText = "";
	URL connection;
	File xmlFile = null;
	InputStream in;
	String fileName;
	boolean success = false;
	Activity aa;

	ServerConnection(String prot, String hostName, int port, String file, Activity uiactivity) {
		aa = uiactivity;
		fileName = file;
		try {
			connection = new URL(prot, hostName, port, file);
			run(); //Not using thread
		} catch (Exception e) {
			e.printStackTrace();
			log("Connection failed...");
			new Thread(cantConnect).start();
		}
	}
	
	public File getXMLFile(){
		return xmlFile;
	}

	@Override
	public void run() {
		log("Updating...");
		FileOutputStream to = null;
		BufferedInputStream from = null;
		HttpURLConnection cc = null;
		boolean gotit = false;
		xmlFile = aa.getDir(fileName, Context.MODE_PRIVATE);
		//if (!xmlFile.isFile()) {
			try { //Make the file
				xmlFile.createNewFile();
				Log.d("JAZY","File created:"+xmlFile.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		//}
		try{
			cc = (HttpURLConnection) connection.openConnection();
				//Fresh Copy
				to = new FileOutputStream(xmlFile);
				from = new BufferedInputStream(cc.getInputStream());
				int bytesread;
				byte[] buffer = new byte[1024];
				while ((bytesread = from.read(buffer))!=-1){
					to.write(buffer,0,bytesread);
				}
				to.flush();
				Log.d("CONNECTION","Connected and retrieved file!");
				gotit = true;
		}catch(Exception ioe){
			//Couln't create? Permissions?
			ioe.printStackTrace();
			log("File failed");
		}finally{
			new Thread(cantConnect).start();
			//close everything
			try{
				to.close();
				from.close();
				cc.disconnect();
			}catch(Exception e){}
			//Parse
			if (gotit) {
				//Leave it
			}else{
				//Look for old file?
				if (xmlFile.length()>0){ //Check for info
					//yay use old one
				}else{
					//gtfo
					xmlFile = null;
				}
			}
		}
	}
	
	Runnable cantConnect = new Runnable(){
		public void run(){
			try{
				Thread.sleep(5000);
			}catch(Exception e){
				//ignore
			}
			SchoolXMLGetterActivity.updating = false;
			log("Refresh");
		}
	};
	
	private synchronized void log(String mes){
		uiText = mes;
		aa.runOnUiThread(uiRun);
	}
	
	Runnable uiRun = new Runnable(){
		public void run(){
			SchoolXMLGetterActivity._refresh_button.setText(uiText);
		}
	};

}
