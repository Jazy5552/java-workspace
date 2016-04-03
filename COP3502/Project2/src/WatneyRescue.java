/*
 * Made by Jazy Llerena
 * Project 2 for COP 3502
 * Mars Rescue
 */
import java.util.Scanner;
import java.lang.Math;

public class WatneyRescue {

	public static void main(String[] args) {

		//Creates a new instance object. Don't worry about this for now.
		WatneyRescue wr = new WatneyRescue();

		//A new scanner to read input from the user
		Scanner s = new Scanner(System.in);

		//The user's menu selection; 'd', 'r', 'q' or something else 
		char inputChoice;

		do  {
			System.out.println("Do you want 'd' distance mode, 'r' rover mode, or 'q' quit?");
			inputChoice = s.nextLine().charAt(0);

			switch(inputChoice) {

			//Enter distance mode; make the table then compute walking time based on the table
			case 'd': 
				System.out.println("How many meters to Watney?");
				String line = s.nextLine(); 
				double metersToWatney = Double.parseDouble(line.trim());
				String table = wr.generateContingency(metersToWatney);
				System.out.println(table);
				System.out.println("Which row?");
				line = s.nextLine();
				int row = Integer.parseInt(line.trim());
				System.out.println(wr.computeWalkingTime(metersToWatney,table,row));	
				break;

			//Enter rover mode; receive and translate a series of angles for the rover
			case 'r':
				System.out.println("What series of angles?");
				String angles = s.nextLine();
				System.out.println("Mission control on earth sends the following message:\n");
				System.out.println(wr.interpretHexadecimal(angles) + "\n");
				break;

			//Quit
			case'q': 
				continue;

			//Ignore anything that isn't 'd', 'r', or 'q'
			default: 
				continue;
			}
			
		} while (inputChoice != 'q'); //keep going until we get 'q'

		System.out.println("Goodbye. May the hexadecimals be with you.");
		
		//Close the scanner to keep eclipse from driving me crazy with its warnings
		s.close();
	}

	/**
	 * Generates a contingency table of distances that Watney
	 * will have to travel to the ship
	 * @param distLewisWatney The distance in meters from Lewis to Watney, 
	 * as read from Lewis' suit sensors
	 * @return
	 */
	public String generateContingency(double distLewisWatney) {
		String result = "";
		int angle, lDistance, wDistance, row;
		result += "Contingencies given distance " + distLewisWatney + " from Lewis to Watney:\n";

		//TODO: Compute the table values for Lewis angle from north ranging every five degrees 
		//from 15 to 75 inclusive, and for her distance to the hab for every three meters from 15
		//to 30 inclusive. Solve each row for Watney's distance to the hab. Round everything
		//to the nearest meter. 
		row = 1;
		for (angle = 15; angle <= 75; angle += 5) {
			for (lDistance = 15; lDistance <= 30; lDistance += 3) {
				//Calculate wDistance
				double rAngle = ((90 - angle)*Math.PI)/180; //Turn into radians
				//Using law of cosines
				wDistance = (int)Math.round(Math.sqrt(
						Math.pow(distLewisWatney, 2) + 
						Math.pow(lDistance, 2) - 
						(2*distLewisWatney*lDistance*Math.cos(rAngle))));
				//Add row
				result += row + ") " + angle + " | " + lDistance + " | " + wDistance + "\n";
				//End row
				row++;
			}
		}

		return result;
	}

	/**
	 * This method computes how long it will take Watney and Lewis to walk to the hab
	 * given their distances from it. The necessary distances must be looked up within
	 * the table provided as a parameter. 
	 * @param distLewisWatney The distance Lewis is from Watney, as measured by her suit
	 * @param table The table of angle and distance values generated from another method
	 * @param row The row of the table that should be used in the calculation
	 * @return
	 */
	public String computeWalkingTime (double distLewisWatney, String table, int row) {
		String result = "";
		//TODO: Traverse the table to get the correct row's string. (Hint: look at the 
		//starter code for the interpretHexadecimal method to see a cool trick for stepping
		//through a String and breaking it apart using a certain character.) 
		String line;
		String[] rows = table.split("\n");
		line = rows[row]; // +1 to account for the table header
		
		//TODO: Extract from that row the needed distance. 
		int wDistance, lDistance;
		lDistance = Integer.parseInt(line.split("\\|")[1].trim());
		wDistance = Integer.parseInt(line.split("\\|")[2].trim());
		
		//TODO: Compute the walking time for Watney and Lewis to the ship. 
		//Watney is injured so his walking pace is 0.9 meters per second. 
		//Lewis' walking pace is 1.7 meters per second.
		long wTime, lTime;
		lTime = Math.round(lDistance / 1.7);
		wTime = Math.round(wDistance / 0.9);
		
		result += "Watney walking time to ship: " + wTime + " seconds\n";
		result += "Lewis walking time to ship: " + lTime + " seconds\n";
		return result;
	}

	/**
	 * This method interprets a series of angles around a circle to find the hexadecimal
	 * digit they "point to" on the circle. Then, it maps those hexadecimal pairs to characters from the
	 * English alphabet. The 360 degrees of the circle are broken into 16 equal pieces of 22.5 degrees each. 
	 * @param angles The comma-delimited list of angles
	 * @return a String with the message in English
	 */
	public String interpretHexadecimal(String angles) {
		String result = "";
		//An easy way to break apart a list of things is to make a Scanner on it
		//This Scanner is unrelated to any other variables with the same name because this
		//one is only meaningful inside this method. Also, this scanner does not read from the
		//console! It only operates over the one String you created it on, namely angles. 
		Scanner s = new Scanner(angles);
		//You can tell each Scanner what you want it to use to break up the String. 
		//Making it a comma, when you call next() you get whatever is between the commas.
		//Pretty convenient. 
		s.useDelimiter(",");

		//TODO: Complete this method :) 
		double angle;
		int val1, val2;
		char letter;
		while (s.hasNext()) {
			angle = Double.parseDouble(s.next());
			val1 = (int)((angle%360) / (360/16.0)); //Get the 0-15 "hex" value
			angle = Double.parseDouble(s.next()); //Assume there is ALWAYS a pair
			val2 = (int)((angle%360) / (360/16.0)); //Get the 0-15 "hex" value
			letter = (char)((val1*16) + val2); //Turn the val1/2 into the appropriate combined value
			result += letter;
		}
		
		s.close();
		//Shhh don't worry, its over
		return result;
	}
}

