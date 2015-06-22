package jazy.programming.utilities;
import javax.swing.JOptionPane;


public class numbers {
	int[] nums;
	public numbers(int amount) {
		nums = new int[amount];
		String[] strings = new String[amount];
		
		for (int i=0;i<amount;i++) {
			while(true){
			strings[i] = JOptionPane.showInputDialog("Input a number...","Number Input");
			if (testInt(strings[i])) break; }
		}
		
		for (int i=0;i<strings.length;i++) {
			nums[i]=Integer.parseInt(strings[i]);
		}
		
	}
	
	public int[] getNumbers() {
		return nums;
	}
	
	public int getSum() {
		int x=0;
		for (int i=0;i<nums.length;i++) {
			x+=nums[i];
		}
		return x;
	}
	
	public int getAverage() {
		int x=0;
		for (int i=0;i<nums.length;i++) x+=nums[i];
		return x/nums.length;
	}
	
	private boolean testInt(String x){
		try{
			Integer.parseInt(x);
		} catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null,"That is NOT a number!!!","Error",JOptionPane.OK_OPTION); return false;
		} 
		return true;
	}
}
