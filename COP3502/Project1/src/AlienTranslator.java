import java.util.Scanner;

/**
 * 
 * This class translates messages from aliens on planets
 * 
 * Beaumonde and Regina into Earth English.
 * 
 * @author Kristy Boyer
 * 
 * @subauthor Jazy Llerena
 */

public class AlienTranslator {

	/**
	 * 
	 * This main method creates an AlienTranslator and then calls the
	 * 
	 * translateInput method. Students should not modify this method.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		AlienTranslator a = new AlienTranslator();

		Scanner s = new Scanner(System.in);

		a.translateInput(s);

	}

	/**
	 * 
	 * This method gets the necessary input from the user regarding planet
	 * 
	 * and the message to be translated.
	 * 
	 * Students should fill in the missing code in this method.
	 * 
	 * @param in
	 *            A Scanner created on the appropriate stream of input.
	 */

	public String translateInput(Scanner in) {

		// TODO: Get from user which planet the inter-galactic message is from.

		// 1=Beaumonde and 2=Regina

		// Store value in an int variable named planet. If it is not valid,
		// print

		// an error to the user and exit the program. Otherwise (a valid planet)

		// Get the inter-galactic message that needs to be translated.

		// Store it in a String variable named message

		// ---- > Fill in Student Code Here < ------
		int planet;
		String message;
		
		System.out.println("What planet?");
		planet = Integer.parseInt(in.nextLine());
		if (planet != 1 && planet != 2) {
			System.out.println("Not a valid planet.");
			return null;
		}
		
		System.out.println("What message?");
		message = in.nextLine();
		
		// ---- > End Student Code < -----

		// Teaching staff code - STUDENTS DO NOT MODIFY - extracts characters

		// one at a time from the String message and stores each character in

		// the char variable named charToTranslate

		// The result variable is used to build and store the translated message

		// which will be sent back when this method is finished.

		String result = "";

		for (int i = 0; i < message.length(); i++) {

			char charToTranslate = message.charAt(i);

			// End teaching staff code

			// TODO: translate the character stored in variable charToTranslate

			// and append the translated character to the result variable

			// ----- > Fill in Student Code Here < -----
			if (planet == 1) {
				//Two letters ahead so get two characters behind
				//A=65 Z=90 a=97 z=122
				if (charToTranslate > 64 && charToTranslate < 91) {
					//Is upper
					charToTranslate = (char)((charToTranslate - 65 + 26 - 2) % 26 + 65);
				} else if (charToTranslate > 96 && charToTranslate < 123) {
					//Is lower
					charToTranslate = (char)((charToTranslate - 97 + 26 - 2) % 26 + 97);
				}
			} else if (planet == 2) {
				//Two letters behind so get two characters ahead
				//A=65 Z=90 a=97 z=122
				if (charToTranslate > 64 && charToTranslate < 91) {
					//Is upper
					charToTranslate = (char)((charToTranslate - 65 + 26 + 2) % 26 + 65);
				} else if (charToTranslate > 96 && charToTranslate < 123) {
					//Is lower
					charToTranslate = (char)((charToTranslate - 97 + 26 + 2) % 26 + 97);
				}
			}
			
			result += charToTranslate; 
			// ----- > End Student Code < -----

		} // ends the for loop
		
		//TODO: perform the necessary output as specified in the requirements

		// **** This TODO was added to this starter code on Mon 1/25 2:55pm ****

		//------> Fill in Student Code < -------
		if (planet == 1) {
			System.out.println("Original message in Beaumondian: " + message);
		} else if (planet == 2) {
			System.out.println("Original message in Reginian: " + message);
		}
		
		System.out.println("Translation in Earth English: " + result);

		//------> End Student Code < ------- 

		// Sends back the value of the result variable

		return result;

	} // ends the translateInput method

} // ends the AlienTranslator class