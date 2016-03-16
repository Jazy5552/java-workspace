/*
 * Jassiel Llerena
 * char[][][] x = new char[9][5][4]
 * The array has 9*5*4 = 180 elements
 * x.length woudl be 9
 * x[2].length would be 5
 * x[0][0].length would be 4
 */
import java.util.Random;

public class Rand4x4 {

	public static void main(String[] args) {
		Random r = new Random(); //Create the random generator
		int[][] x = new int[4][4]; //Create a 4x4 matrix (double array)
		int maxRi, maxCi, max, diag, count;
		
		//Fill in the matrix
		for(int i=0; i<x.length; i++) {
			for (int j=0; j<x[i].length; j++) {
				x[i][j] = r.nextInt(2); //Insert 1 or 0 (2 non inclusive)
				System.out.print(x[i][j]); //Print out the saved number
			}
			System.out.println(); //Just a newline
		}
		
		//Get the largest row index by looping through the rows
		max = 0; maxRi = 0;
		for (int i=0; i<x.length; i++) {
			count = 0;
			for (int j=0; j<x[i].length; j++) {
				count += x[i][j]; //Increment if its a 1
			}
			//Lets count
			if (count > max) {
				//El New Max
				max = count; //Set the new max
				maxRi = i; //Store the row index
			}
		}
		
		//Get the largest column index
		max = 0; maxCi = 0;
		for (int j=0; j<x[0].length; j++) {
			count = 0;
			for (int i=0; i<x.length; i++) {
				count += x[i][j];
			}
			//Lets count
			if (count > max) {
				max = count; //Set the new max
				maxCi = j; //Store the column index
			}
		}
		
		//Get the sum of the diagonal
		diag = 0;
		for (int i=0; i<x.length; i++) {
			//Matrix must be nxn!
			diag += x[i][i];
		}
		
		//Print out the stuffs
		System.out.println("The largest row index: " + maxRi);
		System.out.println("The largest column index: " + maxCi);
		System.out.println("The sum of the diagonal: " + diag);
		
	}

}
