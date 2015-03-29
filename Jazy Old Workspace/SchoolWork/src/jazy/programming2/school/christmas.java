/*
 * Author comments...
 * Remove the //s to create a scenario where it will ask for user input
 * Free to use or take as pleased
 * Courtesy of Jazy5552
*/
package jazy.programming2.school;
//import javax.swing.JOptionPane;

public class christmas {

	public static void main(String[] args) {
		int number;
		number = 12;
		//String ask=JOptionPane.showInputDialog("Enter the number of lines");
		//if (testInt(ask)) number=Integer.parseInt(ask); else System.exit(0);
		System.out.println();

		for(int i=1; i<=number; i++) {
		System.out.print("On the ");
		switch(i) {
		case 1: System.out.print("1st"); break;
		case 2: System.out.print("2nd"); break;
		case 3: System.out.print("3rd"); break;
		default: System.out.print(i+"th");
		}
		System.out.println(" day of Christmas my true love sent to me");
		switch(i) {
		case 12: System.out.print("Twelve Drummers Drumming, ");
		case 11: System.out.println("Eleven Pipers Piping, ");
		case 10: System.out.print("Tten Lords a Leaping, ");
		case 9: System.out.print("Nine Ladies Dancing, ");
		case 8: System.out.println("Eight Maids a Milking, ");
		case 7: System.out.print("Seven Swans a Swimming, ");
		case 6: System.out.print("Six Geese a Laying, ");
		case 5: System.out.println("Five Gold Rings,");
		case 4: System.out.print("Four Calling Birds, ");
		case 3: System.out.print("Three French Hens, ");
		case 2: System.out.print("Two Turtle Doves,\nand ");
		case 1: System.out.println("a Partridge in a Pear Tree.\n");
		} } }
		
		
		/*
		private static boolean testInt(String x){ //Tests the input to make sure it's an int
			int i=0;
			boolean finish=true;
			for (i=0;i<3;){
			try{
				Integer.parseInt(x);
			} catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null,"That is NOT a number!!!","Error",JOptionPane.OK_OPTION);
				x = JOptionPane.showInputDialog("Please try again...");
				finish=false;
			} if (finish==false) {i++; finish=true;} else break;
			}
			if (i==3) return false;
			else return true;
		}
		*/

}
