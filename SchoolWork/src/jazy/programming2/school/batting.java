/*
 *Author Comments
 *Take and use as pleased. Courtesy of Jazy5552 
*/
package jazy.programming2.school;

import java.util.Scanner;

public class batting {

	public static void main(String[] args) { //Useless method...(long story)
		System.out.println("Welcome to the Batting Average Calculator.\nBy: Jazy5552");
		calculate();
		System.out.printf("\nThank you for using the calculator\nShutting down now!");
		System.exit(0);
	}
	
	public static void calculate() { //The Beef
		int atbat = getInt("Enter number of times at bat: ",false);
		float[] bases = new float[atbat + 1];
		System.out.printf("%n0 = out, 1 = single, 2 = double, 3 = triple, 4 = home run");
		for (int i=1;i<=atbat;i++){
			bases[i] = getInt("%nResult for at-bat " + i + ": ",true);
		}
		
		float bAverage = 0;
		for (int i=1;i<=atbat;i++){
			if (bases[i]!=0) bAverage+=1;
		}
		bAverage = bAverage/atbat; //Nasty!!!
		System.out.printf("%nBatting average: %.3f",bAverage);
		
		float sPercent = 0;
		for (int i=1;i<=atbat;i++){
			sPercent+=bases[i];
		}
		sPercent = sPercent/atbat;
		System.out.printf("%nSlugging percent: %.3f", sPercent);
		
		getContinue(); //Not sure why I like so many methods :\
		
	}

	
	public static int getInt(String display, boolean test){
		Scanner scan1 = new Scanner(System.in);
		int in = 0;
		
		try {
			System.out.printf(display);
			in = scan1.nextInt();
		} catch(Exception e) {
			System.out.printf("Only integers are allowed!");
			return getInt(display,test); //Slightly nasty
		} 
		if ((test)&&(in>=0)&&(in<=4))  return in;
		else if (!test) return in;
		else {
			System.out.print("Number must be between 0-4");
			return getInt(display,test);
		}
	}
	
	public static void getContinue(){
		Scanner scan1 = new Scanner(System.in);
		System.out.printf("%nAnother batter? (y/n) ");
		String in = scan1.next();
		if ((in.equals("y"))||(in.equals("Y"))) {
			calculate();
		}
		else if ((in.equals("n"))||(in.equals("N"))) {
			//Peace!
		}
		else {
			System.out.printf("%s is not a valid response%nPlease try again...",in);
			getContinue();
		}
	}

}
