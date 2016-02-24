/*
 * Jassiel Llerena
 * Robert Beck
 */
import java.util.Scanner;

public class FibonacciNumber {

	public static void main(String args[]) {

		int temp, n, result;

		int x = 1, y = 1;

		Scanner sc = new Scanner(System.in);

		System.out.print("Which number of Fibonacci do you want?: ");

		n = sc.nextInt();

		System.out.println();

		for (int i = 3; i <= n; i++) {

			// TODO
			temp = x + y;
			x = y;
			y = temp;
			// END

		}

		result = y;

		System.out.printf("The %d number of the Fibonacci sequence is %d", n,
				result);

		sc.close();

	}

}