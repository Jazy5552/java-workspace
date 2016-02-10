/*
 * Jassiel Llerena
 * Jackson Harris
 */
import java.util.Scanner;

public class TwoWords {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String line, word1, word2;
		
		System.out.print("Enter two words: ");
		line = in.nextLine();
		
		//Substring from start to first space
		word1 = line.substring(0, line.indexOf(' '));
		//Substring from AFTER first space to end (implicitly)
		word2 = line.substring(line.indexOf(' ')+1);
		
		if (word1.equals(word2)) {
			System.out.println(word1);
		} else if (word1.charAt(0) == word2.charAt(0)) {
			System.out.println(word1);
			System.out.println(word2);
		} else {
			System.out.println(word1 + ' ' + word2);
		}

		in.close();
	}

}
