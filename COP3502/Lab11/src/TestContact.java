//Jassiel Llerena
//Tony Parra
//David Stellear
import java.util.Scanner;

public class TestContact {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String inS;
		Contact c1,c2,c3;
		
		System.out.print("Enter contact 1 information:");
		inS = in.nextLine();
		c1 = new Contact(inS);
		
		System.out.print("Enter contact 2 information:");
		inS = in.nextLine();
		c2 = new Contact(inS);
		
		System.out.print("Enter contact 3 information:");
		inS = in.nextLine();
		c3 = new Contact(inS);
		
		/*
		c1 = new Contact("Apple Berry-Mansanita-02.12.1993");
		c2 = new Contact("Apple Fruit-Frutica-01.05.1997");
		c3 = new Contact("Apple Melon-Coquito-30.01.1990");
		*/

		c1.printContact();
		c2.printContact();
		c3.printContact();
		
		
		in.close(); //Shhh
	}

}
