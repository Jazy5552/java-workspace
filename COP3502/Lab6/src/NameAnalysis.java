/*
 * Jassiel Llerena
 * Robert Beck
 */
import java.util.Scanner;
import java.util.Random;

public class NameAnalysis {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Random r = new Random();

		String line, word1, word2;
		int charCount, random, min;
		//int vA, vE, vI, vO, vU;
		System.out.print("Name: ");
		line = in.nextLine();

		// Use the comma as delimiter
		word1 = line.substring(0, line.indexOf(',')); // Lastname
		word2 = line.substring(line.indexOf(',') + 1); // Firstname
		word1 = word1.toUpperCase();
		word2 = word2.toUpperCase();

		charCount = word1.length() + word2.length();
		random = r.nextInt(word2.length()-1);

		/*
		vA = word2.indexOf('A');
		vE = word2.indexOf('E');
		vI = word2.indexOf('I');
		vO = word2.indexOf('O');
		vU = word2.indexOf('U');
		*/
		
		min = word2.length()-1;
		
		//Use a for loop
		for (int i=word2.length()-1; i >= 0; i--) {
			char c = word2.charAt(i);
			if (c == 'A')
				min = i;
			else if (c == 'E')
				min = i;
			else if (c == 'I')
				min = i;
			else if (c == 'O')
				min = i;
			else if (c == 'U')
				min = i;
		}

		/*
		if (vA >= 0 && vA < min) {
			min = vA;
		}
		if (vE >= 0 && vE < min) {
			min = vE;
		}
		if (vI >= 0 && vI < min) {
			min = vI;
		}
		if (vO >= 0 && vO < min) {
			min = vO;
		}
		if (vU >= 0 && vU < min) {
			min = vU;
		}
		*/

		//Print shit out
		System.out.println(word1 + "," + word2);
		System.out.println(charCount);
		System.out.println(word2);
		System.out.println(min);
		System.out.println("The random character is " + word2.charAt(random) + " at position " + random + " of the first name.");
		
		in.close();
	}

}
