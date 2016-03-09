import java.lang.Math;
import java.util.Scanner;

public class Triangle {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		double a, b, c, base, height, area;
		int choice = 0;

		do {
			System.out.println("--- Triangle Calculator ---");
			System.out.println("1. Enter Sides");
			System.out.println("2. Enter base and height");
			System.out.println("3. Exit");
			choice = in.nextInt();
			in.nextLine(); // Get rid of newline
			if (choice == 1) {
				System.out.print("Enter the lengths of three sides: ");
				a = in.nextDouble();
				b = in.nextDouble();
				c = in.nextDouble();
				in.nextLine(); // Get rid of newline

				area = getArea(a, b, c);
				if (area != -1)
					System.out.println("The area is " + area);
				else
					displayError();
			} else if (choice == 2) {
				System.out.print("Enter the base and the height: ");
				base = in.nextDouble();
				height = in.nextDouble();
				in.nextLine(); // Get rid of newline

				area = getArea(base, height);
				System.out.println("The area is " + area);
			} else if (choice == 3) {
				// Just exit
			} else {
				// Invalid choice
				System.out.println("Invalid choice!");
			}
			System.out.println(); // Just a new line
		} while (choice != 3);

		in.close();
	}

	public static double getArea(double a, double b, double c) {
		if (validTriangle(a, b, c)) {
			double s = (a + b + c) / 2;
			return Math.sqrt(s * (s - a) * (s - b) * (s - c));
		}
		// Not a valid triangle
		return -1;
	}

	public static double getArea(double base, double height) {
		return base * height / 2;
	}

	public static boolean validTriangle(double a, double b, double c) {
		if (a > (b + c) || b > (a + c) || c > (a + b)) {
			// One of the sides is too large
			return false;
		}
		// Sides are A ou K
		return true;
	}

	public static void displayError() {
		System.out.println("The provided sides do not form a valid triangle");
	}

}
