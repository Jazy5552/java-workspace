/*
 * Jassiel Llerena
 * Cristina Cantero
 */
import java.util.Scanner;

public class Lab12 {
	static Scanner in;

	public static void main(String[] args) {
		Shape[] sArr = new Shape[20];
		in = new Scanner(System.in);
		int input, i;

		System.out.println("How many shapes do you want to create?");
		input = in.nextInt();

		for (i = 0; i < input; i++) {
			sArr[i] = addNewShape();
		}

		// Menu
		do {
			System.out.println("Select an option:\n"
					+ "1. Display info for circles\n"
					+ "2. Display info for rectangles\n"
					+ "3. Display info for triangles\n"
					+ "4. Add another shape\n"
					+ "5. Exit");
			
			input = in.nextInt();
			if (input >= 1 && input <= 3) {
				print(sArr, input);
			} else if (input == 4) {
				sArr[i] = addNewShape();
				i++;
			}
			
		} while(input != 5);
		
		System.out.println("Good bye");
		in.close();
	}

	public static Shape addNewShape() {
		int intput, num1, num2;
		String color;
		System.out
				.println("What shape do you want to make (1=circle, 2=rectangle, 3=triangle)?");
		intput = in.nextInt();
		in.nextLine(); // Fix trailing new line
		switch (intput) {
		case 1:
			System.out.println("What color is the circle?");
			color = in.nextLine();
			System.out.println("What's the radius?");
			num1 = in.nextInt();
			return new Circle(color, num1);
		case 2:
			System.out.println("What color is the rectangle?");
			color = in.nextLine();
			System.out.println("What's the height?");
			num1 = in.nextInt();
			System.out.println("What's the width?");
			num2 = in.nextInt();
			return new Rectangle(color, num1, num2);
		case 3:
			System.out.println("What color is the triangle?");
			color = in.nextLine();
			System.out.println("What's the height?");
			num1 = in.nextInt();
			System.out.println("What's the base?");
			num2 = in.nextInt();
			return new Rectangle(color, num1, num2);
		default:
			System.out.println("Invalid shape!");
			return null;
		}
	}

	public static void print(Shape myshapes[], int option) {
		// Option will be
		// 1=All circles
		// 2=All rectangles
		// 3=All triangles
		int counter = 1; // Keep count of found shapes

		for (int i = 0; i < myshapes.length; i++) {
			if (option == 1 && myshapes[i] instanceof Circle) {
				System.out.println("--------------------------------");
				System.out.println("Circle " + counter + "'s Info:");
				System.out.println(myshapes[i].toString());
				counter++;
				System.out.println("--------------------------------");
			} else if (option == 2 && myshapes[i] instanceof Rectangle) {
				System.out.println("--------------------------------");
				System.out.println("Rectangle " + counter + "'s Info:");
				System.out.println(myshapes[i].toString());
				counter++;
				System.out.println("--------------------------------");
			} else if (option == 3 && myshapes[i] instanceof Triangle) {
				System.out.println("--------------------------------");
				System.out.println("Triangle " + counter + "'s Info:");
				System.out.println(myshapes[i].toString());
				counter++;
				System.out.println("--------------------------------");
			}
		}
	}

}
