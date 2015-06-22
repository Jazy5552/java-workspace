/*
 * Author's Comments
 * Free to use and modify
 * Courtesy of Jazy5552
 */
package jazy.programming2.garage;
import java.util.Scanner;
import java.util.ArrayList;


public class garageClient {
	static ArrayList<garage> garages = new ArrayList<garage>(0);
	
	public static void main(String[] args) {
		System.out.println("Welcome to Jazy's Garage creator!");
		createGarage();
	}
	
	public static void createGarage(){
		System.out.printf("Enter a new garage name: ");
		Scanner scan1 = new Scanner(System.in);
		String x = scan1.nextLine();
		
		garages.add(0, new garage(x));
		int current = 0;
		
		while(true){ //Not sure if this dirty
			System.out.printf("\nEnter a new command for %s. (Help) for list of commands.\n: ", garages.get(current).getName());
			x = scan1.next().toLowerCase();
				
			if (x.equals("help")) System.out.printf("\nCommand - Description" + //Commands (Didn't use format for organization...)
					"\nadd [INT] - add int number of cars" +
					"\noccupancy - display occupancy" +
					"\ngallons - display total number of gallons" +
					"\nmiles - display total number of miles in garage" +
					"\nnew [STRING] - create a new garage with STRING name" +
					"\nlist - list all garages" +
					"\nchange [STRING] - change to STRING garage" +
					"\nexit - End garage class\n");
			else if ((x.equals("add"))&&(scan1.hasNextInt())) garages.get(current).addCars(scan1.nextInt());
			else if (x.equals("occupancy")) garages.get(current).showOccupancy();
			else if (x.equals("gallons")) garages.get(current).showGallons();
			else if (x.equals("miles")) garages.get(current).showMiles();
			else if ((x.equals("new"))&&(scan1.hasNext())) { //Create another garage
				garages.add(new garage(scan1.next()));
				current = garages.size()-1; 
			}
			else if (x.equals("list")) {
				for (int i=0; i<garages.size(); i++){
					System.out.printf("\n%s",garages.get(i).getName());
				}
			}
			else if ((x.equals("change"))&&(scan1.hasNext())){
				boolean done = false;
				String n = scan1.next().toLowerCase();
				for (int i=0;i<garages.size();i++){
					if (n.equals(garages.get(i).getName().toLowerCase())) {
						current = i;
						done = true;
					}
				}
				if (!done) System.out.printf("\nGarage (%s) was not found in the database!", n);
			}
			else if (x.equals("exit")){
				System.out.printf("\nThank you for using the garage class\nHave a nice day");
				System.exit(0);
			}
			else System.out.printf("\n[%s] is not a valid command, use help for a list of commands", x + scan1.nextLine());
		}
	}

}
