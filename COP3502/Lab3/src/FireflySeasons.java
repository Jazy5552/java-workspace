/*
 * Jassiel Llerena
 * Mark Schuster
 */
import java.util.Scanner;

public class FireflySeasons {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int month, year;
		int bSeason, rSeason;
		
		System.out.print("Enter year: ");
		year = in.nextInt();
		System.out.print("Enter month: ");
		month = in.nextInt();
		
		bSeason = (((month-1) / 3) + 3) % 4; //Shift the seasons
		// 0 = spring
		// 1 = summer
		// 2 = autumn
		// 3 = winter
		rSeason = (bSeason + (5 - (year % 5))) % 5; //Get the rseason in reference to bseason
		// 0 = spring
		// 1 = summer
		// 2 = autumn
		// 3 = winter
		// 4 = off-season
		
		//Beaumonde seasons
		if (bSeason == 0) {
			System.out.println("Beaumonde: spring");
		} else if (bSeason == 1) {
			System.out.println("Beaumonde: summer");
		} else if (bSeason == 2) {
			System.out.println("Beaumonde: autumn");
		} else if (bSeason == 3) {
			System.out.println("Beaumonde: winter");
		}
		
		//Regina seasons
		if (rSeason == 0) {
			System.out.println("Regina: spring");
		} else if (rSeason == 1) {
			System.out.println("Regina: summer");
		} else if (rSeason == 2) {
			System.out.println("Regina: autumn");
		} else if (rSeason == 3) {
			System.out.println("Regina: winter");
		} else if (rSeason == 4) {
			System.out.println("Regina: off-season");
		}
			
		
		//Done with this shit
		in.close();
	}
}
