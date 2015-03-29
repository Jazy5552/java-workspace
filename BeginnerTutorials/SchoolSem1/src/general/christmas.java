/*
 * Author comments...
 * Remove the //s to create a scenario where it will ask for user input
 * Free to use or take as pleased
 * Courtesy of Jazy5552
*/
package general;
//import javax.swing.JOptionPane;

public class christmas {

	public static void main(String[] args) {
		int number;
		number = 12;
		//String ask=JOptionPane.showInputDialog("Enter the number of lines");
		//if (testInt(ask)) number=Integer.parseInt(ask); else System.exit(0);
		mainClass.println();

		for(int i=1; i<=number; i++) {
		mainClass.print("On the ");
		switch(i) {
		case 1: mainClass.print("1st"); break;
		case 2: mainClass.print("2nd"); break;
		case 3: mainClass.print("3rd"); break;
		default: mainClass.print(i+"th");
		}
		mainClass.println(" day of Christmas my true love sent to me");
		switch(i) {
		case 12: mainClass.print("Twelve Drummers Drumming, ");
		case 11: mainClass.println("Eleven Pipers Piping, ");
		case 10: mainClass.print("Tten Lords a Leaping, ");
		case 9: mainClass.print("Nine Ladies Dancing, ");
		case 8: mainClass.println("Eight Maids a Milking, ");
		case 7: mainClass.print("Seven Swans a Swimming, ");
		case 6: mainClass.print("Six Geese a Laying, ");
		case 5: mainClass.println("Five Gold Rings,");
		case 4: mainClass.print("Four Calling Birds, ");
		case 3: mainClass.print("Three French Hens, ");
		case 2: mainClass.print("Two Turtle Doves,\nand ");
		case 1: mainClass.println("a Partridge in a Pear Tree.\n");
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
