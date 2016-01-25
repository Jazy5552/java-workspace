/*
 * Jassiel Llerena
 * Jorge Gando
 */
import java.util.Scanner;

public class TriangleArea {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int base;
		int height;
		double area;
		
		System.out.print("Base of the triangle: ");
		base = in.nextInt();
		
		System.out.print("Height of the triangle: ");
		height = in.nextInt();
		
		area = ((double)base * height) / 2;
		
		System.out.println("The area of the triangle is " + area);
		in.close();

	}

}
