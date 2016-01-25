/*
 * Jassiel Llerena
 * Jorge Gando
 */
import java.util.Scanner;

public class GetDigits {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int number;
		int first,second,third;
		
		System.out.print("Enter a number (Between 100-999): ");
		number = in.nextInt();
		
		first = number/100;
		second = (number%100)/10;
		third = number%10;
		
		System.out.println("The hundreds digit is: " + first);
		System.out.println("The tens digit is: " + second);
		System.out.println("The ones digit is: " + third);
		
		in.close();
	}

}
