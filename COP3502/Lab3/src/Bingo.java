/*
 * Jassiel Llerena
 * Mark Schuster
 */
import java.util.Scanner;

public class Bingo {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num1,num2;
		
		System.out.print("Input 1: ");
		num1 = in.nextInt();
		System.out.print("Input 2: ");
		num2 = in.nextInt();
		
		if (num1 < num2) {
			System.out.println(num1 + " < " + num2);
		} else if (num2 < num1) {
			System.out.println(num1 + " > " + num2);
		} else if (num1 == num2) {
			System.out.println("Bingo!");
		}
		
		in.close();
	}

}
