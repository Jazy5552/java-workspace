package jazy.programming1.school;
import javax.swing.JOptionPane;

public class school1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x1 = Integer.parseInt(JOptionPane.showInputDialog("Input number..","Here..."));
		int x2 = Integer.parseInt(JOptionPane.showInputDialog("Input 2nd number..","Here..."));
		int x3 = Integer.parseInt(JOptionPane.showInputDialog("Input 3rd number..","Here..."));
		
		JOptionPane.showMessageDialog(null,"This is your numbers " + x1+" "+x2+" "+x3 + "\nThe sum is "+(x1+x2+x3)+"\nThe difference is "+(x1-x2-x3),"Title",JOptionPane.PLAIN_MESSAGE);
		
		
	}

}
