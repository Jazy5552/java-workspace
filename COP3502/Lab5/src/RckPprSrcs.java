/*
 * Jassiel Llerena
 * Charels Knox
 */

import java.util.Random;
import java.util.Scanner;

public class RckPprSrcs {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Random r = new Random();
		int com, choice, wins = 0, loses = 0;
		System.out.println( "=====================\n"+
							"Rock, Paper, Scissors\n"+
							"=====================\n\n\n");
		
		do {
			System.out.println( "1. Rock\n"+
								"2. Paper\n"+
								"3. Scissors\n"+
								"4. Exit\n\n");
			System.out.print("Input: ");
			choice = in.nextInt();
			if (choice == 4) break;
			
			//Get com input
			com = r.nextInt(3) + 1;
			
			System.out.println("You choose " + choice + "\n");
			if (com == 1) {
				System.out.println("The computer choose Rock\n");
				if (choice == 1) {//So inefficient...
					System.out.println("Rock = Rock, Draw!");
				} else if (choice == 2) {
					System.out.println("Paper > Rock, You Win!");
					wins++;
				} else if (choice == 3) {
					System.out.println("Scissors < Rock, You Lose!");
					loses++;
				} 
			} else if (com == 2) {
				System.out.println("The computer choose Paper\n");
				if (choice == 1) { //So inefficient...
					System.out.println("Rock < Paper, You Lose!");
					loses++;
				} else if (choice == 2) {
					System.out.println("Paper = Paper, Draw!");
				} else if (choice == 3) {
					System.out.println("Scissors > Paper, You Win!");
					wins++;
				} 
			} else if (com == 3) {
				System.out.println("The computer choose Scissors\n");
				if (choice == 1) { //So inefficient...
					System.out.println("Rock > Scissors, You Win!");
					wins++;
				} else if (choice == 2) {
					System.out.println("Paper < Scissors, You Lose!");
					loses++;
				} else if (choice == 3) {
					System.out.println("Scissors = Scissors, Draw!");
				} 
			}
			System.out.println("\n**Wins: " + wins + "  Loses: " + loses + "**");
		} while (choice != 4); //Unnecessary due to break
		
		in.close();
	}

}
