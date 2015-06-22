/**
 * CmdLine: appname.exe udp [IPADDRESS] [PORT]
 * CmdLine: appname.exe tcp [IPADDRESS] [PORT]
 */
package edu.jazy.main;

import java.util.Scanner;

import edu.jazy.udpcatcher.UDPMain;

public class MainClass {

	public static void main(String[] args) {
		final Logger l = new Logger(); //Carry this shit everywhere...
		Scanner input = new Scanner(System.in);
		String in;
		if (args.length == 0) {
			//UDP SECTION
			UDPMain udpm;
			//Initial setup with command line arguments
			int p;
			if (args.length == 3) {
				in = args[1];
				p = Integer.parseInt(args[2]);
				udpm = new UDPMain(p, l);
				udpm.setCurrentClient(in, p);
			} else {
				l.syslog("Enter the port to use (8008): ");
				p = Integer.parseInt(input.nextLine());
				udpm = new UDPMain(p, l);
			}
			l.log("\n******UDP COMMANDS*******\n" +
					"setip [IPADDRESS]:[PORT]\n" +
					"disconnect\n" +
					"reset\n" +
					"**************************");
			
			while (!(in = input.nextLine()).equals("quit")) {
				if (in.startsWith("setip ")) {
					try {
					if (in.contains(":")) {
						String ip = in.substring(6, in.indexOf(':'));
						int port = Integer.parseInt(in.substring(in.indexOf(':') + 1));
						udpm.setCurrentClient(ip, port);
					} else if (in.equals("reset")) {
						l.log("Reseting...");
						break;
					} else {
						String ip = in.substring(6);
						l.syslog("Enter the port number: ");
						int port = Integer.parseInt(input.nextLine());
						udpm.setCurrentClient(ip, port);
					}
					} catch(Exception e) {
						l.log("Error: " + e.getMessage());
					}
				} else if (in.equals("disconnect")) {
					udpm.disconnectCurrentClient();
				} else if (!in.equals("quit")) {
					udpm.send(in);
				}
			}
			udpm.shutdown();
			input.close();
			//END UDP SECTION
		} else if (args[0] == "tcp") {
			//TCP SECTION
		}
	}
}
