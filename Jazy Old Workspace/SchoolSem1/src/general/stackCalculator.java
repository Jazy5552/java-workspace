/**
 * Author's Comments
 * Free to use and modify
 * Courtesy of Jazy5552 (No credit needed but grateful)
 */
package general;

import java.util.Scanner;
import java.util.ArrayList;

public class stackCalculator {
	ArrayList<Double> nums = new ArrayList<Double>(0);
	public static void main(String[] args) {
		mainClass.printf("Welcome to Jazy's stack calculator\n");
		new stackCalculator();
		keyListener.wait(2000);
		mainClass.reset();
	}

	stackCalculator(){
		mainClass.printf("Commands: push [NUMBER], add, sub, mult, div, clear, clearall, or quit");
		boolean on=true;
		while(on){
			mainClass.printf("\n\n:: ");
			Scanner scan1 = new Scanner(mainClass.nextLine());
			String x = scan1.next().toLowerCase();
			
			if ((x.equals("push"))&&(scan1.hasNextDouble())){
				double tmp = scan1.nextDouble();
				nums.add(tmp);
			}
			else if ((x.equals("add"))&&(nums.size()>1)){
				double tmp1 = nums.get(nums.size()-1);
				double tmp2 = nums.get(nums.size()-2);
				tmp2+=tmp1;
				nums.set(nums.size()-2, tmp2);
				nums.remove(nums.size()-1);
			}
			else if ((x.equals("sub"))&&(nums.size()>1)){
				double tmp1 = nums.get(nums.size()-1);
				double tmp2 = nums.get(nums.size()-2);
				tmp1-=tmp2;
				nums.set(nums.size()-2, tmp1);
				nums.remove(nums.size()-1);
			}
			else if ((x.equals("multiply"))&&(nums.size()>1)){
				double tmp1 = nums.get(nums.size()-1);
				double tmp2 = nums.get(nums.size()-2);
				tmp2*=tmp1;
				nums.set(nums.size()-2, tmp2);
				nums.remove(nums.size()-1);
			}
			else if ((x.equals("div"))&&(nums.size()>1)){
				double tmp1 = nums.get(nums.size()-1);
				double tmp2 = nums.get(nums.size()-2);
				tmp1/=tmp2;
				nums.set(nums.size()-2, tmp1);
				nums.remove(nums.size()-1);
			}
			else if (x.equals("clearall")) nums.clear();
			else if (x.equals("clear")){
				if (nums.size()>0) nums.remove(nums.size()-1);
			}
			else if (x.equals("quit")){
				mainClass.printf("Thank you for using Jazy's Stack Calcualtor\nShutting down!");
				break; //Alternative to exit
				//System.exit(0);
			}
			else if (nums.size()==1) mainClass.printf("Two or more numbers needed...\n");
			else{
				mainClass.printf("(%s) is not a valid command...\n", x);
				mainClass.printf("Commands: push [NUMBER], add, sub, mult, div, clear, or quit\n\n");
			}
			
			if (nums.isEmpty()) mainClass.printf("empty\n");
			else{
				for (int i=nums.size()-1;i>=0;i--){
					mainClass.printf("%.1f\n",nums.get(i));
				}
			}
		}
	}
}
