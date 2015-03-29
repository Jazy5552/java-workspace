package jazy.programming.utilities;
import java.lang.Runtime;
import java.io.IOException;
public class test {
	public static void main(String args[]) throws IOException 
	        {
	        Runtime load = Runtime.getRuntime();
	        // getRuntime() method is a static method which is used to 
	        // obtain access to an object of class Runtime.
	        load.exec("java -Xmx1024M -Xms1024M -jar C:\\minecraft_server.jar");
	        }
	} // End of class loadNotpad
