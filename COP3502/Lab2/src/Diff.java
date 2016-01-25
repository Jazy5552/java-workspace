/*
 * Jassiel Llerena
 * Jorge Gando
 */
import java.util.Scanner;

public class Diff {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char first;
		char second;
		int diff;
		
		System.out.print("Enter the first letter: ");
		first = in.nextLine().charAt(0);
		
		System.out.print("Enter the second letter: ");
		second = in.nextLine().charAt(0);
		
		diff = (int)second - (int)first;
		System.out.println("The difference is " + diff);
	
		in.close();
	}

}
