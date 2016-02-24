/*
 * Jassiel Llerena
 * Robert Beck
 */
import java.util.Scanner;

public class ConvertToDecimal {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String line;
		int mainLoop, choice, dNumber = 0;
		
		System.out.print("How many numbers do you want to convert? : ");
		line = in.nextLine();
		mainLoop = Integer.parseInt(line);
		
		for (int i=0; i < mainLoop; i++) {
			System.out.print("\n" 
					+ "Convert from\n" 
					+ "1. Binary\n" 
					+ "2. Octal\n"
					+ "3. Hexadecimal\n\n"
					+ "Choice: ");
			line = in.nextLine();
			choice = Integer.parseInt(line);
			
			if (choice > 3 || choice < 1) {
				//You done goofed
				System.out.println("\nYou have entered an invalid choice!");
				i--;
				continue;
			}
			
			System.out.print("\nNumber to convert: ");
			line = in.nextLine();
			//Convert line to the appropriate number
			if (choice == 1) {
				//Binary
				dNumber = Integer.parseInt(line, 2);
			} else if (choice == 2) {
				//Octal
				dNumber = Integer.parseInt(line, 8);
			} else if (choice == 3) {
				//Octal
				dNumber = Integer.parseInt(line, 16);
			}
			
			System.out.println("\nConversion: " + dNumber);
		}
		in.close(); //Shhh
	}

}
