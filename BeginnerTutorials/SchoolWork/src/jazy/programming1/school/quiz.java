/**
 * Keeping it simple...
 * 10 Question quiz
 */
package jazy.programming1.school;

import javax.swing.JOptionPane;

public class quiz {

	public static void main(String[] args) {
		String[] questions = new String[10];
		String[] ans = new String[10];
		int right = 0; //Store number of right here
		//             QUESTIONS             Answers
		questions[0] = "What is 2+2"; ans[0] = "4";
		questions[1] = "What is 2-2"; ans[1] = "0";
		questions[2] = "What 3+10"; ans[2] = "13";
		questions[3] = "What is Ramirez's name?"; ans[3] = "Ramirez";
		questions[4] = "What is this classroom number?"; ans[4] = "4236";
		questions[5] = "What is Ramirez's age?"; ans[5] = "86";
		questions[6] = "Spell racecar backwards."; ans[6] = "racecar";
		questions[7] = "What language is this CODED in?"; ans[7] = "Java";
		questions[8] = "What is 4^3?"; ans[8] = "64";
		questions[9] = "How many questions are in this test?"; ans[9] = "10";
		
		for (int i=0;i<questions.length;i++){ //For loop for all the number of questions[]
			String x = JOptionPane.showInputDialog(null,questions[i],"Question number " + (i+1),JOptionPane.PLAIN_MESSAGE); //Display question and place answer into x
			if (x.toLowerCase().equals(ans[i].toLowerCase())){ //STRING.equals(STRING) function is used to compare strings. ! = NOT
				right++; //If right add 1 to INT wrong
			}
		}
		//Now calculate the grade
		double pw = (right/questions.length)*100; //Percent formula :D
		String comment; //We'll put their grade in here
		if (pw>89) comment = "A congrats!";
		else if (pw>79) comment = "B";
		else if (pw>69) comment = "C";
		else if (pw>59) comment = "D";
		else if (pw<60) comment = "F!!";
		else comment = "???";
		//Now we tell them
		JOptionPane.showMessageDialog(null,String.format("You recieved a(n) %s\nYou had %d right out of %d.",comment,right,questions.length),"Final Score.",JOptionPane.PLAIN_MESSAGE);
		//And your done ;)
	}

}
