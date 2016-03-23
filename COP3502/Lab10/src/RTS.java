import java.util.Scanner;

public class RTS {

	public static void main(String[] args) {
		//Use the BusRoute class to create 3 objects
		Scanner in = new Scanner(System.in);
		String startDest, endDest, mInput;
		int name, time, input, mRoute;
		BusRoute routes[] = new BusRoute[3]; //For the sacred for loop
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~Regional Transit System Scheduler~~");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		//Create the 3 routes
		/* Ain't nobody got time for this
		for (int i = 0; i < 3; i++) {
			System.out.println("\nCreating route" + (i+1));
			System.out.print("Name of route: ");
			name = Integer.parseInt(in.nextLine()); //Dont break me plz
			System.out.print("Start destination: ");
			startDest = in.nextLine();
			System.out.print("End destination: ");
			endDest = in.nextLine();
			System.out.print("Time: ");
			time = Integer.parseInt(in.nextLine()); //Dont break me plz
			routes[i] = new BusRoute(name, startDest, endDest, time);
		}
		*/
		//Shortcut
		routes[0] = new BusRoute(21, "Cabana", "Reitz Union", 30);
		routes[1] = new BusRoute(1, "Apple Farm", "Equestria", 10);
		routes[2] = new BusRoute(52, "Mi Casa", "Tu Casa", 2);
		
		do {
			//Print the menu
			System.out.println("\nMain Menu");
			System.out.println("1\tView Route");
			System.out.println("2\tView number of Routes");
			System.out.println("3\tChange Route");
			System.out.println("4\tExit");

			System.out.print("\nInput: ");
			input = Integer.parseInt(in.nextLine()); //Dont break me plz
			
			//Conditionals
			if (input == 1) {
				//Ask for route to view
				System.out.print("Which route: ");
				mInput = in.nextLine();
				mRoute = GimyDatNum(mInput) - 1; // :)
				routes[mRoute].toString(); //Prints in toString function... such unconvention
			} else if (input == 2) {
				//Print the number of routes
				BusRoute.printNumRoutes();
			} else if (input == 3) {
				//Ask for route to change and change it
				System.out.print("Which route: ");
				mInput = in.nextLine();
				mRoute = GimyDatNum(mInput) - 1; // :)
				routes[mRoute].changeRoute();
			}
		
		} while(input != 4);
		
		System.out.println("Stop requested. For your safety, if crossing the street: please wait till the bus departs, look both ways, and use crosswalk if available.\n"
				+ "If you brough an aligator with you please don't forget to take it with you on your way out.");
		
		in.close();
	}
	
	static int GimyDatNum(String routeIn) {
		return(Integer.parseInt(routeIn.substring(5))); //Gimy dat number)
	}

}
