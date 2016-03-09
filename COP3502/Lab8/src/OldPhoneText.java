import java.util.Scanner;

public class OldPhoneText {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int count; //Store the number of letters
		char[] cArr; //The array of characters after processing the spaces
		System.out.print("Enter number of letters: ");
		count = in.nextInt();
		cArr = new char[count];
		System.out.print("Enter letters seperated by space: ");
		for (int i=0; i<count; i++) {
			//Read up to next space and only get the first character
			cArr[i] = in.next().charAt(0);
		}
		//toNumerals will print the answer
		toNumerals(cArr);
		in.close(); //Shhh bby
	}
	
	static void toNumerals(char[] word) {
		String result = "";
		char let;
		int num;
		for (int i=0; i<word.length; i++) {
			//Standardize the character
			let = Character.toLowerCase(word[i]);
			num = 1; //Initial value
			//Trickle down economy at work
			switch(let) {
			case 'z':
			case 'y':
			case 'x':
			case 'w':
				num++;
			case 'v':
			case 'u':
			case 't':
				num++;
			case 's':
			case 'r':
			case 'q':
			case 'p':
				num++;
			case 'o':
			case 'n':
			case 'm':
				num++;
			case 'l':
			case 'k':
			case 'j':
				num++;
			case 'i':
			case 'h':
			case 'g':
				num++;
			case 'f':
			case 'e':
			case 'd':
				num++;
			case 'c':
			case 'b':
			case 'a':
				num++;
			default:
				//Add num to the result string
				result += num;
			}
		}
		//Print out the final result string
		System.out.println(result);
	}

}
