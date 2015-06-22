/*
 * Author comments
 * Free to take and use
 * Courtesy of Jazy5552
 */
package general;


public class keypad {

	public static void main(String[] args){
		mainClass.printf("Enter a string: "); 
		String x = mainClass.nextLine();
		mainClass.printf("\n%s\n",convertString(x));
		getContinue();
		mainClass.println("Shutting down app...");
		keyListener.wait(2000);
		mainClass.reset();
		
	}
	
	public static String convertString(String x){
		x = x.toUpperCase();
		String x1 = "";
		
		for (int i=0;i<x.length();i++) {
			switch(x.charAt(i)){
			case 'A': x1+="2"; break;
			case 'B': x1+="2"; break;
			case 'C': x1+="2"; break;
			case 'D': x1+="3"; break;
			case 'E': x1+="3"; break;
			case 'F': x1+="3"; break;
			case 'G': x1+="4"; break;
			case 'H': x1+="4"; break;
			case 'I': x1+="4"; break;
			case 'J': x1+="5"; break;
			case 'K': x1+="5"; break;
			case 'L': x1+="5"; break;
			case 'M': x1+="6"; break;
			case 'N': x1+="6"; break;
			case 'O': x1+="6"; break;
			case 'P': x1+="7"; break;
			case 'Q': x1+="7"; break;
			case 'R': x1+="7"; break;
			case 'S': x1+="7"; break;
			case 'T': x1+="8"; break;
			case 'U': x1+="8"; break;
			case 'V': x1+="8"; break;
			case 'W': x1+="9"; break;
			case 'X': x1+="9"; break;
			case 'Y': x1+="9"; break;
			case 'Z': x1+="9"; break;
			default: x1+=x.charAt(i); break;
			}
		}
		return x1;
	}
	
	public static void getContinue(){
		mainClass.printf("\nAnother number? (y/n) ");
		String in = mainClass.next();
		if ((in.equals("y"))||(in.equals("Y"))) {
			main(null);
		}
		else if ((in.equals("n"))||(in.equals("N"))) {
			//Peace
		}
		else {
			mainClass.printf("%s is not a valid response\nPlease try again...",in);
			getContinue();
		}
	}
}
