/*
 * Jassiel Llerena
 * Charels Knox
 */

import java.util.Scanner;

public class PrimeCalculator {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int number;
		
		System.out.println( "================\n"+
							"Prime Calculator\n"+
							"================\n");
		do {
			System.out.print("Enter a number: ");
			number = in.nextInt();
			
			//Check if the number is prime
			int i=number-1;
			
			while (i > 1) {
				if (number % i == 0) {
					break;
				}
				i--;
			}
			
			if (i == 1 && number != 1) { //Mathematically correct
				//The loop didn't break and ran till 1 therefore the number is prime
				System.out.println("The number is prime");
			} else if (i > 1) {
				//The loop broke
				System.out.println("The number is not prime");
			}
			
		} while (number > 0);
		
		System.out.println("Good Bye!");
		in.close();
	}

}
