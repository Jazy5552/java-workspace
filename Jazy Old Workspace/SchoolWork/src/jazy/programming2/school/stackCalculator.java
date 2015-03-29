/**
 * Author's Comments
 * Free to use and modify
 * Courtesy of Jazy5552 (No credit needed but grateful)
 */
package jazy.programming2.school;

import java.util.Scanner;
import java.util.ArrayList;

public class stackCalculator {
	ArrayList<Double> nums = new ArrayList<Double>(0);
	public static void main(String[] args) {
		System.out.printf("Welcome to Jazy's stack calculator\n");
		@SuppressWarnings("unused")
		stackCalculator cal = new stackCalculator();
	}

	stackCalculator(){
		System.out.printf("Commands: push [NUMBER], add, sub, mult, div, clear, clearall, or quit");
		boolean on=true;
		while(on){
			System.out.printf("\n\n:: ");
			Scanner scan1 = new Scanner(System.in);
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
				System.out.printf("Thank you for using Jazy's Stack Calcualtor\nShutting down!");
				//on = false; //Alternative to exit
				System.exit(0);
			}
			else if (nums.size()==1) System.out.printf("Two or more numbers needed...\n");
			else{
				System.out.printf("(%s) is not a valid command...\n", x);
				System.out.printf("Commands: push [NUMBER], add, sub, mult, div, clear, or quit\n\n");
			}
			
			if (nums.isEmpty()) System.out.printf("empty\n");
			else{
				for (int i=nums.size()-1;i>=0;i--){
					System.out.printf("%.1f\n",nums.get(i));
				}
			}
		}
	}
}
