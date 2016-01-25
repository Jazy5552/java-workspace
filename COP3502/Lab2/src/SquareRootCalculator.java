/*
 * Jassiel Llerena
 * Jorge Gando
 */
import java.util.Scanner;
import java.lang.Math;

public class SquareRootCalculator {
	public static void main(String  args []){
		Scanner input = new Scanner(System.in);
		int x;
		double y;
		
		System.out.print("Enter a number: ");
		x = input.nextInt();
		y = Math.sqrt(x);	
		
		System.out.print("The square root of " + x + " is " + y);
		
		input.close();
	}
}
