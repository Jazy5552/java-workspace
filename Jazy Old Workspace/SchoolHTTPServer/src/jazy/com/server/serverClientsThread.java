package jazy.com.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.SwingUtilities;

import jazy.com.gui._gui;

public class serverClientsThread extends Thread{
	Socket s;
	DataOutputStream out;
	BufferedInputStream in;
	String id;
	int totalbytes;
	int bytessent;

	serverClientsThread(Socket s){
		try{
		this.s = s;
		id = s.getInetAddress().getHostName();
		out = new DataOutputStream(s.getOutputStream());
		in = new BufferedInputStream(s.getInputStream());
		start();
		}catch(Exception e){
			e.printStackTrace();
			_gui.log("Error connecting: "+e.getMessage(), "red");
			disconnect();
		}
	}
	
	
	public void run(){ //InputSTream
		try{
		BufferedReader inn = new BufferedReader(new InputStreamReader(in));
		String inp;
		while ((inp = inn.readLine())!=null){ //While the connection is alive
			System.out.println("RE:"+inp);
			if (inp.startsWith("GET")){
				if (inp.contains(".xml")){// TODO Clean this shit
					//Send to erien's app
					giveXMLFile();
					disconnect();
					return;
				}else if (inp.startsWith("GET /favicon.ico")){
					PrintWriter to = null;
					try{
						to = new PrintWriter(out);
						to.println("<h1>200 OK</h1>");
						to.println("<h2>Content-Type: image/ico</h2>");
					}catch(Exception e){
						e.printStackTrace();
						System.out.println("Error:"+e.getMessage());
					}
					
					//send my little icon :D
					int bytesread;
					byte[] bufferr = new byte[1024];
					BufferedInputStream n = null;
					try{
						n = _gui.findIconFile();
						while ((bytesread = n.read(bufferr))!=-1){
							out.write(bufferr,0,bytesread);
						}
						out.flush();
					}catch(Exception erasd){
					}finally{
						try{to.close();}catch(Exception ignore){}
						try{n.close();}catch(Exception ignore){}
					}
					System.out.println("SENT Favico.ico");
				}else if (inp.startsWith("GET /3dmariowall.jpg")){
					PrintWriter to = null;
					try{
						to = new PrintWriter(out);
						to.println("<h1>200 OK</h1>");
						to.println("<h2>Content-Type: image/jpg</h2>");
					}catch(Exception ee){}
					//Send mario pic
					BufferedInputStream in = null;
					byte[] buffer = new byte[1024];
					int bytesread;
					try{
						in = new BufferedInputStream(this.getClass().getResourceAsStream("/resources/3dmariowall.jpg"));
						while ((bytesread = in.read(buffer))!=-1){
							out.write(buffer,0,bytesread);
						}
						out.flush();
						System.out.println("Send mario 3dwall");
					}catch(Exception ee){
						System.out.println(ee.getMessage());
					}finally{
						try{in.close();}catch(Exception ignored){}
						try{to.close();}catch(Exception isad){}
					}
				}else{ //Send to normal browser
					PrintWriter to = null;
					try{
						to = new PrintWriter(out);
						to.println("<HTML>" +
								"<HEAD>" +
								"<TITLE>Jazy/Erian School Server</TITLE>" +
								"<link REL=\"SHORTCUT ICON\" HREF=\"/favicon.ico\">" +
								"</HEAD>" +
								"<BODY>" +
								"<p style=\"text-align:center;text-shadow:4px 4px 8px blue;\">" +
								"How about you enjoy this mario picture<br>Completely Irrelevant ;)<br>Courtesy of Jazy</p>" +
								"<br>" +
								"<p style=\"text-align:center;\">" +
								"<a href=\"/XMLFile.xml\" >" + //target=\"_blank\"" + //<<-- Opens in new tab
								"<img src=\"/3dmariowall.jpg\" width=\"603\" height=\"453\" border=\"2\" style=\"border:2px solid black;\" alt=\"Mario Wall Paper\" </a></p>" +
								"</BODY>" +
								"</HTML>");
					}catch(Exception e){
						e.printStackTrace();
						System.out.println("Error:"+e.getMessage());
						toLog("Error:"+e.getMessage(),"red");
					}finally{
						try{to.close();}catch(Exception ignore){}
					}
				}
				
			}
		}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			disconnect();
		}
	}
	
	private void toLog(final String mes,final String style){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				_gui.log(mes, style);
			}
		});
	}
	
	public void giveXMLFile(){ //OutputStream
		try{
			final File xmlFile = _gui.xmlFile;
			if (xmlFile == null){
				PrintWriter oout = new PrintWriter(out,true);
				oout.println("404 Not Found");
				oout.println("Server: JazysServer");
				disconnect();
				return;
			}
			//Start writing
					try{
						FileInputStream in = new FileInputStream(xmlFile);
						byte[] buffer = new byte[1024*2];
						int bytesread;
						bytessent = 0;
						totalbytes = in.available();
						while ((bytesread = in.read(buffer))!=-1){
							out.write(buffer, 0, bytesread);
							bytessent += bytesread;
							//Progress
							SwingUtilities.invokeLater(new Runnable(){
								public void run(){
									_gui.info.setText("Writing: "+ (bytessent/totalbytes)*100 + "%");
								}
							});
						}
						SwingUtilities.invokeLater(new Runnable(){
							public void run(){
								_gui.info.setText("Jazy & Erian Server Interface");
								_gui.log("XML Sent to "+id, "green");
							}
						});
					}catch(Exception e){
						e.printStackTrace();
						_gui.log("Error sending: "+e.getMessage(), "red");
					}finally{
						try{in.close();}catch(Exception ee){}
					}
					
		}catch(Exception uhoh){
			uhoh.printStackTrace();
			_gui.log("Error sending file: "+uhoh.getMessage(), "red");
		}
	}
	
	public void disconnect(){
		try{
			out.close();
			in.close();
		}catch(Exception e){}
		try{
			s.close();
		}catch(Exception e){}
	}
	
	
	
	
}
