import java.util.Scanner;

public class BusRoute {
	static int numRoutes = 0;
	static Scanner in = new Scanner(System.in); //Just need this...
	
	int bRName;
	String startDest;
	String endDest;
	int time;
	
	BusRoute(int name, String start, String end, int time) {
		//Creates route and prints New Route Created
		bRName = name;
		startDest = start;
		endDest = end;
		this.time = time;
		numRoutes++;
		System.out.println("New route create!");
	}
	
	public void changeRoute() {
		//Ask to change route information
		System.out.print("New name: ");
		bRName = Integer.parseInt(in.nextLine()); //Dont break me plz
		System.out.print("New start destination: ");
		startDest = in.nextLine();
		System.out.print("New end destination: ");
		endDest = in.nextLine();
		System.out.print("New time: ");
		time = Integer.parseInt(in.nextLine()); //Dont break me plz
	}
	
	public String toString() {
		//Print the route specific information
		System.out.println(bRName + " " + startDest + " " + endDest + " " + time);
		return null;
	}
	
	static public void printNumRoutes() {
		//Print the numRoutes variable
		System.out.println("Number of routes: " + numRoutes);
	}
	
	
}
