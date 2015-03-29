package tut.starter.first;
import javax.swing.JOptionPane;


public class jazy {
	public static void main(String[] args) {
		
		String in1,in2;
		
		while(true){
		in1 = JOptionPane.showInputDialog("Input a number!!");
		if (testInt(in1)) break; }
		
		while(true){
		in2 = JOptionPane.showInputDialog("Input another number!");
		if (testInt(in2)) break; }
		
		int sum = Integer.parseInt(in1) + Integer.parseInt(in2);
		
		JOptionPane.showMessageDialog(null, "The sum is " + sum, "The Sum of your Numbers", JOptionPane.PLAIN_MESSAGE);
		
		
	}
	public static boolean testInt(String x){
		try{
			Integer.parseInt(x);
		} catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null,"That is NOT a number!!!","Error",JOptionPane.OK_OPTION); return false;
		} 
		return true;
	}
}
