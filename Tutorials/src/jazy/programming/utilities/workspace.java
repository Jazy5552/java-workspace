package jazy.programming.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.Runtime;

public class workspace {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		
		Runtime rt = Runtime.getRuntime();
		try {
			Process pr = rt.exec("C:/Windows/System32/cmd.exe /c start cmd.exe");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
