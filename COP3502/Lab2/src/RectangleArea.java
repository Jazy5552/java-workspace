/*
 * Jassiel Llerena
 * Jorge Gando
 */
import java.util.Scanner;

public class RectangleArea {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int length;
		int width;
		int area;
		
		System.out.print("Length of the rectangle: ");
		length = in.nextInt();
		
		System.out.print("Width of the rectangle: ");
		width = in.nextInt();
		
		area = length * width;
		
		System.out.println("The area of the rectange is " + area);
		in.close();
	}

}
